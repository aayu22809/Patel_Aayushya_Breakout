package com.apcs.disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class HostSideSyncHandler extends SyncHandler implements Closeable {

    private final Host host;
    private final List<Thread> threads = new LinkedList<>();

    public HostSideSyncHandler() throws IOException {
        host = new Host();

        host.attachJoinAction((id) -> {
            PacketTransceiver transceiver = host.getTransceiver(id);

            Thread recieverThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    distribute(id, transceiver.recieve());
                }
            });

            Thread senderThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    transceiver.send(poll(id));
                }
            });

            threads.add(recieverThread);
            threads.add(senderThread);

            recieverThread.start();
            senderThread.start();

        });
    }

    @Override
    public void close() throws IOException {
        for (Thread t : threads) {
            t.interrupt();
        }
    }


}
