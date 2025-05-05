package com.apcs.disunity.nodes.sprite;

import java.awt.image.BufferedImage;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.resources.Image;
import com.apcs.disunity.resources.Resources;

/**
 * A 2d node that renders an image
 * 
 * @author Qinzhao Li
 */
public class Sprite extends Node2D<Node<?>> {

    /* ================ [ FIELDS ] ================ */

    // Sprite image id
    private final ImageLocation imageLocation;

    // Constructors
    public Sprite(ImageLocation imageLocation) {
        this.imageLocation = imageLocation;
    }
    public Sprite(String path) {
        this.imageLocation = new ImageLocation(path);
    }

    /* ================ [ NODE ] ================ */

    protected BufferedImage getImage() {
        BufferedImage img = Resources.loadResource(imageLocation.PATH, Image.class).getBuffer();
        if(imageLocation.POS == null || imageLocation.SIZE == null) {
            return img;
        } else {
            return img.getSubimage(
                imageLocation.POS.xi,
                imageLocation.POS.yi,
                imageLocation.SIZE.xi,
                imageLocation.SIZE.yi
            );
        }
    }

    @Override
    public void draw(Transform offset) {

        // Load sprite image
        BufferedImage img = getImage();

        // Draw image to buffer
        Game.getInstance().getBuffer().drawImage(img, getTransform().apply(offset));

        // Draw children
        super.draw(offset);
    }

}
