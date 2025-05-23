package com.apcs.disunity.app.network;

import com.apcs.disunity.app.network.packet.PacketTransceiver;
import com.apcs.disunity.app.network.packet.SyncHandler;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/// subclass of SyncHandler, which specializes in notifying client what their
/// states
/// should be
public class HostSideSyncHandler extends SyncHandler implements Closeable {

    private final Host host;
    private final List<TranscieverThread> threads = new LinkedList<>();

    public HostSideSyncHandler() throws IOException {
        host = new Host();
        host.attachJoinAction((id) -> {
            PacketTransceiver transceiver = host.getTransceiver(id);

            TranscieverThread thread = new TranscieverThread(transceiver, id);
            threads.add(thread);
            thread.start();

        });
    }

    private class TranscieverThread extends Thread {
        public final PacketTransceiver TRANCIEVER;

        public TranscieverThread(PacketTransceiver tr, int clientId) {
            super(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    distribute(clientId, tr.recieve());
                    for (TranscieverThread tt : threads) {
                        // prevent sending back to packet source
                        if (tt != Thread.currentThread()) {
                            tt.TRANCIEVER.send(poll());
                        }
                    }
                }
            });
            TRANCIEVER = tr;
        }
    }

    @Override
    public void start() { host.start(); }

    @Override
    public void close() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    @Override
    public int getEndpointId() { return HOST_ID; }

    public int getPort() { return host.getPort(); }

    public String getAddress() { return host.getAddress(); }
}