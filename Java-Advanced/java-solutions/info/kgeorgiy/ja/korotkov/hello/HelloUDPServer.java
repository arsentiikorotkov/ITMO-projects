package info.kgeorgiy.ja.korotkov.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.Console;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implementation of {@link HelloServer} interface.
 *
 * @author Arsentiy Korotkov
 */
public class HelloUDPServer implements HelloServer {
    private DatagramSocket socketServer;
    private ExecutorService service;
    private int bufferSize;

    /**
     * Starts a new Hello server.
     * This method should return immediately.
     *
     * @param port    server port.
     * @param threads number of working threads.
     */
    @Override
    public void start(final int port, final int threads) throws IllegalArgumentException {
        validateArgs(port, threads);

        initialize(port, threads);

        for (int i = 0; i < threads; i++) {
            service.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    final DatagramPacket receivedPacket = new DatagramPacket(new byte[bufferSize], bufferSize);

                    if (!API.receive(socketServer, receivedPacket)) {
                        continue;
                    }

                    final String request = String.format("Hello, %s", API.getResponse(receivedPacket));

                    final DatagramPacket dispatchPacket = API.createDispatchPacket(request, receivedPacket.getSocketAddress());

                    API.send(socketServer, dispatchPacket);
                }
            });
        }
    }

    private void initialize(final int port, final int threads) {
        try {
            socketServer = new DatagramSocket(port);
            bufferSize = socketServer.getReceiveBufferSize();
        } catch (final SocketException e) {
            System.err.println("There is an error creating or accessing a Socket. Message: " + e.getMessage());
        }

        service = Executors.newFixedThreadPool(threads);
    }

    private void validateArgs(final int port, final int threads) throws IllegalArgumentException {
        if (port < 1 | port > 65535) {
            throw new IllegalArgumentException("Port out of range");
        }

        if (threads < 1) {
            throw new IllegalArgumentException("Threads must be natural");
        }
    }

    /**
     * Stops server and deallocates all resources.
     */
    @Override
    public void close() {
        API.closeService(service);
        socketServer.close();
    }

    /**
     * Create {@link HelloUDPServer} and invoke {@link #start} on args.
     * Usage: java HelloUDPServer <port> <threads>
     *
     * @param args user input
     */
    public static void main(String[] args) {
        if (args == null || args.length != 2 || Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.err.println("Usage: java HelloUDPServer <port> <threads>");
            return;
        }

        try (final HelloServer server = new HelloUDPServer()) {
            server.start(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

            System.out.println("Enter \"exit\" to exit: ");
            Scanner scanner = new Scanner(System.in);
            while (!scanner.nextLine().equals("exit"));

        } catch (final NumberFormatException e) {
            System.err.println("<port> <threads> must be integers. Message: " + e.getMessage());
        } catch (final IllegalArgumentException e) {
            System.err.println("Args not valid. Message: " + e.getMessage());
        }
    }
}
