package disunity.nodes;

import java.awt.Graphics2D;

import disunity.resources.Image;
import disunity.resources.Resources;

public class Sprite extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Sprite image
    private String image;

    // Constructors
    public Sprite(String image) { this.image = image; }
    public Sprite(String image, Node... children) { super(children); this.image = image; }
    public Sprite(String image, int x, int y) { super(x, y); this.image = image; }
    public Sprite(String image, int x, int y, Node... children) { super(x, y, children); this.image = image; }

    /* ================ [ METHODS ] ================ */

    @Override
    public void update() {
        for (Node node : children) {
            node.update();
        }
    }
    
    @Override
    public void draw(Graphics2D g, int dx, int dy) {
        g.drawImage(((Image) Resources.loadResource(image)).getImage(), x + dx, y + dy, null);

        for (Node node : children) {
            node.draw(g, x + dx, y + dy);
        }
    }

}
