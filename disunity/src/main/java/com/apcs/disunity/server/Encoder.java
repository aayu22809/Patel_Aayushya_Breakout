package com.apcs.disunity.server;

import java.io.OutputStream;

public interface Encoder<T> {
    /// encodes {@param value} to byte array, and
    /// outputs it to {@param out}
    void encode(T value, OutputStream out);
}
