package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

import static com.apcs.disunity.server.SyncableLong.*;

public class SyncableDouble extends SyncableWrapper<Double> {
    public SyncableDouble(double initialValue) {
        super(initialValue, SyncableDouble::encodeDouble, SyncableDouble::decodeDouble);
    }

    public static void encodeDouble(double val, OutputStream out) {
        encodeLong(Double.doubleToRawLongBits(val), out);
    }
    public static double decodeDouble(InputStream in) {
        return Double.longBitsToDouble(decodeLong(in));
    }
}
