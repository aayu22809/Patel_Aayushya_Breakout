package ljaag.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

    /* ================ [ FIELDS ] ================ */

    /** Image instance */
    private BufferedImage img = null;

    /** Construct an ImageUtil. */
    public ImageUtil() {}

    /**
     * Construct an ImageUtil w/ image.
     * 
     * @param img The image
    */
    public ImageUtil(BufferedImage img) { this.img = img; }

    /**
     * Construct an ImageUtil w/ image path.
     * 
     * @param img The path to the image
    */
    public ImageUtil(String path) {
        // Load image
        try { img = ImageIO.read(new File(path)); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /* ================ [ METHODS ] ================ */

    /**
     * Rescale the image.
     * 
     * @param w The width to rescale to
     * @param h The height to rescale to
     * @return The rescaled ImageUtil
     */
    public ImageUtil rescale(int w, int h) {
        // New image
        BufferedImage res = new BufferedImage(w, h, img.getType());
        
        // Draw image
        Graphics g = res.createGraphics();
        g.drawImage(img, 0, 0, w, h, null);
        g.dispose();

        // Set image
        img = res;

        // Return self
        return this;
    }

    /**
     * Get the image.
     * 
     * @return The image
     */
    public BufferedImage getImage() { return img; }
    
}
