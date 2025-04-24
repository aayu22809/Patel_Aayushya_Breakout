package com.apcs.disunity;

/**
 * All of the configurable options for the game
 * 
 * @author Qinzhao Li
 */
public class Options {

    // Frame rate
    public static final double FPS = 60.0;
    // Seconds per frame
    public static final double getSPF() { return 1.0 / FPS; }
    // Milliseconds per frame
    public static final double getMSPF() { return 1000.0 / FPS; }

    // packet rate
    public static final double PPS = FPS;
    // Seconds per frame
    public static final double getSPP() { return 1.0 / PPS; }
    // Milliseconds per frame
    public static final double getMSPP() { return 1000.0 / PPS; }
}
