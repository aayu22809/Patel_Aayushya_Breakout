package com.apcs.disunity.physics;

import com.apcs.disunity.signals.Signals;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages physics and collision detection for all nodes
 * @author Aayushya Patel
 */
public class PhysicsManager {

    private static final PhysicsManager instance = new PhysicsManager();
    private final List<Collider> colliders = new ArrayList<>();

    // Private constructor to enforce singleton pattern
    private PhysicsManager() {}

    /**
     * Get the singleton instance
     */
    public static PhysicsManager getInstance() {
        return instance;
    }

    /**
     * Register a collider with the physics system
     */
    public void registerCollider(Collider collider) {
        colliders.add(collider);
    }

    /**
     * Unregister a collider from the physics system
     */
    public void unregisterCollider(Collider collider) {
        colliders.remove(collider);
    }

    /**
     * Update the physics system
     */
    public void update(double delta) {
        // Check all colliders against each other
        for (int i = 0; i < colliders.size(); i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                checkCollision(colliders.get(i), colliders.get(j));
            }
        }
    }

    /**
     * Check collision between two colliders
     */
    private void checkCollision(Collider a, Collider b) {
        // Check actual collision
        if (a.getBounds().intersects(b.getBounds())) {
            // Trigger collision events
            Signals.trigger("collision_" + a.getId(), new CollisionInfo(a, b));

            Signals.trigger("collision_" + b.getId(), new CollisionInfo(b, a));
        }
    }
}
