package com.apcs.disunity.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * An set of images displayed in order
 * 
 * @author Qinzhao Li
 */
public class Animation extends Image {
    
    /* ================ [ FIELDS ] ================ */

    // Current frame
    private int frame = 0;

    // Image frames
    private final BufferedImage[] images;

    // Constructors
    public Animation(BufferedImage... images) {
        super(images[0]);
        this.images = images;
    }

    /* ================ [ BUILDER ] ================ */

    /**
     * Builds an animation
     * 
     * @author Qinzhao Li
     */
    public static class Builder {

        /* ================ [ FIELDS ] ================ */

        // Image instances
        private BufferedImage[] images;

        /* ================ [ METHODS ] ================ */
        
        // Set image
        public Builder set(BufferedImage[] images) { this.images = images; return this; }

        // Load image
        public Builder load(String path) {
            BufferedImage temp = new Image.Builder().load(path).getImage().getImage();
            
            return this;
        }

        // Get animation
        public Animation getAnimation() { return new Animation(images); }

    }

}
