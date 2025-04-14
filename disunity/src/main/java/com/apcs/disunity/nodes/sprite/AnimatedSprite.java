package com.apcs.disunity.nodes.sprite;

import java.awt.image.BufferedImage;

import com.apcs.disunity.Game;
import com.apcs.disunity.animation.AnimationSet;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.resources.Image;
import com.apcs.disunity.resources.Resources;

/**
 * A sprite that can play animations
 * 
 * @author Qinzhao Li
 */
public class AnimatedSprite extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Animation set
    private AnimationSet animations;

    // Current animation
    private String animation = "run";

    // Previous frame time
    private long prevFrame = System.nanoTime();

    // Constructors
    public AnimatedSprite(AnimationSet animations) { this.animations = animations; }
    public AnimatedSprite(AnimationSet animations, Node<?>... children) { super(children); this.animations = animations; }
    public AnimatedSprite(AnimationSet animations, Vector2 pos, Node<?>... children) { super(pos, children); this.animations = animations; }
    public AnimatedSprite(AnimationSet animations, Vector2 pos, Vector2 scale, Node<?>... children) { super(pos, scale, children); this.animations = animations; }

    /* ================ [ METHODS ] ================ */

    // Set animation
    public void setAnimation(String animation) {
        this.animation = animation;
        prevFrame = System.nanoTime();
    }

    // Get animation
    public String getAnimation() { return animation; }

    /* ================ [ NODE ] ================ */

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
    public void draw(Vector2 offset) {
        BufferedImage img;
        if (animation == null) {
            // Default sprite fallback
            img = Resources.loadResource(animations.getBase(), Image.class).getImage();
        } else {
            // Load current frame
            img = Resources.loadResource(
                animations.getAnimation(animation).getFrame().image,
                Image.class
            ).getImage();
        }

        Vector2 imgScale = getScale();
            
        // Draw image to buffer
        Game.getInstance().getBuffer().drawImage(
            img,
            getPos().add(offset).add(Vector2.of(img.getWidth() * imgScale.x, img.getHeight() * imgScale.y).mul(-0.5)),
            imgScale
        );
        
        // Draw children
        super.draw(offset);
    }
    
}
