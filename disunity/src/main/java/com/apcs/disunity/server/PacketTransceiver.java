package com.apcs.disunity.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * A helper class that sends and recieves packets with a size header and body over an input and output stream.
 * 
 * @author Sharvil Phadke
 * 
 */
public class PacketTransceiver {
    
    private final OutputStream out;
    private final InputStream in;

    public PacketTransceiver(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void send(byte[] bytes) {
        try {
            byte[] packet = Util.pack(bytes);
            out.write(packet);
        } catch (IOException e) { }
    }

    public byte[] recieve() {
        return Util.unpack(in);
    }

}
