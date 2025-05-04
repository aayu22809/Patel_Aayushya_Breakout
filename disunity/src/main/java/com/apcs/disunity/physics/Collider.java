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
    public static final CollisionLayer DEFAULT_LAYER = new CollisionLayer((byte) 0);
    public static final CollisionMask DEFAULT_MASK = new CollisionMask(DEFAULT_LAYER);
    public static final CollisionMask ALL_LAYER_MASK = new CollisionMask(~0);
    public static final CollisionMask NO_LAYER_MASK = new CollisionMask(0);

    public final Vector2 SIZE;

    public final CollisionLayer LAYER;
    public final CollisionMask MASK;

    public Signal<CollisionInfo> collisionInfo = new Signal<>(CollisionInfo.class);

    /// layers 0-31 can collide with other colliders.
    /// layers 32- will not collide with anything.
    public Collider(int w, int h, CollisionLayer layer, CollisionMask mask) {
        SIZE = Vector2.of(w,h);
        this.LAYER = layer;
        this.MASK = mask;
    }
    public Collider(int w, int h) { this(w,h, DEFAULT_LAYER, DEFAULT_MASK); }
}
