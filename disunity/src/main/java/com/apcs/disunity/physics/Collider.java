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
    public static final byte DEFAULT_LAYER = 0;
    public static final int DEFAULT_MASK = 1;
    public static final int COLLIDE_WITH_ALL = ~0;

    public final Vector2 SIZE;

    /// Collision layer
    public final int LAYER;
    /// bit set representing which layers to collide with
    public final int MASK;

    public Signal<CollisionInfo> collisionInfo = new Signal<>(CollisionInfo.class);

    /// layers 0-31 can collide with other colliders.
    /// layers 32- will not collide with anything.
    public Collider(int w, int h, byte layer, int mask) {
        SIZE = Vector2.of(w,h);
        this.LAYER = 1<<layer;
        this.MASK = mask;
    }
    public Collider(int w, int h) { this(w,h, DEFAULT_LAYER, DEFAULT_MASK); }
}
