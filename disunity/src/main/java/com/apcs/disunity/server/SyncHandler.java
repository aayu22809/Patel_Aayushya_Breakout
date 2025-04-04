package com.apcs.disunity.server;

import java.util.LinkedList;
import java.util.List;

public abstract class SyncHandler {

    private static SyncHandler instance; 
    { instance = this; }

    private final List<Synced> syncs = new LinkedList<>();

    public void register(Synced synced) {
        syncs.add(synced);
    }

    public static SyncHandler getInstance() {
        return instance;
    }

    ///  creates bundled packets from registered {@link Synced} objects.
    protected final byte[] poll(int recipient) {
        List<byte[]> subpackets = new LinkedList<>();
        for (Synced sync : syncs) {
            subpackets.add(sync.supply(recipient));
        }
        return Util.bundleSubpackets(subpackets);
    }

    /// applies received packets to registered {@link Synced} objects.
    protected final void distribute(int sender, byte[] data) {
        List<byte[]> subdata = Util.debundleSubpackets(data);
        for (int i = 0; i < syncs.size(); i++) {
            syncs.get(i).receive(sender, subdata.get(i));
        }
    }

    public abstract int getEndpointId();
}
