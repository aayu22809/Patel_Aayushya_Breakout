package com.apcs.disunity.server;

import java.io.InputStream;

public interface Decoder<T> {
    /// decodes incoming stream to value of type T.
    /// caller must ensure that data put into this method
    /// contains properly encoded stream.
    T decode(InputStream in);
}
