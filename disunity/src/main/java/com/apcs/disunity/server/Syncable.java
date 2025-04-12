package com.apcs.disunity.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface Syncable<T> {

    int HOST = 0;

    // packets period in miliseconds
    int PPMS = 20;

    /**
     * Generates the data to be sent to a specific recipient.  
     * The provided data should be sufficient for the recipient to fully reconstruct the object.
     * This method should be non-blocking and should not wait for tick updates.
     *
     * @param recipient The ID of the recipient, as managed by the Host.
     * @param packetOut output stream to write packet data
     */
    void supply(int recipient, OutputStream packetOut);

    /**
     * Processes incoming data from a specified sender.  
     * The received data should be sufficient to fully reconstruct the object.
     * The recieved data does not have to be handled immediately, it is valid to
     * cache the data to be synced with other senders' copies at a different time.
     * The super implementation should be called first. It is the super's responsibility
     * to return how many of the bytes have been used by it and it's own super, and so on.
     * 
     * @param sender The ID of the sender, as managed by the Host.
     * @param packetIn   The input stream containing the received data.
     * @return the number of bytes used so far, so the current recieve call knows where to read from.
     */
    T receive(int sender, InputStream packetIn);

}
