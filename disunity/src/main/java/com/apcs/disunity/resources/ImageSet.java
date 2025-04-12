package com.apcs.disunity.resources;

import java.awt.image.BufferedImage;
import java.io.File;

import com.apcs.disunity.files.FileUtil;

/**
 * An set of images displayed in order
 * 
 * @author Qinzhao Li
 */
public class ImageSet extends Image {
    
    /* ================ [ FIELDS ] ================ */

    // Image frames
    private final BufferedImage[] images;

    // Constructors
    public ImageSet(BufferedImage... images) { super(images[0]); this.images = images; }

    /* ================ [ METHODS ] ================ */

    // Get image at indexs
    public BufferedImage getImage(int index) { return images[index]; }

    /* ================ [ BUILDER ] ================ */

    /**
     * Builds an image set
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
            // Load file data
            BufferedImage temp = new Image.Builder().load(path).get().getImage();
            int frames = Integer.parseInt(FileUtil.parseInfo(FileUtil.getName(new File(path)))[1]);
            int width = temp.getWidth() / frames;

            // Create images
            images = new BufferedImage[frames];
            for (int i = 0; i < frames; i++) {
                images[i] = temp.getSubimage(i * width, 0, width, temp.getHeight());
            }
            
            return this;
        }

        // Get animation
        public ImageSet get() { return new ImageSet(images); }

    }

}
