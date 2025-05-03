package com.apcs.disunity.physics;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.signals.Signal;

public class ColliderInfo {
    public final AABB AABB;
    public final Signal<CollisionInfo> COLLISION_INFO;

    public ColliderInfo(Collider collider, Vector2 absPos) {
        AABB = new AABB(collider,absPos);
        COLLISION_INFO = collider.collisionInfo;
    }
}
