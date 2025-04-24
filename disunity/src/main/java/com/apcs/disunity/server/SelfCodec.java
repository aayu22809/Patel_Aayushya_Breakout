package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface SelfCodec<T> {
    T decode(InputStream in);
    void encode(OutputStream out);
}
