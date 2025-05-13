package com.apcs.disunity.app.network.packet;

import java.io.InputStream;
import java.io.OutputStream;

/// indicates {@link SyncHandler} that this object has a codec methods to be used
/// instead of the default object codec
public interface SelfCodec<T> {
    /**
     * mutates itself according to the packet
     * @param in stream containing packet content
     * @return itself
     */
    T decode(InputStream in);

    /**
     * encodes itself according to its state
     * @param out stream to write the packet content
     */
    void encode(OutputStream out);
}
