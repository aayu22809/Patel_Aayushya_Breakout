package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

import static com.apcs.disunity.server.SyncableInt.decodeInt;
import static com.apcs.disunity.server.SyncableInt.encodeInt;

public class SyncableFloat extends SyncableWrapper<Float> {
    public SyncableFloat(float initialValue) {
        super(initialValue,SyncableFloat::encodeFloat,SyncableFloat::decodeFloat);
    }

    public static void encodeFloat(float val, OutputStream out) {
        encodeInt(Float.floatToRawIntBits(val),out);
    }
    public static float decodeFloat(InputStream in) {
        return Float.intBitsToFloat(decodeInt(in));
    }
}
