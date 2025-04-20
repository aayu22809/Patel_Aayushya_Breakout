package com.apcs.disunity.server.test;

import com.apcs.disunity.server.Host;
import com.apcs.disunity.server.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static com.apcs.disunity.server.SyncableLong.decodeLong;
import static com.apcs.disunity.server.SyncableLong.encodeLong;

public class PingTestServer {
    public static void main(String[] args) {
        try (Host h = new Host()) {
            System.out.printf("Address: %s\n   Port: %d\n",h.getAddress(),h.getPort());
            h.start();
            System.out.print("Server started. Please wait for Client to join, then type the identifier: ");
            int identifier;
            try (Scanner s = new Scanner(System.in)) {
                identifier = h.identify(s.nextLine());
            }

            for (int i = 0; i < 100; i++) {

                // synchronize
                long t = System.currentTimeMillis();
                h.send(identifier, new byte[0]);
                h.recieve(identifier);
                System.out.println("Sync time: "+(System.currentTimeMillis() - t));

                //test
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                encodeLong(System.currentTimeMillis(),out);
                h.send(identifier, out.toByteArray());

            }
            h.send(identifier, new byte[0]);
            h.recieve(identifier);
            for (int i = 0; i < 100; i++) {

                // synchronize
                long t = System.currentTimeMillis();
                h.send(identifier, new byte[0]);
                h.recieve(identifier);
                System.out.println("Sync time: "+(System.currentTimeMillis() - t));

                //test
                System.out.println("Ping: "+(System.currentTimeMillis() - decodeLong(new ByteArrayInputStream(h.recieve(identifier)))));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
