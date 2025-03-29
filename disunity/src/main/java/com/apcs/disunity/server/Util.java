package com.apcs.disunity.server;

import static java.lang.Byte.SIZE;

public class Util {

    public static byte[] getBytes(int val) {
        byte[] bytes = new byte[Integer.BYTES];
        for (int i = 0; i < Integer.BYTES; i++) {
            bytes[i] = (byte) ((val & (0b11111111 << (i * SIZE))) >> (i * SIZE));
        }
        return bytes;
    }

    public static int getInt(byte[] data) {
        int val = 0;
        for (int i = 0; i < Integer.BYTES; i++) {
            val += data[i] << (i * SIZE);
        }
        return val;
    }

}
