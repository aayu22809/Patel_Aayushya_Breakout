package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

import static com.apcs.disunity.server.Util.decodeBits;
import static com.apcs.disunity.server.Util.encodeBits;

public class SyncableByte extends SyncableWrapper<Byte> {
    public SyncableByte(byte initalValue) {
        super(initalValue, SyncableByte::encodeByte, SyncableByte::decodeByte);
    }

    public static void encodeByte(byte val, OutputStream out) {
        encodeBits(val,Byte.BYTES,out);
    }
    public static byte decodeByte(InputStream in) {
        return (byte) decodeBits(Byte.BYTES,in);
    }
}
