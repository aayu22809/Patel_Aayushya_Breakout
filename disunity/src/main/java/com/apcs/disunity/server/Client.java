package com.apcs.disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;


/** 
 * A client that can connect to the server
 * 
 * @author Sharvil Phadke
 */
public class Client implements Closeable {

    /* ================ [ FIELDS ] ================ */

    private final Socket socket;
    private final PayloadTransceiver transceiver;
    private final String identifier;

    // Constructor
    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        transceiver = new PayloadTransceiver(socket.getInputStream(), socket.getOutputStream());
        identifier = String.format("%s:%d", socket.getLocalAddress(), socket.getLocalPort());
        System.out.println("[CLIENT] Connected to server. My identifier is: "+identifier);
    }

    /* ================ [ METHODS ] ================ */

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

    public String id() {
        return identifier;
    }
      
}
