package disunity.server;

import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        try(Host h = new Host()) {
            ByteStore temp = new ByteStore();
            h.supply(() -> temp.getBytes());
            h.recieve((bytes) -> temp.setBytes(bytes));

            try (Client c = new Client()) {
                try (Scanner in = new Scanner(System.in)) {
                    do {
                        System.out.println("CLIENT: Initiating a SEND request:");
                        System.out.print("CLIENT: Send something to the server: ");
                        String input = in.nextLine();
                        System.out.println();
                        c.send(input.getBytes());
                        System.out.println("\nCLIENT: Sending a GET request.\n");
                        String output = new String(c.recieve());
                        System.out.printf("\nCLIENT: Recieved: %s\n\n", output);
                        System.out.print("CLIENT: Continue? (y/n): ");
                    } while (in.nextLine().trim().equalsIgnoreCase("y"));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
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
