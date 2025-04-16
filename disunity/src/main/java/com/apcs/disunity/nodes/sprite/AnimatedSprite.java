package com.apcs.disunity.nodes.sprite;

import java.awt.image.BufferedImage;

import com.apcs.disunity.Game;
import com.apcs.disunity.animation.AnimationSet;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.controller.Controlled;
import com.apcs.disunity.resources.Image;
import com.apcs.disunity.resources.Resources;
import com.apcs.disunity.signals.Signals;

/**
 * A sprite that can play animations
 * 
 * @author Qinzhao Li
 */
public class AnimatedSprite extends Node2D implements Controlled {

    /* ================ [ FIELDS ] ================ */

    // Controller id
    private int controller;

    // Animation set
    private AnimationSet animations;

    // Current animation
    private String animation = null;

    // Previous frame time
    private long prevFrame = System.nanoTime();

    // Constructors
    public AnimatedSprite(AnimationSet animations) { this.animations = animations; }
    public AnimatedSprite(AnimationSet animations, Node<?>... children) { super(children); this.animations = animations; }
    public AnimatedSprite(AnimationSet animations, Transform transform, Node<?>... children) { super(transform, children); this.animations = animations; }
    
    /* ================ [ CONTROLLED ] ================ */

    // Set controller id
    public void setController(int controller) { this.controller = controller; }
    
    /* ================ [ METHODS ] ================ */

    // Set animation
    public void setAnimation(String animation) {
        if (this.animation == animation) return;
        this.animation = animation;
        prevFrame = System.nanoTime();
    }

    // Get animation
    public String getAnimation() { return animation; }

    /* ================ [ NODE ] ================ */

    @Override
    public void initialize() {
        // Connect to signal
        Signals.connect(Signals.getSignal(controller, "animate"), this::setAnimation);

        // Complete initialization
        super.initialize();
    }

    @Override
    public void update(double delta) {
        // Update frame
        if (animation != null) {
            if (System.nanoTime() - prevFrame >= animations.getAnimation(animation).getFrame().duration * 1000000000) {
                prevFrame = System.nanoTime();
                animations.getAnimation(animation).nextFrame();
            }
        }

        // Update children
        super.update(delta);
    }

    @Override
    public void draw(Transform offset) {
        BufferedImage img;
        if (animation == null) {
            // Default sprite fallback
            img = Resources.loadResource(animations.getBase(), Image.class).getImage();
        } else {
            // Load current frame
            img = Resources.loadResource(
                Resources.createId(
                    animations.getBase(),
                    animations.getAnimation(animation).getFrame().image
                ),
                Image.class
            ).getImage();
        }
            
        // Draw image to buffer
        Game.getInstance().getBuffer().drawImage(img, transform.apply(offset));
        
        // Draw children
        super.draw(offset);
    }
    
}
