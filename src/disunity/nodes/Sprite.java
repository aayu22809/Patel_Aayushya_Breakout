package disunity.nodes;

import java.awt.image.BufferedImage;

import disunity.Game;
import disunity.input.Inputs;
import disunity.math.Vector2;
import disunity.resources.Image;
import disunity.resources.Resources;

public class Sprite extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Sprite image
    protected String image;

    // Constructors
    public Sprite(String image) { this.image = image; }
    public Sprite(String image, Node... children) { super(children); this.image = image; }
    public Sprite(String image, Vector2 pos) { super(pos); this.image = image; }
    public Sprite(String image, Vector2 pos, Node... children) { super(pos, children); this.image = image; }

    /* ================ [ METHODS ] ================ */

    @Override
    public void update() {
        // TODO: Remove
        Vector2 input = new Vector2(
            (Inputs.getAction("left") ? -1 : 0) + (Inputs.getAction("right") ? 1 : 0),
            (Inputs.getAction("up") ? -1 : 0) + (Inputs.getAction("down") ? 1 : 0)
        ).normalized();

        move(input.mul(2));
        // TODO: Remove

        // Update children
        for (Node node : children) node.update();
    }
    
    @Override
    public void draw(Vector2 offset) {
        BufferedImage img = Resources.loadResource(image, Image.class).getImage();

        Game.getInstance().getBuffer().drawImage(
            img,
            pos.add(offset).add(Vector2.of(img.getWidth(), img.getHeight()).mul(-0.5)),
            null
        );

        // Draw children
        for (Node node : children) node.draw(pos.add(offset));
    }

}
