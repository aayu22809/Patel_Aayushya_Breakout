package com.apcs.disunity.animation;

/**
 * Handles one animation with frames
 * 
 * @author Qinzhao Li
 */
public class Animation {

    /* ================ [ FIELDS ] ================ */

    // Animation name
    private final String name;

    // Frame durations list
    private final double[] frameDurations;

    // Current frame
    private int frame = 0;

    // Constructors
    public Animation(String name, double... frameDurations) {
        this.name = name;
        this.frameDurations = frameDurations;
    }

    /* ================ [ METHODS ] ================ */

    // Get animation name
    public String getName() { return name; }

    // Next frame
    public void nextFrame() { frame = (frame + 1) % frameDurations.length; }

    // Get current frame
    public int getFrame() { return frame; }

    // Get frame duration
    public double getFrameDuration() { return frameDurations[frame]; }

    // Get frame count
    public int getFrameCount() { return frameDurations.length; }
    
}
