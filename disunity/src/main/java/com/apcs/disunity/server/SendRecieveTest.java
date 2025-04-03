package com.apcs.disunity.server;


import java.io.IOException;
import java.util.Scanner;

class SendRecieveTest {

    public static void main(String[] args) {
        try(Host h = new Host()) {
            h.start();
            try (Client c = new Client(h.getAddress(), h.getPort())) {
                try (Scanner in = new Scanner(System.in)) {
                    do {
                        System.out.print("[CLIENT] Sending (enter something): ");
                        String input = in.nextLine();
                        c.send(input.getBytes());
                        System.out.println("[CLIENT] Sent.");
                        byte[] recieved = h.recieve(c.id());
                        System.out.println("[SERVER] Recieved: " + new String(recieved));
                        System.out.print("[SERVER] Sending (enter something): ");
                        input = in.nextLine();
                        h.send(c.id(), input.getBytes());
                        System.out.println("[SERVER] Sent.");
                        recieved = c.recieve();
                        System.out.println("[CLIENT] Recieved: " + new String(recieved));
                        System.out.print("Continue? (y/n): ");
                    } while (in.nextLine().trim().equalsIgnoreCase("y"));
                }
            }
        } catch (IOException ex) { }
    }
}
