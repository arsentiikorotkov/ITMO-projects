package info.kgeorgiy.ja.korotkov.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.net.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Implementation of {@link HelloClient} interface.
 *
 * @author Arsentiy Korotkov
 */
public class HelloUDPClient implements HelloClient {
    private static final int TIMEOUT = 500;

    /**
     * Runs Hello client.
     * This method should return when all requests are completed.
     *
     * @param host     server host
     * @param port     server port
     * @param prefix   request prefix
     * @param threads  number of request threads
     * @param requests number of requests per thread.
     */
    @Override
    public void run(final String host, final int port, final String prefix, final int threads, final int requests) {
        validateArgs(port, threads, requests);

        try (final ExecutorService service = Executors.newFixedThreadPool(threads)) {
            final SocketAddress socketServerAddr = new InetSocketAddress(host, port);

            IntStream.range(1, threads + 1).forEach(threadNum -> service.submit(() -> process(prefix, requests, socketServerAddr, threadNum)));
        }
    }

    private void process(String prefix, int requests, SocketAddress socketServerAddr, int threadNum) {
        try (final DatagramSocket socketClient = new DatagramSocket()) {
            final int bufferSize = socketClient.getReceiveBufferSize();
            socketClient.setSoTimeout(TIMEOUT);

            for (int requestNum = 1; requestNum <= requests; requestNum++) {
                while (!Thread.currentThread().isInterrupted()) {
                    final String request = String.format("%s%s_%s", prefix, threadNum, requestNum);

                    final DatagramPacket dispatchPacket = API.createDispatchPacket(request, socketServerAddr);

                    API.send(socketClient, dispatchPacket);

                    final DatagramPacket receivedPacket = new DatagramPacket(new byte[bufferSize], bufferSize);

                    if (!API.receive(socketClient, receivedPacket)) {
                        continue;
                    }

                    if (isValidResponse(receivedPacket, request)) {
                        break;
                    }
                }
            }
        } catch (final SocketException e) {
            System.err.println("The socket could not be opened, or the socket could not be bound.. Message: " + e.getMessage());
        }
    }

    private boolean isValidResponse(final DatagramPacket receivedPacket, final String request) {
        final String response = API.getResponse(receivedPacket);
        if (response.contains(request)) {
            System.out.println(response);
            return true;
        }

        return false;
    }

    private void validateArgs(final int port, final int threads, final int requests) {
        if (port < 1 | port > 65535) {
            throw new IllegalArgumentException("Port out of range");
        }

        if (threads < 1) {
            throw new IllegalArgumentException("Threads must be natural");
        }

        if (requests < 1) {
            throw new IllegalArgumentException("Requests must be natural");
        }
    }

    /**
     * Create {@link HelloUDPClient} and invoke {@link #run} on args.
     * Usage: java HelloUDPClient <host> <port> <prefix> <threads> <requests>
     *
     * @param args user input
     */
    public static void main(String[] args) {
        if (args == null || args.length != 5 || Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.err.println("Usage: java HelloUDPClient <host> <port> <prefix> <threads> <requests>");
            return;
        }

        final HelloClient client = new HelloUDPClient();
        try {
            client.run(args[0], Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        } catch (final NumberFormatException e) {
            System.err.println("<port> <threads> <requests> must be integers. Message: " + e.getMessage());
        } catch (final IllegalArgumentException e) {
            System.err.println("Args not valid. Message: " + e.getMessage());
        }
    }
}
