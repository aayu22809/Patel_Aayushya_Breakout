package com.apcs.disunity.physics;

import com.apcs.disunity.math.Vector2;

/**
 * A rectangle for collision detection
 * @author Aayushya Patel
 */
public class Rectangle {

    private final Vector2 position; // Top-left corner
    private final Vector2 size;

    public Rectangle(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
    }

    /**
     * Check if this rectangle intersects with another
     */
    public boolean intersects(Rectangle other) {
        // Check if one rectangle is to the left of the other
        if (
            position.x + size.x < other.position.x ||
            other.position.x + other.size.x < position.x
        ) {
            return false;
        }

        // Check if one rectangle is above the other
        if (
            position.y + size.y < other.position.y ||
            other.position.y + other.size.y < position.y
        ) {
            return false;
        }

        // If neither, the rectangles intersect
        return true;
    }

    /**
     * Get the position of the rectangle
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Get the size of the rectangle
     */
    public Vector2 getSize() {
        return size;
    }
}
