package com.apcs.disunity.camera;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.UndrawnNode;

/**
 * A camera to control the viewport
 * 
 * @author Qinzhao Li
 */
public class Camera extends UndrawnNode {

    /* ================ [ FIELDS ] ================ */

    // Camera position
    private Vector2 pos = Vector2.ZERO;

    // Camera scale
    private double scale = 1;

    // Constructors
    public Camera() { super(); }
    public Camera(UndrawnNode... children) { super(children); }
    public Camera(Vector2 pos, UndrawnNode... children) { super(children); this.pos = pos; }

    /* ================ [ METHODS ] ================ */

    // Scale
    public void scale(double s) { scale = s; }

    // Set position
    public void setPos(Vector2 pos) { this.pos = pos; }

    // Get position
    public Vector2 getPos() { return pos; }

    /* ================ [ NODE ] ================ */

    @Override
    public void update(double delta) {
        // Update camera position
        Game.getInstance().setCameraPos(getPos());

        // Update children
        super.update(delta);
    }

    /* ================ [ SYNCED ] ================ */

    @Override
    public byte[] supply(int recipient) {
        return pos.getBytes();
    }

    @Override
    public void receive(int sender, byte[] data) {
        pos = Vector2.of(sender,data);
    }
}
