package com.apcs.disunity.server;

import java.io.Closeable;
import java.io.IOException;

public class ClientSideSyncHandler extends SyncHandler implements Closeable {

    private final PacketTransceiver transceiver;
    private final Thread recieverThread;
    private final Thread senderThread;
    private final Client client;

    public ClientSideSyncHandler(String host, int port) throws IOException {
        client = new Client(host, port);
        transceiver = client.getTransceiver();

        recieverThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                distribute(Syncable.HOST, transceiver.recieve());
            }
        });

        senderThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                transceiver.send(poll(client.id()));
            }
        });
    }

    public void start() {
        senderThread.start();
        recieverThread.start();
    }


    @Override
    public void close() throws IOException {
        client.close();
        recieverThread.interrupt();
        senderThread.interrupt();
    }

    @Override
    public int getEndpointId() {
        return client.id();
    }
    
}
