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

    // Frames list
    private final AnimationFrame[] frames;

    // Current frame
    private int frame = 0;

    // Constructors
    public Animation(String name, AnimationFrame... frames) { this.name = name; this.frames = frames; }

    /* ================ [ METHODS ] ================ */

    // Get animation name
    public String getName() { return name; }

    // Next frame
    public void nextFrame() { frame = (frame + 1) % frames.length; }

    // Get current frame
    public AnimationFrame getFrame() { return frames[frame]; }
    
}
