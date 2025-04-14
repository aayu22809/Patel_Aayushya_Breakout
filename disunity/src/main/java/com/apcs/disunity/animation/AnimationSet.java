package com.apcs.disunity.animation;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores a set of animations for some game element.
 * 
 * @author Qinzhao Li
 */
public class AnimationSet {

    /* ================ [ FIELDS ] ================ */

    // Base image
    private final String base;

    // Animations
    private final Map<String, Animation> animations = new HashMap<>();

    // Constructors
    public AnimationSet(String base, Animation... animations) {
        this.base = base;
        for (Animation animation : animations)
            this.animations.put(animation.getName(), animation);
    }

    /* ================ [ METHODS ] ================ */

    // Get base image
    public String getBase() { return base; }

    // Get animation
    public Animation getAnimation(String name) { return animations.get(name); }
    
}
