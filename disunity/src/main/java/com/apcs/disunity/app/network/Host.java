package com.apcs.disunity.app.network;

import com.apcs.disunity.app.network.packet.PacketTransceiver;

import java.io.Closeable;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * Listens for incoming clients sockets and sends and recieves packets to/from
 * clients.
 * 
 * @author Sharvil Phadke
 */
public class Host implements Closeable {

    public static final int DEFAULT_PORT = 3000;
    public static final long DELAY_MS = 20;

    private final ServerSocket server;
    private final Map<Integer, PacketTransceiver> clientTransceivers = new ConcurrentHashMap<>();
    private final Map<String, Integer> ids = new ConcurrentHashMap<>();
    private final Queue<Socket> sockets = new ConcurrentLinkedQueue<>();
    private final Thread listenerThread;
    private final List<Consumer<Integer>> onJoinActions = new LinkedList<>();

    private int clientId = 0;

    public Host() throws IOException { this(0); }

    public Host(int port) throws IOException {
        server = new ServerSocket(0);
        listenerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket socket = server.accept();
                    sockets.add(socket);
                    int id = ++clientId;
                    socket.getOutputStream().write(id);
                    PacketTransceiver clientTransceiver = new PacketTransceiver(socket.getInputStream(),
                            socket.getOutputStream());
                    String clientIdentifier = getStringIdentifier(socket);
                    ids.put(clientIdentifier, id);
                    clientTransceivers.put(id, clientTransceiver);
                    for (Consumer<Integer> action : onJoinActions) {
                        action.accept(id);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }

    public void start() { listenerThread.start(); }

    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public void close() throws IOException {
        listenerThread.interrupt();
        for (Socket socket : sockets) {
            socket.close();
        }
        server.close();
    }

    public byte[] recieve(int id) { return clientTransceivers.get(id).recieve(); }

    public void send(int id, byte[] bytes) { clientTransceivers.get(id).send(bytes); }

    public String getAddress() {
        Enumeration<NetworkInterface> interfaces;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface nInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = nInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet4Address && !address.isAnyLocalAddress()) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (IOException e) {
        }
        StringBuilder b = new StringBuilder();
        for (byte by : server.getInetAddress().getAddress())
            b.append(by).append('.');
        return b.toString().substring(0, b.length() - 1);
    }

    public int getPort() { return server.getLocalPort(); }

    public static String getStringIdentifier(Socket socket) {
        return String.format("%s:%d", socket.getInetAddress(), socket.getPort());
    }

    public PacketTransceiver getTransceiver(int id) { return clientTransceivers.get(id); }

    public int identify(String clientIdentifier) { return ids.get(clientIdentifier); }

    public void attachJoinAction(Consumer<Integer> onJoinAction) { onJoinActions.add(onJoinAction); }

    public void removeJoinAction(Consumer<Integer> onJoinAction) { onJoinActions.remove(onJoinAction); }

}
