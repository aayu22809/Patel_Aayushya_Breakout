package com.apcs.disunity.game.physics;

/// represents which layers should collide, in a bitset.
/// e.g. mask of 0b1101 means colliders in layers 3 2 and 0 should collide
public class CollisionMask {
    public final int BITSET;

    public CollisionMask(CollisionLayer... layers) {
        int mask = 0;
        for (CollisionLayer layer : layers) {
            mask |= layer.BITSET;
        }
        BITSET = mask;
    }

    public CollisionMask(int bitset) { this.BITSET = bitset; }
}
