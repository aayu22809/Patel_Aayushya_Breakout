package com.apcs.disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Host implements Closeable {

    public static final int DEFAULT_PORT = 3000;
    public static final long DELAY_MS = 20;

    private final ServerSocket server;
    private final Map<String, PacketTransceiver> clientTransceivers = new ConcurrentHashMap<>();
    private final Queue<Socket> sockets = new ConcurrentLinkedQueue<>();
    private final Thread listenerThread;

    public Host() throws IOException {
        this(DEFAULT_PORT);
    }
    
    public Host(int port) throws IOException {
        server = new ServerSocket(port);
        listenerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket socket = server.accept();
                    sockets.add(socket);
                    PacketTransceiver clientTransceiver = new PacketTransceiver(socket.getInputStream(), socket.getOutputStream());
                    String clientIdentifier = id(socket);
                    System.out.printf("[SERVER] Client at %s connected, creating handler\n", clientIdentifier);
                    clientTransceivers.put(clientIdentifier, clientTransceiver);
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
            System.out.printf("[SERVER] Client %s handler socket closed\n", id(socket));
        }
        server.close();
        System.out.println("[SERVER] Server socket closed");
    }

    public byte[] recieve(String clientIdentifier) {
        return clientTransceivers.get(clientIdentifier).recieve();
    }

    public void send(String clientIdentifier, byte[] bytes) {
        clientTransceivers.get(clientIdentifier).send(bytes);
    }

    public String getAddress() {
        return server.getInetAddress().getHostAddress();
    }

    public int getPort() {
        return server.getLocalPort();
    }

    public String id(Socket socket) {
        return String.format("%s:%d", socket.getInetAddress(), socket.getPort());
    }
}
