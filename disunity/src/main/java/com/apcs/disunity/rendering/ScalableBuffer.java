package com.apcs.disunity.rendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.apcs.disunity.math.Vector2;

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
        this.refresh(target);
    }

    /* ================ [ METHODS ] ================ */

    // Refresh buffer
    public void refresh(Vector2 size) {

        size = Vector2.of(
            Math.min(size.x, size.y * ratio.x / ratio.y),
            Math.min(size.y, size.x * ratio.y / ratio.x)
        );

        // Create buffer
        image = new BufferedImage(
            size.xi, size.yi,
            BufferedImage.TYPE_INT_ARGB
        );
        graphics = image.createGraphics();

        // Set scale
        scale = size.x / ratio.x;

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
    public void drawImage(Image img, Vector2 pos, Vector2 scaleFactor) {
        graphics.drawImage(img,
            (int) (pos.x * scale), (int) (pos.y * scale),
            (int) (img.getWidth(null) * scale * scaleFactor.x),
            (int) (img.getHeight(null) * scale * scaleFactor.y),
            null
        );
    }

    // Draw image
    public void drawImage(Image img, Vector2 pos) {
        this.drawImage(img, pos, Vector2.ONE);
    }

}
