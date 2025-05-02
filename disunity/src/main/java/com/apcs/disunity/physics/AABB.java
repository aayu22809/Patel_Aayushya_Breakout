package com.apcs.disunity.physics;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.signals.Signal;

// Axis Aligned Bounding Box
public class AABB {
    public final double RB;
    public final double BB;
    public final double LB;
    public final double TB;
    public final int LAYER;
    public final Signal<CollisionInfo> COLLISION_INFO;

    public AABB(Collider collider, Vector2 absPos) {
        TB = absPos.y;
        LB = absPos.x;
        BB = TB + collider.getSize().y;
        RB = LB + collider.getSize().x;
        LAYER = collider.getLayer();
        COLLISION_INFO = collider.collisionInfo;
    }
}
