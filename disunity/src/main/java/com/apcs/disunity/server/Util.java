package com.apcs.disunity.server;

import java.io.IOException;
import java.io.InputStream;
import static java.lang.Byte.SIZE;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        byte[] packet = new byte[PACKET_HEADER_SIZE + bytes.length];
        byte[] header = Util.getBytes(bytes.length);
        System.arraycopy(header, 0, packet, 0, PACKET_HEADER_SIZE);
        System.arraycopy(bytes, 0, packet, PACKET_HEADER_SIZE, bytes.length);
        return packet;
    }

    public static final byte[] unpack(byte[] data, int headerLoc) {
        int size = getInt(data, headerLoc);
        byte[] output = new byte[size];
        System.arraycopy(data, headerLoc + Integer.BYTES, output, 0, size);
        return output;
    }

    public static final byte[] bundleSubpackets(List<byte[]> subpackets) {
        int length = 0;
        for (byte[] subpacket : subpackets) {
            length += subpacket.length + PACKET_HEADER_SIZE;
        }
        byte[] output = new byte[length];
        int loc = 0;
        for (byte[] subpacket : subpackets) {
            System.arraycopy(pack(subpacket), 0, output, loc, subpacket.length);
            loc += subpacket.length + PACKET_HEADER_SIZE;
        }
        return output;
    }

    public static final List<byte[]> debundleSubpackets(byte[] data) {
        int loc = 0;
        List<byte[]> output = new ArrayList<>();
        while (loc < data.length) {
            byte[] subdata = unpack(data, loc);
            output.add(subdata);
            loc += subdata.length + PACKET_HEADER_SIZE;
        }
        return output;
    }

    public static final byte[] unpack(byte[] data) {
        return unpack(data, 0);
    }

    public static final byte[] unpack(InputStream data) {
        try {
        byte[] header = data.readNBytes(Integer.BYTES);
        int packetSize = getInt(header);
        byte[] packet = data.readNBytes(packetSize);
        return packet;
        } catch (IOException ioe) { return null; }
    }

    public static final byte[] getBytes(int val) {
        byte[] bytes = new byte[Integer.BYTES];
        for (int i = 0; i < Integer.BYTES; i++) {
            bytes[i] = (byte) ((val & (0XFF << (i * SIZE))) >> (i * SIZE));
        }
        return bytes;
    }

    public static final int getInt(byte[] data, int loc) {
        int val = 0;
        for (int i = 0; i < Integer.BYTES; i++) {
            val |= (data[i + loc] & 0xFF) << (i * SIZE);
        }
        return val;
    }

    public static final int getInt(byte[] data) {
        return getInt(data, 0);
    }

    public static final byte[] getBytes(long val) {
        byte[] bytes = new byte[Long.BYTES];
        for (int i = 0; i < Long.BYTES; i++) {
            bytes[i] = (byte) ((val & (0XFFL << (i * SIZE))) >> (i * SIZE));
        }
        return bytes;
    }

    public static final long getLong(byte[] data, int loc) {
        long val = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            val |= (data[i + loc] & 0XFFL) << (i * SIZE);
        }
        return val;
    }

    public static final long getLong(byte[] data) {
        return getLong(data, 0);
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

}
