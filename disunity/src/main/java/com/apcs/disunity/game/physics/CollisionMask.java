package com.apcs.disunity.game.physics;

/// represents which layers will affect the collider
public class CollisionMask {
    public final int BITSET;
    public CollisionMask(CollisionLayer... layers) {
        int mask = 0;
        for(CollisionLayer layer: layers) {
            mask |= layer.BITSET;
        }
        BITSET = mask;
    }
    public CollisionMask(int bitset) {
        this.BITSET = bitset;
    }
}
