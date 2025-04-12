package com.apcs.disunity.nodes.sprite;

import java.awt.image.BufferedImage;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.resources.Animation;
import com.apcs.disunity.resources.Resources;

/**
 * A sprite that can play animations
 * 
 * @author Qinzhao Li
 */
public class AnimatedSprite extends Sprite {

    /* ================ [ FIELDS ] ================ */

    // Current animation
    private String animation = null;

    // Current frame
    private int frame = 0;

    // Constructors
    public AnimatedSprite(String image) { super(image); }
    public AnimatedSprite(String image, Node<?>... children) { super(image, children); }
    public AnimatedSprite(String image, Vector2 pos, Node<?>... children) { super(image, pos, children); }
    public AnimatedSprite(String image, Vector2 pos, Vector2 scale, Node<?>... children) { super(image, pos, scale, children); }

    /* ================ [ METHODS ] ================ */

    // Set animation
    public void setAnimation(String animation) { this.animation = animation; }

    // Get animation
    public String getAnimation() { return animation; }

    /* ================ [ NODE ] ================ */

    @Override
    public void update(double delta) {
        // Update frame
        if (animation != null) {
            frame = (frame + 1) % Resources.loadResource(
                Resources.createId(getImage(), getAnimation()),
                Animation.class
            ).getFrames();
        }

        // Update children
        super.update(delta);
    }

    @Override
    public void draw(Vector2 offset) {
        // Default sprite when no animation is playing
        if (animation == null) super.draw(offset);
        else {
            // Load frame image
            BufferedImage img = Resources.loadResource(
                Resources.createId(getImage(), getAnimation()),
                Animation.class
            ).getFrame(frame);
            Vector2 imgScale = getScale();
            
            // Draw image to buffer
            Game.getInstance().getBuffer().drawImage(
                img,
                getPos().add(offset).add(Vector2.of(img.getWidth() * imgScale.x, img.getHeight() * imgScale.y).mul(-0.5)),
                imgScale
            );
        }

    }
    
}
