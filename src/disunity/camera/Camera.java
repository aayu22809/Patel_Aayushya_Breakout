package disunity.camera;

import disunity.math.Vector2;
import disunity.nodes.Node;
import disunity.nodes.Node2D;

/**
 * A camera to control the viewport
 * 
 * @author Qinzhao Li
 */
public class Camera extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Camera scale
    protected double scale = 1;

    // Constructors
    public Camera() { }
    public Camera(Node... children) { super(children); }
    public Camera(Vector2 pos) { super(pos); }
    public Camera(Vector2 pos, Node... children) { super(pos, children); }

    /* ================ [ METHODS ] ================ */

    // Scale
    public void scale(double s) { scale = s; }

    /* ================ [ NODE ] ================ */

    @Override
    public void update() {
        // Update children
        for (Node node : children) node.update();
    }

    @Override
    public void draw(Vector2 offset) {
        // Draw children
        for (Node node : children) node.draw(pos.add(offset));
    }
    
}
