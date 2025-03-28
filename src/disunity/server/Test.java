package disunity.server;

import java.io.IOException;
import java.util.Scanner;

class Test {

    public static void main(String[] args) {
        ByteStore temp = new ByteStore();
        try(@SuppressWarnings("unused")
        Host h = new Host((s) -> temp.getBytes(), (b) -> temp.setBytes(b))) {
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

    static class ByteStore {
        private byte[] bytes = new byte[0];

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }


    }

}
