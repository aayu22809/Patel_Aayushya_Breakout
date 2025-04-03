package com.apcs.disunity.server.test;

import com.apcs.disunity.server.Client;
import com.apcs.disunity.server.Util;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("CallToPrintStackTrace")
public class PingTestClient {
    public static void main(String[] args) {
        String host;
        int port;
        try (Scanner s = new Scanner(System.in)) {
            System.out.print("Enter Host: ");
            host = s.nextLine();
            System.out.print("Enter Port: ");
            port = s.nextInt();
        }
        try (Client c = new Client(host, port)) {
            System.out.printf("Identifier: ",c.getStringIdentifier());
            for (int i = 0; i < 100; i++) {
                
                //synchronize
                long t = System.currentTimeMillis();
                c.send(new byte[0]);
                c.recieve();
                System.out.println("Sync time: "+(System.currentTimeMillis() - t));

                //test
                System.out.println("Ping: "+(System.currentTimeMillis() - Util.getLong(c.recieve())));
                
            }
            c.send(new byte[0]);
            c.recieve();
            for (int i = 0; i < 100; i++) {

                //synchronize
                long t = System.currentTimeMillis();
                c.send(new byte[0]);
                c.recieve();
                System.out.println("Sync time: "+(System.currentTimeMillis() - t));

                //test
                c.send(Util.getBytes(System.currentTimeMillis()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}