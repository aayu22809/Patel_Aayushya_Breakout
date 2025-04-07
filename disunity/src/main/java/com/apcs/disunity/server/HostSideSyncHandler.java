package com.apcs.disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.apcs.disunity.server.Util.forever;

public class HostSideSyncHandler extends SyncHandler implements Closeable {

    private final Host host;
    private final List<Thread> threads = new LinkedList<>();

    public HostSideSyncHandler() throws IOException {
        host = new Host();

        host.attachJoinAction((id) -> {
            PacketTransceiver transceiver = host.getTransceiver(id);

            Thread recieverThread = new Thread(() -> {
                forever(() -> distribute(id, transceiver.recieve()), Synced.PPMS);
            });

            Thread senderThread = new Thread(() -> {
                forever(() -> transceiver.send(poll(Synced.HOST)), Synced.PPMS);
            });

            threads.add(recieverThread);
            threads.add(senderThread);

            recieverThread.start();
            senderThread.start();

        });

    }

    public void start() {
        host.start();
    }

    @Override
    public void close() throws IOException {
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    @Override
    public int getEndpointId() {
        return Synced.HOST;
    }

}
