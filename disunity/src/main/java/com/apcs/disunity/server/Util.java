package com.apcs.disunity.server;

import static java.lang.Byte.SIZE;

/**
 * 
 * A utility class for converting data to and from byte arrays.
 * 
 * @author Sharvil Phadke
 * 
 */
public class Util {

    public static byte[] getBytes(int val) {
        byte[] bytes = new byte[Integer.BYTES];
        for (int i = 0; i < Integer.BYTES; i++) {
            bytes[i] = (byte) ((val & (0XFF << (i * SIZE))) >> (i * SIZE));
        }
        return bytes;
    }

    public static int getInt(byte[] data) {
        int val = 0;
        for (int i = 0; i < Integer.BYTES; i++) {
            val |= (data[i] & 0xFF) << (i * SIZE);
        }
        return val;
    }

    public static byte[] getBytes(long val) {
        byte[] bytes = new byte[Long.BYTES];
        for (int i = 0; i < Long.BYTES; i++) {
            bytes[i] = (byte) ((val & (0XFFL << (i * SIZE))) >> (i * SIZE));
        }
        return bytes;
    }

    public static long getLong(byte[] data) {
        long val = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            val |= (data[i] & 0XFFL) << (i * SIZE);
        }
        return val;
    }

}
