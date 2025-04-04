package com.apcs.disunity.server;

public interface Synced {

    int HOST = 0;

    /**
     * Generates the data to be sent to a specific recipient.  
     * The provided data should be sufficient for the recipient to fully reconstruct the object.
     * This method should be non-blocking and should not wait for tick updates.
     *
     * @param recipient The ID of the recipient, as managed by the Host.
     * @return A byte array containing the data for the specified recipient.
     */
    byte[] supply(int recipient);

    /**
     * Processes incoming data from a specified sender.  
     * The received data should be sufficient to fully reconstruct the object.
     * The recieved data does not have to be handled immediately, it is valid to
     * cache the data to be synced with other senders' copies at a different time.
     * The super implementation should be called first. It is the super's responsibility
     * to return how many of the bytes have been used by it and it's own super, and so on.
     * 
     * @param sender The ID of the sender, as managed by the Host.
     * @param data   The byte array containing the received data.
     * @return the number of bytes used so far, so the current recieve call knows where to read from.
     */
    int receive(int sender, byte[] data);

}
