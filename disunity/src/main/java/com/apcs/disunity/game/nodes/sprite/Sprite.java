package com.apcs.disunity.game.nodes.sprite;

import java.awt.image.BufferedImage;

import com.apcs.disunity.game.Game;
import com.apcs.disunity.game.nodes.FieldChild;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.game.nodes.Node;
import com.apcs.disunity.game.nodes.twodim.Node2D;
import com.apcs.disunity.app.resources.Image;
import com.apcs.disunity.app.resources.Resources;

/**
 * A 2d node that renders an image
 * 
 * @author Qinzhao Li
 */
public class Sprite extends Node2D<Node<?>> {

    /* ================ [ FIELDS ] ================ */

    // Sprite image id
    @FieldChild
    private  ImageLocation imageLocation;

    // Constructors
    public Sprite(ImageLocation imageLocation) {
        this.imageLocation = imageLocation;
    }
    public Sprite(String path) {
        this.imageLocation = new ImageLocation(path);
    }

    /* ================ [ NODE ] ================ */

    public ImageLocation getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(ImageLocation imageLocation) {
        this.imageLocation = imageLocation;
    }

    @Override
    public void draw(Transform offset) {

        // Load sprite image
        BufferedImage img = imageLocation.getImage();

        // Draw image to buffer
        Game.getInstance().getBuffer().drawImage(img, getTransform().apply(offset));

        // Draw children
        super.draw(offset);
    }

}
