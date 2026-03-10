package info.kgeorgiy.ja.korotkov.hello;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * API for {@link HelloUDPClient} and {@link HelloUDPServer} interface.
 *
 * @author Arsentiy Korotkov
 */
public class API {
    private final static long TIMEOUT = 500;
    private final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    /**
     * Correctly close a service.
     *
     * @param executorService a service to close
     */
    public static void closeService(final ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(TIMEOUT, TIME_UNIT)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    /**
     * Get data from packet.
     *
     * @param receivedPacket a packet
     * @return data in string format
     */
    public static String getResponse(final DatagramPacket receivedPacket) {
        return new String(receivedPacket.getData(), receivedPacket.getOffset(),
                receivedPacket.getLength(), StandardCharsets.UTF_8);
    }

    /**
     * Create a packet with request to sending to socketAddr.
     *
     * @param request    data of creating packet
     * @param socketAddr socket of recipient
     * @return a packet
     */
    public static DatagramPacket createDispatchPacket(final String request, final SocketAddress socketAddr) {
        byte[] bytes = request.getBytes(StandardCharsets.UTF_8);
        return new DatagramPacket(bytes, 0, bytes.length, socketAddr);
    }

    /**
     * Try receives packet to socket owner.
     *
     * @param socketServer   a socket to receive
     * @param receivedPacket a packet to receive
     * @return true if receiving occurred correctly
     */
    public static boolean receive(final DatagramSocket socketServer, final DatagramPacket receivedPacket) {
        try {
            socketServer.receive(receivedPacket);
            return true;
        } catch (final IOException e) {
            System.err.println("I/O error occurs while receiving packet. Message: " + e.getMessage());
            return false;
        }
    }

    /**
     * Try sends packet to socket owner.
     *
     * @param socket         a socket to send from
     * @param dispatchPacket a packet to send
     */
    public static void send(final DatagramSocket socket, final DatagramPacket dispatchPacket) {
        try {
            socket.send(dispatchPacket);
        } catch (final IOException e) {
            System.err.println("I/O error occurs while sending packet. Message: " + e.getMessage());
        }
    }
}
