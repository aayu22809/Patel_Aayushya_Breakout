package com.apcs.disunity.app.network.packet;

import java.io.OutputStream;

public interface Encoder<T> {
    void encode(T value, OutputStream out);
}
