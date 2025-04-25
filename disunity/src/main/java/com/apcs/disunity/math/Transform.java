package com.apcs.disunity.math;

import com.apcs.disunity.annotations.syncedfield.SyncedDouble;
import com.apcs.disunity.annotations.syncedfield.SyncedObject;

/**
 * Contains position, scale, and rotation information
 * 
 * @author Qinzhao Li
 */
public class Transform {

    /* ================ [ FIELDS ] ================ */

    // Position
    @SyncedObject
    public final Vector2 pos;

    // Scale
    @SyncedObject
    public final Vector2 scale;

    // Rotation
    @SyncedDouble
    public final double rot;

    // Constructors
    public Transform() { this(Vector2.ZERO, Vector2.ONE, 0); }
    public Transform(Vector2 pos, Vector2 scale, double rot) {
        this.pos = pos;
        this.scale = scale;
        this.rot = rot % 360;
    }

    /* ================ [ METHODS ] ================ */

    // Basic setters
    public Transform setPos(Vector2 pos) { return new Transform(pos, scale, rot); }
    public Transform setScale(Vector2 scale) { return new Transform(pos, scale, rot); }
    public Transform setRot(double rot) { return new Transform(pos, scale, rot); }

    // Move by an amount
    public Transform move(Vector2 amt) { return new Transform(pos.add(amt), scale, rot); }

    // Scale by a scalar
    public Transform scale(double amt) { return new Transform(pos, scale.mul(amt), rot); }

    // Scale by a vector
    public Transform scale(Vector2 amt) { return new Transform(pos, scale.mul(amt), rot); }

    // Rotate by an amount
    public Transform rotate(double amt) { return new Transform(pos, scale, (rot + amt) % 360); }

    // Apply another transform
    public Transform apply(Transform t) { return new Transform(pos.add(t.pos), scale.mul(t.scale), (rot + t.rot) % 360); }

    /* ================ [ OBJECT ] ================ */

    @Override
    public String toString() { return "pos: " + pos + ", scale: " + scale + ", rot: " + rot; }

}
