package com.apcs.disunity.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.apcs.disunity.server.SyncableInt.decodeInt;
import static com.apcs.disunity.server.SyncableInt.encodeInt;
import static java.lang.Byte.SIZE;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;

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
}
