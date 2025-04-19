package com.apcs.disunity;

/**
 * All of the configurable options for the game
 * 
 * @author Qinzhao Li
 */
public class Options {

    // Frame rate
    public static double FPS = 60.0;
    // Seconds per frame
    public static double getSPF() { return 1.0 / FPS; }
    // Milliseconds per frame
    public static double getMSPF() { return 1000.0 / FPS; }

    // packet rate
    public static double PPS = FPS;
    // Seconds per frame
    public static double getSPP() { return 1.0 / PPS; }
    // Milliseconds per frame
    public static double getMSPP() { return 1000.0 / PPS; }
}
