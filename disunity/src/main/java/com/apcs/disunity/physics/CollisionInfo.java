package com.apcs.disunity.physics;

/**
 * Contains info for collision between two colliders
 * @author Aayushya Patel
 */
public class CollisionInfo {

    public final AABB me;
    public final AABB you;
    public final double delta;
    public final CollisonCheckerRuntime checker;

    public CollisionInfo(AABB self, AABB other, double delta, CollisonCheckerRuntime checker) {
        this.me = self;
        this.you = other;
        this.delta = delta;
        this.checker = checker;
    }

    public boolean isColliding() {
        return checker.isColliding(me,you);
    }
}
