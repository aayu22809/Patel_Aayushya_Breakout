package com.apcs.disunity.nodes.sprite;

import java.awt.image.BufferedImage;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.resources.Image;
import com.apcs.disunity.resources.Resources;

/**
 * A 2d node that renders an image
 * 
 * @author Qinzhao Li
 */
public class Sprite extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Sprite image id
    private String image;

    // Constructors
    public Sprite(String image) { super(); this.image = image; }
    public Sprite(String image, Node<?>... children) { super(children); this.image = image; }
    public Sprite(String image, Vector2 pos, Node<?>... children) { super(pos, children); this.image = image; }
    public Sprite(String image, Vector2 pos, Vector2 scale, Node<?>... children) { super(pos, scale, children); this.image = image; }

    /* ================ [ METHODS ] ================ */

    // Get image
    public String getImage() { return image; }

    // Set image
    public void setImage(String image) { this.image = image; }

    /* ================ [ NODE ] ================ */

    @Override
    public void draw(Vector2 offset) {
        // Load sprite image
        BufferedImage img = Resources.loadResource(getImage(), Image.class).getImage();
        Vector2 imgScale = getScale();

        // Draw image to buffer
        Game.getInstance().getBuffer().drawImage(
            img,
            getPos().add(offset).add(Vector2.of(img.getWidth() * imgScale.x, img.getHeight() * imgScale.y).mul(-0.5)),
            imgScale
        );

        super.draw(offset);
    }

}
