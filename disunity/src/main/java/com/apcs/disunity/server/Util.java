package com.apcs.disunity.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Byte.SIZE;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.apcs.disunity.server.SyncableInt.decodeInt;
import static com.apcs.disunity.server.SyncableInt.encodeInt;

/**
 * 
 * A utility class for converting data to and from byte arrays.
 * 
 * @author Sharvil Phadke
 * 
 */
public final class Util {

    public static final int PACKET_HEADER_SIZE = Integer.BYTES;

    public static final byte[] pack(byte[] bytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        encodeInt(bytes.length,out);
        try {
            out.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }

    public static final byte[] unpack(InputStream data) {
        try {
        int packetSize = decodeInt(data);
        byte[] packet = new byte[packetSize];
        int numRead = data.read(packet);
        if(numRead < packetSize) throw new RuntimeException("packet size larger than stream length");
        return packet;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static final Object[] flatten(Object o) {
        List<Object> objs = new LinkedList<>();
        if (o.getClass().isArray()) {
            objs.addAll(Arrays.asList((Object[]) o));
        } else {
            objs.add(o);
        }
        return objs.toArray();
    }

    public static void encodeBits(long bits, int byteSize, OutputStream out) {
        for (int i = 0; i < byteSize; i++, bits >>= SIZE) {
            try {
                out.write((byte) (bits & 0xFF));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static long decodeBits(int byteSize, InputStream in) {
        try {
            long val = 0;
            for (int i = 0; i < byteSize; i++) {
                long byt = in.read();
                if(byt < 0) throw new RuntimeException("stream shorter than required size");
                val |= byt << (i * SIZE);
            }
            return val;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Map<Class<?>,Function<InputStream,?>> decoders = new HashMap<>();
    static {
        decoders.put(Boolean.class, SyncableBoolean::decodeBoolean);
        decoders.put(Byte.class, SyncableByte::decodeByte);
        decoders.put(Character.class, SyncableChar::decodeChar);
        decoders.put(Double.class, SyncableDouble::decodeDouble);
        decoders.put(Float.class, SyncableFloat::decodeFloat);
        decoders.put(Integer.class, SyncableInt::decodeInt);
        decoders.put(Long.class, SyncableLong::decodeLong);
        decoders.put(Short.class, SyncableShort::decodeShort);
    }

    public static final Map<Class<?>,BiConsumer<Object, OutputStream>> encoders = new HashMap<>();
    static {
        encoders.put(Boolean.class, (val, in) -> SyncableBoolean.encodeBoolean((boolean)val,in));
        encoders.put(Byte.class, (val, in) -> SyncableByte.encodeByte((byte)val,in));
        encoders.put(Character.class, (val, in) -> SyncableChar.encodeChar((char)val,in));
        encoders.put(Double.class, (val, in) -> SyncableDouble.encodeDouble((double)val,in));
        encoders.put(Float.class, (val, in) -> SyncableFloat.encodeFloat((float)val,in));
        encoders.put(Integer.class, (val, in) -> SyncableInt.encodeInt((int)val,in));
        encoders.put(Long.class, (val, in) -> SyncableLong.encodeLong((long)val,in));
        encoders.put(Short.class, (val, in) -> SyncableShort.encodeShort((short)val,in));
    }

    public static final Object decodePrimitive(Class<?> type, InputStream in) {
        for (Map.Entry<Class<?>,Function<InputStream,?>> entry : decoders.entrySet()) {
            if (type.equals(entry.getKey())) return entry.getValue().apply(in);
        }
        throw new RuntimeException("Invalid Type: "+type.getName());
    }

    public static final void encodePrimitive(Object val, OutputStream out) {
        for (Map.Entry<Class<?>,BiConsumer<Object, OutputStream>> entry : encoders.entrySet()) {
            if (val.getClass().equals(entry.getKey())) {
                entry.getValue().accept(val, out);
                return;
            }
        }
        throw new RuntimeException("Invalid Type: "+val.getClass().getName());
    }

}
