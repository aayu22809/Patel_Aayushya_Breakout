package com.apcs.disunity.game.physics;

/**
 * Contains info for collision between two colliders
 * @author Aayushya Patel
 */
public class CollisionInfo {

    public final AABB me;
    public final AABB you;
    public final double delta;

    public CollisionInfo(AABB self, AABB other, double delta) {
        this.me = self;
        this.you = other;
        this.delta = delta;
    }
}
