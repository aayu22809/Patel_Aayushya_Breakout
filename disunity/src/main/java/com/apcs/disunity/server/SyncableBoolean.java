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
        if(val == 1) return true;
        else if (val == 0) return false;
        else throw new IllegalArgumentException("value was neither 1 or 0. value: " + val);
    }
}
