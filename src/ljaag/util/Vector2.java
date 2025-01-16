package ljaag.util;

public class Vector2 {

    /* ============== [ FIELDS ] ============= */

    /** X position */
    public final double x;
    /** Y position */
    public final double y;

    /** Construct a Vector2 */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /* ============== [ METHODS ] ============= */

    /** Add a vector to this one */
    public Vector2 add(Vector2 vec) { return new Vector2(x + vec.x, y + vec.y); }
    
}
