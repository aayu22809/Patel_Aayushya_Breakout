package com.apcs.disunity.server;

import java.io.IOException;
import java.util.Scanner;

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
                h.send(identifier, Util.getBytes(System.currentTimeMillis()));

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
                System.out.println("Ping: "+(System.currentTimeMillis() - Util.getLong(h.recieve(identifier))));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
