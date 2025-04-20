package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;


public class SyncableInt extends SyncableWrapper<Integer> {
    public SyncableInt(int initialValue) {
        super(initialValue, SyncableInt::encodeInt, SyncableInt::decodeInt);
    }

    public static void encodeInt(int val, OutputStream out) {
        Util.encodeBits(val,Integer.BYTES,out);
    }
    public static int decodeInt(InputStream in) {
        return (int) Util.decodeBits(Integer.BYTES,in);
    }
}
