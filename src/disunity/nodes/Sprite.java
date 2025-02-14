package disunity.nodes;

import java.awt.Graphics2D;

import disunity.input.Inputs;
import disunity.math.Vector2;
import disunity.resources.Image;
import disunity.resources.Resources;

public class Sprite extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Sprite image
    private String image;

    // Constructors
    public Sprite(String image) { this.image = image; }
    public Sprite(String image, Node... children) { super(children); this.image = image; }
    public Sprite(String image, Vector2 pos) { super(pos); this.image = image; }
    public Sprite(String image, Vector2 pos, Node... children) { super(pos, children); this.image = image; }

    /* ================ [ METHODS ] ================ */

    @Override
    public void update() {
        for (Node node : children) {
            node.update();
        }

        Vector2 input = new Vector2(
            (Inputs.getAction("left") ? -1 : 0) + (Inputs.getAction("right") ? 1 : 0),
            (Inputs.getAction("up") ? -1 : 0) + (Inputs.getAction("down") ? 1 : 0)
        ).normalized();

        pos = pos.add(input.mul(2));

        System.out.println(input.x + ", " + input.y);
    }
    
    @Override
    public void draw(Graphics2D g, int dx, int dy) {
        g.drawImage(((Image) Resources.loadResource(image)).getImage(), pos.xi + dx, pos.yi + dy, null);

        for (Node node : children) {
            node.draw(g, pos.xi + dx, pos.yi + dy);
        }
    }

}
