package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

import static com.apcs.disunity.server.Util.decodeBits;
import static com.apcs.disunity.server.Util.encodeBits;

public class SyncableBoolean extends SyncableWrapper<Boolean> {
    public SyncableBoolean(boolean initialValue) {
        super(initialValue, SyncableBoolean::encodeBoolean,SyncableBoolean::decodeBoolean);
    }

    public static void encodeBoolean(boolean val, OutputStream out) {
        encodeBits(val ? 1 : 0, 1, out);
    }
    public static boolean decodeBoolean(InputStream in) {
        long val = decodeBits(1,in);
        switch ((int)val) {
            case 1 -> { return true; }
            case 0 -> { return false; }
            default -> throw new IllegalArgumentException("value was neither 1 or 0. value: " + val);
        }
    }
}
