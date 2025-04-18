package com.apcs.disunity.server;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.apcs.disunity.annotations.SyncedField;

public abstract class SyncHandler {
    private static SyncHandler instance;
    { instance = this; }

    private final List<Data> syncs = new LinkedList<>();

    public void register(Object o) {
        List<Field> syncedFields = new LinkedList<>(Arrays.asList(o.getClass().getDeclaredFields()));
        syncedFields.removeIf((f) -> f.isAnnotationPresent(SyncedField.class));
        List<Data> syncedData = Data.of(o,syncedFields);
        syncs.addAll(syncedData);
    }

    public void finalizeSyncs() {

    }

    public static SyncHandler getInstance() {
        return instance;
    }

    ///  creates bundled packets from registered {@link Syncable} objects.
    protected final byte[] poll(int recipient) {
        List<byte[]> subpackets = new LinkedList<>();
        for (Data sync : syncs) {
            subpackets.add(Util.getBytes(sync.get()));
        }
        return Util.bundleSubpackets(subpackets);
    }

    /// applies received packets to registered {@link Syncable} objects.
    protected final void distribute(int sender, byte[] data) {
        List<byte[]> subdata = Util.debundleSubpackets(data);
        for (int i = 0; i < syncs.size(); i++) {
            if (syncs.get(i).get()
            syncs.get(i).receive(sender, subdata.get(i));
        }
    }

    public abstract int getEndpointId();

    private static class Data {

        private final Field f;
        private final Object o;

        public Data(Object o, Field f) {
            this.o = o;
            this.f = f;
        }

        public Field getField() {
            return f;
        }

        public Object getObject() {
            return o;
        }

        public byte[] get() {
            try {
                return Util.getBytes(f.get(o));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                return null;
            }
        }

        public void set(int sender, byte[] data) {
            try {
                if (f.get(o) instanceof Syncable s) { s.receive(sender, data); }
                else { Util.getPrimativeData(data, o.getClass()); }
            } catch (IllegalArgumentException | IllegalAccessException e) { }
        }

        public static List<Data> of(Object o, List<Field> fields) {
            List<Data> output = new ArrayList<>(fields.size());
            for (Field field : fields) {
                output.add(new Data(o, field));
            }
            return output;
        }

    }
}
