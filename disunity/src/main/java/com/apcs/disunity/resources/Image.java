package com.apcs.disunity.resources;

import java.awt.Graphics2D;
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

        // Resize image
        public Builder resize(int w, int h) {
            // New image
            BufferedImage res = new BufferedImage(w, h, image.getType());
            
            // Draw image
            Graphics2D g = res.createGraphics();
            g.drawImage(image, 0, 0, w, h, null);
            g.dispose();

            // Set image
            image = res;

            // Return self
            return this;
        }

        // Get image
        public Image getImage() { return new Image(image); }

    }
    
}
