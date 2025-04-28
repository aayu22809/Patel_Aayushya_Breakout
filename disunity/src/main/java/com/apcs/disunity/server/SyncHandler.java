package com.apcs.disunity.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import static com.apcs.disunity.server.CODEC.decodeInt;

public abstract class SyncHandler implements Closeable {
    public static final int HOST_ID = 0;
    private static SyncHandler instance;

    {
        if (instance == null) {
            instance = this;
        } else {
            throw new SingletonViolationException("You can only make one instance of SyncHandler.");
        }
    }

    private final List<Object> syncs = new LinkedList<>();

    public void register(Object obj) {
        syncs.add(obj);
    }

    public static SyncHandler getInstance() {
        return instance;
    }

    protected final byte[] poll() {
        try {
            // this should only run on server,
            // and client should have its own protocol for sending inputs.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream packetStream = new ByteArrayOutputStream();
            for (Object sync : syncs) {
                CODEC.encodeObject(sync,packetStream);
                out.write(Util.pack(packetStream.toByteArray()));
                packetStream.reset();
            }
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected final void distribute(int sender, byte[] data) {
        // this should only run on client,
        // and server should have its own protocol for applying user input.
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            for (Object sync : syncs) {

                int size = decodeInt(in);
                byte[] nodePacket = new byte[size];
                int numBytes = in.read(nodePacket);
                if (numBytes == -1 && size != 0) throw new RuntimeException("packet was smaller than expected.");
                // MUST DO: implement proper client reconciliation
                // client ignores server packet overriding owning nodes
                // server ignores clients overriding unowned nodes
                try {
                    Field ownerField = sync.getClass().getField("owner");
                    int owner = (int) ownerField.get(sync);
                    if(owner == getEndpointId() || getEndpointId() == HOST_ID && sender != owner) continue;
                } catch (NoSuchFieldException nsfe) {}

                ByteArrayInputStream packetStream = new ByteArrayInputStream(nodePacket);
                CODEC.decodeObject(sync, packetStream);
            }
            if(in.available() != 0) throw new RuntimeException("reciever did not consume all contents of packet");
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean isClient() {
        return getEndpointId() == HOST_ID;
    }

    public abstract int getEndpointId();
    public abstract void start();
}