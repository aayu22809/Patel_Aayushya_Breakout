package com.apcs.disunity.nodes;

import java.awt.image.BufferedImage;
import java.util.List;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.rendering.RenderObject;
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
    
    @Override
    public List<RenderObject> getRenderObjects(Vector2 offset) {
        // Draw sprite image
        BufferedImage img = Resources.loadResource(image, Image.class).getImage();

        Vector2 sf = getScale();

        List<RenderObject> objs = super.getRenderObjects(offset);
        objs.add(new RenderObject(
            image, 
            getPos().add(offset).add(Vector2.of(img.getWidth() * sf.x, img.getHeight() * sf.y).mul(-0.5)),
            sf
        ));


        // Draw children
        return objs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
