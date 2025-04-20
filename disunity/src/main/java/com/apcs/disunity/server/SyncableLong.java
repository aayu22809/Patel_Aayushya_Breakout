package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

public class SyncableLong extends SyncableWrapper<Long> {
    public SyncableLong(long initialValue) {
        super(initialValue, SyncableLong::encodeLong, SyncableLong::decodeLong);
    }

    public static void encodeLong(long val, OutputStream out) {
        Util.encodeBits(val,Long.BYTES,out);}

    public static long decodeLong(InputStream in) {return Util.decodeBits(Long.BYTES,in);}
}
