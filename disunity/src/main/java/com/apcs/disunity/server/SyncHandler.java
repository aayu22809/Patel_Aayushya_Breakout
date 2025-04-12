package com.apcs.disunity.server;

import com.apcs.disunity.annotations.SyncedField;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public abstract class SyncHandler {

    private static SyncHandler instance; 
    { instance = this; }

    private final List<Object> syncs = new LinkedList<>();

    public void register(Object obj) {
        syncs.add(obj);
    }

    public static SyncHandler getInstance() {
        return instance;
    }

    ///  creates bundled packets from registered {@link Syncable} objects.
    protected final byte[] poll(int recipient) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream packetStream = new ByteArrayOutputStream();
        for (Object sync : syncs) {
            for(Field field: sync.getClass().getFields()) {
                if(field.getAnnotation(SyncedField.class) == null) continue;
                try {
                    ((Syncable<?>)field.get(sync)).supply(recipient, packetStream);
                    out.write(Util.pack(packetStream.toByteArray()));
                    out.reset();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
            }
        }
        return Util.pack(out.toByteArray());
    }

    /// applies received packets to registered {@link Syncable} objects.
    protected final void distribute(int sender, byte[] data) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            for (Object sync : syncs) {
                for(Field field: sync.getClass().getFields()) {
                    if(field.getAnnotation(SyncedField.class) == null) continue;

                    int size = Util.getInt(in);
                    byte[] subpkt = new byte[size];
                    int numBytes = in.read(subpkt);
                    if(numBytes == -1) throw new RuntimeException("packet was smaller than expected.");

                    field.set(sync,((Syncable<?>) field.get(sync)).receive(sender,new ByteArrayInputStream(subpkt)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
    }

    public abstract int getEndpointId();
}
