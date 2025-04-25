package com.apcs.disunity.server;

import java.io.OutputStream;

public interface Encoder<T> {
    void encode(T value, OutputStream out);
}
