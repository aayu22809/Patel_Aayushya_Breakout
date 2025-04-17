package com.apcs.disunity.server;

import com.apcs.disunity.annotations.SyncedField;
import com.apcs.disunity.nodes.Body;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public abstract class SyncHandler {

    private static SyncHandler instance;

    {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("You can only make one instance of SyncHandler.");
        }
    }

    private final List<Object> syncs = new LinkedList<>();

    public void register(Object obj) {
        syncs.add(obj);
    }

    public static SyncHandler getInstance() {
        return instance;
    }

    ///  creates bundled packets from registered {@link Syncable} objects.
    protected final byte[] poll(int recipient) {
        try {
            // this should only run on server,
            // and client should have its own protocol for sending inputs.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream packetStream = new ByteArrayOutputStream();
            for (Object sync : syncs) {
                for (Field field : getSyncedFields(sync.getClass()).toList()) {
                    ((Syncable<?>) field.get(sync)).supply(recipient, packetStream);
                    out.write(Util.pack(packetStream.toByteArray()));
                    packetStream.reset();
                }
            }
            return out.toByteArray();
        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /// applies received packets to registered {@link Syncable} objects.
    protected final void distribute(int sender, byte[] data) {
        // this should only run on client,
        // and server should have its own protocol for applying user input.
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            for (Object sync : syncs) {
                for (Field field : getSyncedFields(sync.getClass()).toList()) {
                    int size = Util.getInt(in);
                    byte[] subpkt = new byte[size];
                    int numBytes = in.read(subpkt);
                    if (numBytes == -1) throw new RuntimeException("packet was smaller than expected.");

                    // MUST DO: implement proper client reconciliation
                    if (sync instanceof Body body)
                        if(body.clientId == getEndpointId() ||
                           getEndpointId() == 0 && sender != body.clientId) continue;
                    ByteArrayInputStream packetStream = new ByteArrayInputStream(subpkt);
                    field.set(sync, ((Syncable<?>) field.get(sync)).receive(sender, packetStream));
                    packetStream.close();
                }
            }
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract int getEndpointId();

    private static Stream<Field> getSyncedFields(Class<?> cls) {
        if (cls == null) return Stream.empty();
        return Stream.concat(
            Arrays.stream(cls.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(SyncedField.class))
                .peek(field -> field.setAccessible(true)),
            getSyncedFields(cls.getSuperclass())
        );
    }
}
