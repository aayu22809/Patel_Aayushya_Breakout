package com.apcs.disunity;

/**
 * All of the configurable options for the game
 * 
 * @author Qinzhao Li
 */
public class Options {

    /** The frame rate of the game */
    public static final double FPS = 60.0;

    /**
     * Get the number of seconds per frame
     *
     * @return The number of seconds per frame
     */
    public static final double getSPF() { return 1.0 / FPS; }
    
    /**
     * Get the number of milliseconds per frame
     *
     * @return The number of milliseconds per frame
     */
    public static final double getMSPF() { return 1000.0 / FPS; }

    // packet rate
    public static final double PPS = FPS;
    // Seconds per frame
    public static final double getSPP() { return 1.0 / PPS; }
    // Milliseconds per frame
    public static final double getMSPP() { return 1000.0 / PPS; }

}
