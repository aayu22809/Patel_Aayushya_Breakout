package disunity.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Byte.SIZE;
import static java.lang.Integer.BYTES;
import java.util.Arrays;


public class PayloadTransciever {
    
    private final OutputStream out;
    private final InputStream in;

    public PayloadTransciever(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void send(byte[] bytes) {
        try {
            System.out.println("Making payload for bytes: "+Arrays.toString(bytes));
            // for convenience header bytes are sent reverse order
            byte[] payload = new byte[BYTES + bytes.length];
            for (int i = 0; i < BYTES; i++) {
                payload[i] = (byte) ((bytes.length & (0b11111111 << (i * SIZE))) >> (i * SIZE));
            }
            System.arraycopy(bytes, 0, payload, BYTES, bytes.length);
            System.out.println("Sending payload: "+Arrays.toString(payload));
            out.write(payload);
            System.out.println("Sent");
        } catch (IOException e) { }
    }

    public byte[] recieve() {
        try {
            System.out.printf("expecting header of size %d...\n", BYTES);
            byte[] header = in.readNBytes(BYTES);
            System.out.println("Recieved header: "+Arrays.toString(header));
            int payloadSize = 0;
            for (int i = 0; i < BYTES; i++) {
                payloadSize += header[i] << (i * SIZE);
            }
            System.out.printf("expecting payload of size %d...\n", payloadSize);
            byte[] payload = in.readNBytes(payloadSize);
            System.out.println("Recieved payload: "+Arrays.toString(payload));
            return payload;
        } catch (IOException e) {
            return null;
        }
    }
}
