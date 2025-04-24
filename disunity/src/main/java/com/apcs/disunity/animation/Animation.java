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
    public Animation(String name, String path, double... frameDurations) {
        this.name = name;

        // Create frames
        frames = new AnimationFrame[frameDurations.length];
        for (int i = 0; i < frameDurations.length; i++) {
            frames[i] = new AnimationFrame(path.replace("?", Integer.toString(i)), frameDurations[i]);
        }
    }

    /* ================ [ METHODS ] ================ */

    // Get animation name
    public String getName() { return name; }

    // Next frame
    public void nextFrame() { frame = (frame + 1) % frames.length; }

    // Get current frame
    public AnimationFrame getFrame() { return frames[frame]; }
    
}
