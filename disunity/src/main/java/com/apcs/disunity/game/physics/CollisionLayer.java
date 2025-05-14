package com.apcs.disunity.game.physics;

/// represents which layer the collider lives in, in a form of bitset
public class CollisionLayer {
    public final int LAYER_ID;
    public final int BITSET;

    public CollisionLayer(int layerId) {
        if (layerId >= Integer.SIZE || layerId < 0)
            throw new IllegalArgumentException("layerId was out of supported range");
        this.LAYER_ID = layerId;
        BITSET = 1 << layerId;
    }
}
