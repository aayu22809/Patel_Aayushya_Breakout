package com.apcs.disunity.camera;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;

/**
 * A camera to control the viewport
 * 
 * @author Qinzhao Li
 */
public class Camera extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public Camera() { super(); }
    public Camera(Node<?>... children) { super(children); }
    public Camera(Transform transform, Node<?>... children) { super(transform, children); }

    /* ================ [ NODE ] ================ */

    @Override
    public void update(double delta) {
        // Update global transform
        Game.getInstance().setTransform(transform);

        // Update children
        super.update(delta);
    }

    /* ================ [ SYNCED ] ================ */

    @Override
    public byte[] supply(int recipient) {
        // return pos.getBytes();
        return new byte[0];
    }

    @Override
    public int receive(int sender, byte[] data) {
        // pos = Vector2.of(Util.getInt(data, 0), Util.getInt(data, Integer.BYTES));
        return 0;
    }
}
