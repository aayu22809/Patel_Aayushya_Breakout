package com.apcs.disunity.server;

import java.io.IOException;
import java.io.InputStream;
import static java.lang.Byte.SIZE;

/**
 * 
 * A utility class for converting data to and from byte arrays.
 * 
 * @author Sharvil Phadke
 * 
 */
public class Util {

    public static byte[] pack(byte[] bytes) {
        byte[] packet = new byte[Integer.BYTES + bytes.length];
        byte[] header = Util.getBytes(bytes.length);
        System.arraycopy(header, 0, packet, 0, Integer.BYTES);
        System.arraycopy(bytes, 0, packet, Integer.BYTES, bytes.length);
        return packet;
    }

    public static byte[] unpack(byte[] data, int headerLoc) {
        int size = getInt(data, headerLoc);
        byte[] output = new byte[size];
        System.arraycopy(data, headerLoc + Integer.BYTES, output, 0, size);
        return output;
    }

    public static byte[] unpack(byte[] data) {
        return unpack(data, 0);
    }

    public static byte[] unpack(InputStream data) {
        try {
        byte[] header = data.readNBytes(Integer.BYTES);
        int packetSize = getInt(header);
        byte[] packet = data.readNBytes(packetSize);
        return packet;
        } catch (IOException ioe) { return null; }
    }

    public static byte[] getBytes(int val) {
        byte[] bytes = new byte[Integer.BYTES];
        for (int i = 0; i < Integer.BYTES; i++) {
            bytes[i] = (byte) ((val & (0XFF << (i * SIZE))) >> (i * SIZE));
        }
        return bytes;
    }

    public static int getInt(byte[] data, int loc) {
        int val = 0;
        for (int i = 0; i < Integer.BYTES; i++) {
            val |= (data[i + loc] & 0xFF) << (i * SIZE);
        }
        return val;
    }

    public static int getInt(byte[] data) {
        return getInt(data, 0);
    }

    public static byte[] getBytes(long val) {
        byte[] bytes = new byte[Long.BYTES];
        for (int i = 0; i < Long.BYTES; i++) {
            bytes[i] = (byte) ((val & (0XFFL << (i * SIZE))) >> (i * SIZE));
        }
        return bytes;
    }

    public static long getLong(byte[] data, int loc) {
        long val = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            val |= (data[i + loc] & 0XFFL) << (i * SIZE);
        }
        return val;
    }

    public static long getLong(byte[] data) {
        return getLong(data, 0);
    }

}
