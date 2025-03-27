package disunity.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Host implements Closeable {

    private static final int BACKLOG_SIZE = 100;
    private static final int DEFAULT_PORT = 3000;

    private int port;
    private byte[] enqueuedBuffer = null;
    private byte[] recievedBuffer = null;
    private Consumer<byte[]> onPost;
    private Supplier<byte[]> onGet;

    private HttpServer server;

    public Host() throws IOException {
        this(DEFAULT_PORT);
    }

    public Host(int port) throws IOException {
        this.port = port;
        server = HttpServer.create(new InetSocketAddress(port),BACKLOG_SIZE, "/", (HttpExchange exchange) -> {
            System.out.printf("SERVER: Exchange Recieved with method %s\n",exchange.getRequestMethod());
            switch (exchange.getRequestMethod()) {
                case "GET" -> {
                    if (onGet != null) {
                        enqueuedBuffer = onGet.get();
                        System.out.printf("SERVER: Enqueued \"%s\" due to attached supplier\n",new String(enqueuedBuffer));
                    }
                    try (exchange) {
                        if (enqueuedBuffer == null) {
                            exchange.sendResponseHeaders(500, -1);
                            System.out.println("SERVER: Unable to respond to GET request as the enqueued buffer was null\n");
                            break;
                        }
                        exchange.sendResponseHeaders(200, enqueuedBuffer.length);
                        exchange.getResponseBody().write(enqueuedBuffer);
                        System.out.printf("SERVER: Responded \"%s\" to GET request\n",new String(enqueuedBuffer));
                    }
                }

                case "POST" -> {
                    try (exchange) {
                        InputStream in = exchange.getRequestBody();
                        recievedBuffer = in.readAllBytes();
                        exchange.sendResponseHeaders(200, -1);
                        if (onPost != null) {
                            onPost.accept(recievedBuffer);
                            System.out.printf("SERVER: Dispatched \"%s\" to attached reciever\n", new String(recievedBuffer));
                        }
                        System.out.printf("SERVER: Recieved \"%s\" from POST request\n",new String(recievedBuffer));
                    }
                }

            }
        });
        server.start();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void enqueueBytes(byte[] enqueuedBuffer) {
        this.enqueuedBuffer = enqueuedBuffer;
    }

    public byte[] getRecievedBytes() {
        return recievedBuffer;
    }

    public void supply(Supplier<byte[]> supplier) {
        onGet = supplier;
    }

    public void recieve(Consumer<byte[]> consumer) {
        onPost = consumer;
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
