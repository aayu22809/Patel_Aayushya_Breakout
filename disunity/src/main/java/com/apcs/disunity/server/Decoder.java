package com.apcs.disunity.server;

import java.io.InputStream;

public interface Decoder<T> {
    T decode(T self, InputStream in);
}
