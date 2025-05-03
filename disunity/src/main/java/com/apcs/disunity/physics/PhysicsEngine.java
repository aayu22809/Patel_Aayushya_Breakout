package com.apcs.disunity.physics;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.scenes.Scenes;

import java.util.ArrayList;

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
        ArrayList<ColliderInfo> colliders = new ArrayList<>();

        searchCollider(Scenes.getScene(), Vector2.ZERO, colliders);

        // nC2 loop
        for (int i = 0; i < colliders.size() - 1; i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                ColliderInfo a = colliders.get(i);
                ColliderInfo b = colliders.get(j);
                if (a.AABB.isColliding(b.AABB)) {
                    // Trigger collision events
                    a.COLLISION_INFO.emit(new CollisionInfo(a.AABB, b.AABB, delta));
                    b.COLLISION_INFO.emit(new CollisionInfo(b.AABB, a.AABB, delta));
//                  Signals.trigger("collision_" + a.getId(), new CollisionInfo(a, b));
//                  Signals.trigger("collision_" + b.getId(), new CollisionInfo(b, a));
                }
            }
        }
    }
    
    private void searchCollider(Node<?> node, Vector2 absPos, ArrayList<ColliderInfo> infos) {
        if(node instanceof Collider collider) {
            infos.add(new ColliderInfo(collider, absPos.add(collider.getPos())));
        } else {
            if(node instanceof Node2D<?> node2D) {
                node.getChildren()
                    .forEach(n -> searchCollider(
                        n,
                        absPos.add(node2D.getPos()),
                        infos));
            } else {
                node.getChildren()
                    .forEach(n -> searchCollider(
                        n,
                        absPos,
                        infos
                    ));
            }
        }
    }

}
