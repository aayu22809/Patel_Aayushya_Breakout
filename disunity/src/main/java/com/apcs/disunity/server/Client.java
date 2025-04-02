package com.apcs.disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * 
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
        System.out.println("[CLIENT] Connected to server. My identifier is: "+identifier);
    }

    public void send(byte[] bytes) {
        transceiver.send(bytes);
    }

    public byte[] recieve() {
        return transceiver.recieve();
    }

    @Override
    public void close() throws IOException {
        socket.close();
        System.out.println("[CLIENT] Socket closed");
    }  

    public String identify() {
        return identifier;
    }

    public byte id() {
        return (byte) id;
    }
      
}

class Base
{

    public void methodOne()
    {
        System.out.print("A");
        methodTwo();
    }

    public void methodTwo()
    {
        System.out.print("B");
    }
}

class Derived extends Base
{
    public static void main(String[] args) {
        Base b = new Derived();
        b.methodOne();
    }

    public void methodOne()
    {
        super.methodOne();
        System.out.print("C");
    }

    public void methodTwo()
    {
        super.methodTwo();
        System.out.print("D");
    }
}