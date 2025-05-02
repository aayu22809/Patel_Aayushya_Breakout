package com.apcs.disunity.physics;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.scenes.Scenes;

import java.util.ArrayList;

public class CollisonCheckerRuntime {
    private final ArrayList<AABB> colliders = new ArrayList<>();

    public CollisonCheckerRuntime(double delta) {
        searchColldier(Scenes.getScene(), Vector2.ZERO, colliders);

        for (int i = 0; i < colliders.size() - 1; i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                AABB a = colliders.get(i);
                AABB b = colliders.get(j);
                if(isColliding(a, b)) {
                    // Trigger collision events
                    a.COLLISION_INFO.emit(new CollisionInfo(a, b, delta, this));
                    b.COLLISION_INFO.emit(new CollisionInfo(b, b, delta, this));
//                    Signals.trigger("collision_" + a.getId(), new CollisionInfo(a, b));
//                    Signals.trigger("collision_" + b.getId(), new CollisionInfo(b, a));
                }
            }
        }
    }
    private void searchColldier(Node<?> node, Vector2 absPos, ArrayList<AABB> infos) {
        if(node instanceof Collider collider) {
            infos.add(new AABB(collider, absPos));
        } else {
            if(node instanceof Node2D<?> node2D) {
                node.getChildren()
                    .forEach(n -> searchColldier(
                        n,
                        absPos.add(node2D.getPos()),
                        infos));
            } else {
                node.getChildren()
                    .forEach(n -> searchColldier(
                        n,
                        absPos,
                        infos
                    ));
            }
        }
    }

    public boolean isColliding(AABB aabb) {
        for(AABB other: colliders) {
            if(aabb != other && isColliding(aabb, other)) return true;
        }
        return false;
    }
    /**
     * Check collision between two colliders
     */
    public boolean isColliding(AABB a, AABB b) {
        // Check actual collision
        return a.LAYER == b.LAYER &&
               Math.max(a.LB, b.LB) < Math.min(a.RB, b.RB) &&
               Math.max(a.TB, b.TB) < Math.min(a.BB, b.BB);
    }
}
