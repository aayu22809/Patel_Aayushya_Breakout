package com.apcs.disunity.app.network.packet;

import java.io.InputStream;

/// represents a decoder that decodes the packet content and updates the last value to decoded value.
public interface Decoder<T> {
    /**
     * decodes the packet content
     * @param self current value to be updated
     * @param in incoming packet content
     * @return value decoded from packet, or self that is mutated according to packet.
     */
    T decode(T self, InputStream in);
}
