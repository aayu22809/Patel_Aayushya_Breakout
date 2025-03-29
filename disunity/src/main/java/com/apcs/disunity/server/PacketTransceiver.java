package com.apcs.disunity.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

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
            System.out.println("Making packet for bytes: "+Arrays.toString(bytes));
            // for convenience header bytes are sent reverse order
            byte[] packet = new byte[Integer.BYTES + bytes.length];
            byte[] header = Util.getBytes(bytes.length);
            System.arraycopy(header, 0, packet, 0, Integer.BYTES);
            System.arraycopy(bytes, 0, packet, Integer.BYTES, bytes.length);
            System.out.println("Sending packet: "+Arrays.toString(packet));
            out.write(packet);
            System.out.println("Sent");
        } catch (IOException e) { }
    }

    public byte[] recieve() {
        try {
            System.out.printf("expecting header of size %d...\n", Integer.BYTES);
            byte[] header = in.readNBytes(Integer.BYTES);
            System.out.println("Recieved header: "+Arrays.toString(header));
            int packetSize = Util.getInt(header);
            System.out.printf("expecting packet of size %d...\n", packetSize);
            byte[] packet = in.readNBytes(packetSize);
            System.out.println("Recieved packet: "+Arrays.toString(packet));
            return packet;
        } catch (IOException e) {
            return null;
        }
    }
}
