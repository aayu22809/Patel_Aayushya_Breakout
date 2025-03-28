package com.apcs.disunity.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Byte.SIZE;
import static java.lang.Integer.BYTES;
import java.util.Arrays;


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
            byte[] packet = new byte[BYTES + bytes.length];
            for (int i = 0; i < BYTES; i++) {
                packet[i] = (byte) ((bytes.length & (0b11111111 << (i * SIZE))) >> (i * SIZE));
            }
            System.arraycopy(bytes, 0, packet, BYTES, bytes.length);
            System.out.println("Sending packet: "+Arrays.toString(packet));
            out.write(packet);
            System.out.println("Sent");
        } catch (IOException e) { }
    }

    public byte[] recieve() {
        try {
            System.out.printf("expecting header of size %d...\n", BYTES);
            byte[] header = in.readNBytes(BYTES);
            System.out.println("Recieved header: "+Arrays.toString(header));
            int packetSize = 0;
            for (int i = 0; i < BYTES; i++) {
                packetSize += header[i] << (i * SIZE);
            }
            System.out.printf("expecting packet of size %d...\n", packetSize);
            byte[] packet = in.readNBytes(packetSize);
            System.out.println("Recieved packet: "+Arrays.toString(packet));
            return packet;
        } catch (IOException e) {
            return null;
        }
    }
}
