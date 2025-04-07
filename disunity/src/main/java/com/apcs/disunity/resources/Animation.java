package com.apcs.disunity.resources;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * An set of images displayed in order
 * 
 * @author Qinzhao Li
 */
public class Animation {
    
    /* ================ [ FIELDS ] ================ */

    // Frame
    private int frame = 0;

    // Images
    private final Image[] images;

    // Constructors
    public Animation(Image... images) { this.images = images; }

    /* ================ [ METHODS ] ================ */

    // Get image
    public Image getImage() { return images[frame]; }

    /* ================ [ BUILDER ] ================ */

    /**
     * Builds an animation
     * 
     * @author Qinzhao Li
     */
    public static class Builder {

        /* ================ [ FIELDS ] ================ */

        // Image instance
        private Image[] images;

        /* ================ [ METHODS ] ================ */
        
        // Set image
        public Builder set(Image[] images) { this.images = images; return this; }

        // Load image
        public Builder load(String path) {
            Image temp = new Image.Builder().load(path).getImage();
            
            return this;
        }

        // Get animation
        public Animation getAnimation() { return new Animation(images); }

    }

}
