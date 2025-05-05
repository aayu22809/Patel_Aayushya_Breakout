package com.apcs.disunity.game.nodes.sprite;


import com.apcs.disunity.app.network.packet.annotation.SyncedInt;
import com.apcs.disunity.game.nodes.selector.Indexed;

import java.awt.image.BufferedImage;

/**
 * Handles one animation with frames
 * 
 * @author Qinzhao Li
 */
public class AnimationSprite extends Sprite implements Indexed<String> {

    /* ================ [ FIELDS ] ================ */

    // Animation name
    private final String name;
    private long prevFrame = System.nanoTime();

    // Frame durations list
    private final double[] frameDurations;

    // Current frame
    @SyncedInt
    private int frameCount = 0;

    public AnimationSprite(String name, ImageLocation imageLocation, double... frameDurations) {
        super(imageLocation);
        this.name = name;
        this.frameDurations = frameDurations;
    }

    public AnimationSprite(String name, String path, double... frameDurations) {
        this(name, new ImageLocation(path), frameDurations);
    }

    // Constructors

    /* ================ [ METHODS ] ================ */

    // Get frame duration
    protected double frameDuration() { return frameDurations[frameCount]; }

    // Get frame count
    protected int length() { return frameDurations.length; }

    @Override
    public String index() {
        return name;
    }

    @Override
    public void update(double delta) {
        // Update frame
        if (System.nanoTime() - prevFrame >= frameDuration() * 1e9) {
            prevFrame = System.nanoTime();
            frameCount++;
            frameCount %= length();
        }
    }

    @Override
    protected BufferedImage getImage() {
        BufferedImage img = super.getImage();

        // Crop image to current frame
        int w = img.getWidth() / length();
        return img.getSubimage(
            w * frameCount, 0,
            w, img.getHeight()
        );
    }
}
