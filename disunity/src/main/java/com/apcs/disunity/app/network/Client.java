package com.apcs.disunity.app.network;

import com.apcs.disunity.app.network.packet.PacketTransceiver;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Sends and recieves packets from/to a socket at a known ip and port.
 * 
 * @author Sharvil Phadke
 */
public class Client implements Closeable {

    private final int id;

    private final Socket socket;
    private final PacketTransceiver transceiver;
    private final String identifier;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        id = socket.getInputStream().read();
        transceiver = new PacketTransceiver(socket.getInputStream(), socket.getOutputStream());
        identifier = String.format("%s:%d", socket.getLocalAddress(), socket.getLocalPort());
    }

    public void send(byte[] bytes) { transceiver.send(bytes); }

    public byte[] recieve() { return transceiver.recieve(); }

    @Override
    public void close() throws IOException { socket.close(); }

    public String getStringIdentifier() { return identifier; }

    public byte id() { return (byte) id; }

    public PacketTransceiver getTransceiver() { return transceiver; }

}