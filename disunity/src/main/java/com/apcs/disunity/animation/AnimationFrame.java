package com.apcs.disunity.animation;

/** 
 * A frame of an animation with an image and duration
 * 
 * @author Qinzhao Li
 */
public class AnimationFrame {

    /* ================ [ FIELDS ] ================ */

    // Frame image
    public final String image;

    // Frame duration
    public final double duration;

    // Constructors
    public AnimationFrame(String image, double duration) { this.image = image; this.duration = duration; }
    
}
