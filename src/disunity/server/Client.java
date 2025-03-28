package disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class Client implements Closeable {

    private final Socket socket;
    private final PayloadTransciever transciever;
    private final String identifier;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        transciever = new PayloadTransciever(socket.getInputStream(), socket.getOutputStream());
        identifier = String.format("%s:%d", socket.getLocalAddress(), socket.getLocalPort());
        System.out.println("[CLIENT] Connected to server. My identifier is: "+identifier);
    }

    public void send(byte[] bytes) {
        transciever.send(bytes);
    }

    public byte[] recieve() {
        return transciever.recieve();
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
