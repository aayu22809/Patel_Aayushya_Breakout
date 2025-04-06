package com.apcs.disunity.signals;

/**
 * A signal that can transmit information
 * 
 * @author Qinzhao Li
 */
public class Signal<T> {

    /* ================ [ FIELDS ] ================ */

    // Signal name
    private final String name;

    // Constructors
    public Signal(String name) { this.name = name; }

    /* ================ [ METHODS ] ================ */

    // Get signal name
    public String getName() { return name; }
    
}
