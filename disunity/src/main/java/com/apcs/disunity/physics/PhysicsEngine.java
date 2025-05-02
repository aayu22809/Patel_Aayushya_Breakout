package com.apcs.disunity.physics;

/**
 * Manages physics and collision detection for all nodes
 * @author Aayushya Patel
 */
public class PhysicsEngine {

    private static final PhysicsEngine instance = new PhysicsEngine();

    // Private constructor to enforce singleton pattern
    private PhysicsEngine() {}

    /**
     * Get the singleton instance
     */
    public static PhysicsEngine getInstance() {
        return instance;
    }

    /**
     * Update the physics system
     */
    public void update(double delta) {
        new CollisonCheckerRuntime(delta);
    }

}
