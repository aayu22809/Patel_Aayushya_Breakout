package com.apcs.disunity.animation;

import com.apcs.disunity.annotations.SyncedField;
import com.apcs.disunity.server.SyncableInt;

/**
 * Handles one animation with frames
 * 
 * @author Qinzhao Li
 */
public class Animation {

    // MUST DO: remove this
    public int owner;

    /* ================ [ FIELDS ] ================ */

    // Animation name
    private final String name;

    // Frames list
    private final AnimationFrame[] frames;

    // Current frame
    @SyncedField
    private SyncableInt frame = new SyncableInt(0);

    // Constructors
    public Animation(String name, double... frameDurations) {
        this.name = name;

        // Create frames
        frames = new AnimationFrame[frameDurations.length];
        for (int i = 0; i < frameDurations.length; i++) {
            frames[i] = new AnimationFrame(name + "_" + i, frameDurations[i]);
        }
    }

    /* ================ [ METHODS ] ================ */

    // Get animation name
    public String getName() { return name; }

    // Next frame
    public void nextFrame() { frame.setValue((frame.value() + 1) % frames.length); }

    // Get current frame
    public AnimationFrame getFrame() { return frames[frame.value()]; }
    
}
