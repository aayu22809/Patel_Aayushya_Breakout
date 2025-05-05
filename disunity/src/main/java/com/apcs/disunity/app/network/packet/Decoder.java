package com.apcs.disunity.app.network.packet;

import java.io.InputStream;

public interface Decoder<T> {
    T decode(T self, InputStream in);
}
