package disunity.rendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import disunity.math.Vector2;

/**
 * A scalable buffer to draw to
 * 
 * @author Qinzhao Li
 */
public class ScalableBuffer {

    /* ================ [ FIELDS ] ================ */

    // Image
    private BufferedImage image;
    private Graphics2D graphics;

    // Scale
    private Vector2 ratio;
    private double scale;

    // Constructor
    public ScalableBuffer(Vector2 ratio, Vector2 target) {
        // Set ratio
        this.ratio = ratio;

        // Refresh buffer
        refresh(target);
    }

    /* ================ [ METHODS ] ================ */

    // Refresh buffer
    public void refresh(Vector2 target) {

        target = Vector2.of(
            Math.min(target.x, target.y * ratio.x / ratio.y),
            Math.min(target.y, target.x * ratio.y / ratio.x)
        );

        // Create buffer
        image = new BufferedImage(
            target.xi, target.yi,
            BufferedImage.TYPE_INT_ARGB
        );
        graphics = image.createGraphics();

        // Set scale
        scale = target.x / ratio.x;

        // White background
        graphics.setBackground(Color.WHITE);
    }

    // Clear buffer
    public void clear() { graphics.clearRect(0, 0, image.getWidth(), image.getHeight()); }

    // Getters
    public double getScale() { return scale; }
    public BufferedImage getImage() { return image; }

    /* ================ [ GRAPHICS ] ================ */

    // Draw image
    public void drawImage(Image img, Vector2 pos, ImageObserver observer) {
        graphics.drawImage(img,
            (int) (pos.x * scale), (int) (pos.y * scale),
            (int) (img.getHeight(observer) * scale),
            (int) (img.getWidth(observer) * scale),
            observer
        );
    }

}
