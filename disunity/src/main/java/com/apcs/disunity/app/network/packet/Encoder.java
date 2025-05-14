package com.apcs.disunity.app.network.packet;

import java.io.OutputStream;

/// encoder that encodes the value a packet content
public interface Encoder<T> {
    /**
     * encodes the given value
     * 
     * @param value value to encode
     * @param out   stream to write the encoded packet content
     */
    void encode(T value, OutputStream out);
}
