package com.apcs.disunity.physics;

import com.apcs.disunity.math.Vector2;

// Axis Aligned Bounding Box
public class AABB {
    public final double RIGHT;
    public final double BOTTOM;
    public final double LEFT;
    public final double TOP;
    public final Vector2 SIZE;
    public final Vector2 POS;
    public final int LAYER;

    public AABB(Collider collider, Vector2 absPos) {
        this(collider.getSize(), absPos, collider.getLayer());
    }
    public AABB(Vector2 size, Vector2 absPos, int layer) {
        this.LAYER = layer;
        this.SIZE = size;
        POS = absPos;
        TOP = absPos.y;
        LEFT = absPos.x;
        BOTTOM = TOP + SIZE.y;
        RIGHT = LEFT + SIZE.x;
    }

    public boolean isColliding(AABB that) {
        // Check actual collision
        return this.LAYER == that.LAYER &&
            Math.max(this.LEFT, that.LEFT) < Math.min(this.RIGHT, that.RIGHT) &&
            Math.max(this.TOP, that.TOP) < Math.min(this.BOTTOM, that.BOTTOM);
    }

    public AABB setPos(Vector2 pos) {
        return new AABB(SIZE, pos, LAYER);
    }
}
