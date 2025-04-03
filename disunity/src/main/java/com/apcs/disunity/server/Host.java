package com.apcs.disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * 
 * Listens for incoming clients sockets and sends and recieves packets to/from clients.
 * 
 * @author Sharvil Phadke
 * 
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

    public static final int ID = 0;

    private int clientId = 0;

    public Host() throws IOException {
        this(DEFAULT_PORT);
    }
    
    public Host(int port) throws IOException {
        server = new ServerSocket();
        server.bind(new InetSocketAddress(Inet6Address.ofLiteral("::"), port));
        listenerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket socket = server.accept();
                    sockets.add(socket);
                    int id = ++clientId;
                    socket.getOutputStream().write(id);
                    PacketTransceiver clientTransceiver = new PacketTransceiver(socket.getInputStream(), socket.getOutputStream());
                    String clientIdentifier = getStringIdentifier(socket);
                    System.out.printf("[SERVER] Client at %s connected, creating handler\n", clientIdentifier);
                    ids.put(clientIdentifier, id);
                    clientTransceivers.put(id, clientTransceiver);
                    for (Consumer<Integer> action : onJoinActions) {
                        action.accept(id);
                    }
                } catch (IOException ioe) { }
            }
            System.out.println("[SERVER] Listener thread terminated");
        });
    }

    public void start() {
        listenerThread.start();
    }

    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public void close() throws IOException {
        listenerThread.interrupt();
        for (Socket socket : sockets) {
            socket.close();
            System.out.printf("[SERVER] Client %s handler socket closed\n", getStringIdentifier(socket));
        }
        server.close();
        System.out.println("[SERVER] Server socket closed");
    }

    public byte[] recieve(int id) {
        return clientTransceivers.get(id).recieve();
    }

    public void send(int id, byte[] bytes) {
        clientTransceivers.get(id).send(bytes);
    }

    public String getAddress() {
        return server.getInetAddress().getHostAddress();
    }

    public int getPort() {
        return server.getLocalPort();
    }

    public static String getStringIdentifier(Socket socket) {
        return String.format("%s:%d", socket.getInetAddress(), socket.getPort());
    }

    public PacketTransceiver getTransceiver(int id) {
        return clientTransceivers.get(id);
    }

    public int identify(String clientIdentifier) {
        return ids.get(clientIdentifier);
    }

    public void attachJoinAction(Consumer<Integer> onJoinAction) {
        onJoinActions.add(onJoinAction);
    }

    public void removeJoinAction(Consumer<Integer> onJoinAction) {
        onJoinActions.remove(onJoinAction);
    }

}
