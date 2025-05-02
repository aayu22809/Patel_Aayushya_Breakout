package com.apcs.disunity.nodes.sprite;


import com.apcs.disunity.Game;
import com.apcs.disunity.annotations.syncedfield.SyncedInt;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.selector.Indexed;
import com.apcs.disunity.resources.Image;
import com.apcs.disunity.resources.Resources;

import java.awt.image.BufferedImage;

/**
 * Handles one animation with frames
 * 
 * @author Qinzhao Li
 */
public class AnimationSprite extends Node2D<Node<?>> implements Indexed<String> {

    /* ================ [ FIELDS ] ================ */

    // Animation name
    private final String name;
    private final String path;
    private long prevFrame = System.nanoTime();

    // Frame durations list
    private final double[] frameDurations;

    // Current frame
    @SyncedInt
    private int frameCount = 0;

    // Constructors
    public AnimationSprite(String name, String path, double... frameDurations) {
        this.name = name;
        this.path = path;
        this.frameDurations = frameDurations;
    }

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
    public void draw(Transform offset) {
        // Load current frame
        BufferedImage img = Resources.loadResource(path, Image.class).getBuffer();

        // Crop image to current frame
        int w = img.getWidth() / length();
        img = img.getSubimage(
            w * frameCount, 0,
            w, img.getHeight()
        );

        // Draw image to buffer
        Game.getInstance().getBuffer().drawImage(img, getTransform().apply(offset));

        // Draw children
        super.draw(offset);
    }
}
