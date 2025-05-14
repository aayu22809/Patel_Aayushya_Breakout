package com.apcs.disunity.app.network;

import com.apcs.disunity.game.GameThread;
import com.apcs.disunity.app.network.packet.PacketTransceiver;
import com.apcs.disunity.app.network.packet.SyncHandler;

import java.io.Closeable;
import java.io.IOException;

/// subclass of SyncHandler, which specializes in sending changes in current
/// runtime and
/// applying changes made in other runtimes
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
                distribute(HOST_ID, transceiver.recieve());
            }
        });

        senderThread = new GameThread(() -> {
        }, () -> transceiver.send(poll()));
    }

    @Override
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
    public int getEndpointId() { return client.id(); }

}
