package com.apcs.disunity.resources;

import java.awt.image.BufferedImage;
import java.io.File;

import com.apcs.disunity.files.FileUtil;

/**
 * An set of images displayed in order
 * 
 * @author Qinzhao Li
 */
public class Animation extends Image {
    
    /* ================ [ FIELDS ] ================ */

    // Image frames
    private final int frames;

    // Constructors
    public Animation(BufferedImage image, int frames) { super(image); this.frames = frames; }

    /* ================ [ METHODS ] ================ */

    // Get number of frames
    public int getFrames() { return frames; }

    // Get frame at index
    public BufferedImage getFrame(int index) {
        // Extract frame
        int width = getImage().getWidth() / getFrames();
        return getImage().getSubimage(index * width, 0, width, getImage().getHeight());
    }

    /* ================ [ BUILDER ] ================ */

    /**
     * Builds an image set
     * 
     * @author Qinzhao Li
     */
    public static class Builder {

        /* ================ [ FIELDS ] ================ */

        // Image instance
        private BufferedImage image;

        // Frame count
        private int frames;

        /* ================ [ METHODS ] ================ */
        
        // Set image
        public Builder set(BufferedImage image, int frames) { this.image = image; this.frames = frames; return this; }

        // Load image
        public Builder load(String path) {
            // Load file data
            image = new Image.Builder().load(path).get().getImage();
            frames = Integer.parseInt(FileUtil.parseInfo(FileUtil.getName(new File(path)))[1]);
            return this;
        }

        // Get animation
        public Animation get() { return new Animation(image, frames); }

    }

}
