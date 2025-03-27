package disunity.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class Client implements Closeable {

    HttpClient client;


    public Client() {
        client = HttpClient.newHttpClient();
    }

    public byte[] recieve() {
        try {
            return client.send(
                HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:3000"))
                    .GET()
                    .build(), 
                BodyHandlers.ofString()).body()
                .getBytes();
        } catch (URISyntaxException | IOException | InterruptedException ix) {
            return null;
        }
    }

    public void send(byte[] buffer) {
        try {
            client.send(
                HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:3000"))
                    .POST(HttpRequest.BodyPublishers.ofByteArray(buffer))
                    .build(),
                BodyHandlers.ofString()
            );
        } catch (URISyntaxException | IOException | InterruptedException e) { }
    }

    @Override
    public void close() {
        client.close();
    }

}
