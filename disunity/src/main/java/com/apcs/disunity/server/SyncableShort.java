package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

import static com.apcs.disunity.server.Util.decodeBits;
import static com.apcs.disunity.server.Util.encodeBits;

public class SyncableShort extends SyncableWrapper<Short> {
    public SyncableShort(short inittialValue) {
        super(inittialValue, SyncableShort::encodeShort, SyncableShort::decodeShort);
    }

    public static void encodeShort(short val, OutputStream out) {
        encodeBits(val,Short.BYTES,out);
    }
    public static short decodeShort(InputStream in) {
        return (byte) decodeBits(Short.BYTES,in);
    }
}
