package com.apcs.disunity.math;

/**
 * A 2D vector
 * 
 * @author Qinzhao Li
 */
public class Vector2 {

    /* ================ [ FIELDS ] ================ */

    // Components
    public final double x, y;

    // Integer components
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

    // Of
    public static Vector2 of(double x, double y) { return new Vector2(x, y); }

    // Add
    public Vector2 add(Vector2 v) { return new Vector2(x + v.x, y + v.y); }

    // Mul
    public Vector2 mul(double v) { return new Vector2(x * v, y * v); }

    // Dot
    public double dot(Vector2 v) { return x * v.x + y * v.y; }

    // Length
    public double length() { return Math.sqrt(x * x + y * y); }
    
    // Normalized
    public Vector2 normalized() {
        double l = length();
        return l == 0 ? this : new Vector2(x / l, y / l);
    }

    /* ================ [ OBJECT ] ================ */

    @Override
    public String toString() { return "(" + x + ", " + y + ")"; }
    
}
