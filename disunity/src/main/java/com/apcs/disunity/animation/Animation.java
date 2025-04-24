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
    private final String path;

    // Frame durations list
    private final double[] frameDurations;

    // Current frame
    private int frame = 0;

    // Constructors
    public Animation(String name, String path, double... frameDurations) {
        this.name = name;
        this.path = path;
        this.frameDurations = frameDurations;
    }

    /* ================ [ METHODS ] ================ */

    // Get animation name
    public String getName() { return name; }

    // Get path to the animation sheet
    public String getPath() { return path; }

    // Next frame
    public void nextFrame() { frame = (frame + 1) % frameDurations.length; }

    // Get current frame
    public int getFrame() { return frame; }

    // Get frame duration
    public double getFrameDuration() { return frameDurations[frame]; }

    // Get frame count
    public int getFrameCount() { return frameDurations.length; }
    
}
