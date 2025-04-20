package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

public class SyncableWrapper<T> implements Syncable<SyncableWrapper<T>> {
    private final Encoder<T> encoder;
    private final Decoder<T> decoder;
    private T value;
    public SyncableWrapper(T value, Encoder<T> encoder, Decoder<T> decoder) {
        this.value = value;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public T value() {return value;}
    public void setValue(T value) {this.value = value;};

    @Override
    public void supply(int recipient, OutputStream packetOut) {
        encoder.encode(value, packetOut);
    }

    @Override
    public SyncableWrapper<T> receive(int sender, InputStream packetIn) {
        value = decoder.decode(packetIn);
        return this;
    }
}
