package com.apcs.disunity.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * An image to be used in the game
 * 
 * @author Qinzhao Li
 */
public class Image {

    /* ================ [ FIELDS ] ================ */

    // Image instance
    private final BufferedImage image;

    // Constructors
    public Image(BufferedImage image) { this.image = image; }

    /* ================ [ METHODS ] ================ */

    // Get image
    public BufferedImage getImage() { return image; }

    /* ================ [ BUILDER ] ================ */

    /**
     * Builds an image
     * 
     * @author Qinzhao Li
     */
    public static class Builder {

        /* ================ [ FIELDS ] ================ */

        // Image instance
        private BufferedImage image;

        /* ================ [ METHODS ] ================ */
        
        // Set image
        public Builder set(BufferedImage image) { this.image = image; return this; }

        // Load image
        public Builder load(String path) {
            try { image = ImageIO.read(new File(path)); }
            catch (IOException e) { e.printStackTrace(); }
            return this;
        }

        // Get image
        public Image get() { return new Image(image); }

    }
    
}
