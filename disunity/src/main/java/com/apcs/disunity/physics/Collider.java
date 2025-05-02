package com.apcs.disunity.physics;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.signals.Signal;

/**
 * A component that adds collision detection ability to a Node2D
 * @author Aayushya Patel
 */
public class Collider extends Node2D<Node<?>> {

    private Vector2 size;

    // Collision floors
    private int layer = 1;

    public Signal<CollisionInfo> collisionInfo = new Signal<>(CollisionInfo.class);

    /**
     * Create new collider with the given size
     * @param size Size of the collision box
     */
    public Collider(Vector2 size) {
        this.size = size;
    }
    public Collider(int w, int h) {
        this(Vector2.of(w,h));
    }

    /**
     * Set the collision layer
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

    /**
     * Get the collision layer
     */
    public int getLayer() {
        return layer;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    /**
     * Get the size of the collider
     */
    public Vector2 getSize() {
        return size;
    }

}
