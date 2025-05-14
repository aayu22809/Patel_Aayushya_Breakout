package com.apcs.disunity.app.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * An image resource loaded form a file.
 * 
 * @author Qinzhao Li
 * @author Sharvil Phadke
 */
public class Image {

    private final BufferedImage buffer;

    public Image(BufferedImage image) { this.buffer = image; }

    public Image(URL location) {
        try (InputStream stream = location.openStream()) {
            buffer = ImageIO.read(stream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read image files");
        }
    }

    public BufferedImage getBuffer() { return buffer; }

}
