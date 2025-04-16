package com.apcs.disunity.math;

import com.apcs.disunity.server.Syncable;
import com.apcs.disunity.server.Util;

/**
 * A 2D vector with x and y components
 * 
 * @author Qinzhao Li
 * @author Sharvil Phadke
 */
public class Vector2 implements Syncable {

    /* ================ [ CONSTANTS ] ================ */

    // Zero vector
    public static final Vector2 ZERO = new Vector2(0, 0);

    // One vector
    public static final Vector2 ONE = new Vector2(1, 1);

    /* ================ [ FIELDS ] ================ */

    // X and Y components
    public final double x, y;

    // X and Y rounded to integers
    public final int xi, yi;

    // Constructors
    public Vector2() { this(0, 0); }
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
        this.xi = (int) Math.round(x);
        this.yi = (int) Math.round(y);
    }

    /* ================ [ METHODS ] ================ */

    // Create a vector2 given values
    public static Vector2 of(double x) { return new Vector2(x, x); }
    public static Vector2 of(double x, double y) { return new Vector2(x, y); }

    // Add two vectors
    public Vector2 add(Vector2 v) { return new Vector2(x + v.x, y + v.y); }

    // Subtract two vectors
    public Vector2 sub(Vector2 v) { return new Vector2(x - v.x, y - v.y); }

    // Multiply by a scalar
    public Vector2 mul(double v) { return new Vector2(x * v, y * v); }
    
    // Multiply component-wise
    public Vector2 mul(Vector2 v) { return new Vector2(x * v.x, y * v.y); }

    // Divide by a scalar
    public Vector2 div(double v) { return new Vector2(x / v, y / v); }

    // Divide component-wise
    public Vector2 div(Vector2 v) { return new Vector2(x / v.x, y / v.y); }

    // Dot product with another vector
    public double dot(Vector2 v) { return x * v.x + y * v.y; }

    // Magnitude of the vector
    public double length() { return Math.sqrt(x * x + y * y); }
    
    // Return the normalized vector
    public Vector2 normalized() {
        double l = length();
        return l == 0 ? this : new Vector2(x / l, y / l);
    }

    // Check if vectors are equal
    public boolean equals(Vector2 v) { return x == v.x && y == v.y; }

    // Convert to bytes
    public byte[] getBytes() {
        byte[] bytes = new byte[Integer.BYTES * 2];
        System.arraycopy(Util.getBytes(xi), 0, bytes, 0, Integer.BYTES);
        System.arraycopy(Util.getBytes(yi), 0, bytes, Integer.BYTES, Integer.BYTES);
        return bytes;
    }

    /* ================ [ OBJECT ] ================ */

    @Override
    public String toString() { return "(" + x + ", " + y + ")"; }

    /* =============== [SYNCABLE] ================== */
    
    // TODO: decide on how this should be implemented.
    @Override
    public byte[] supply(int recipient) {
        return new byte[0];
    }

    @Override
    public int receive(int sender, byte[] data) {
        return 0;
    }
}
