package com.apcs.disunity.nodes.body;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.moveaction.MoveAction;

/**
 * A 2d node that can handle movement and collision
 * 
 * @author Qinzhao Li
 */
public class Body extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Velocity
    private Vector2 vel = Vector2.ZERO;

    // Constructors
    public Body() { super(); }
    public Body(Node<?>... children) { super(children); }
    public Body(Vector2 pos, Node<?>... children) { super(pos, children); }

    /* ================ [ METHODS ] ================ */

    // Set velocity
    public void setVel(Vector2 vel) { this.vel = vel; }

    // Get velocity
    public Vector2 getVel() { return vel; }

    /* ================ [ NODE ] ================ */

    @Override
    public void update(double delta) {
        // Apply movement nodes
        for (Node<?> node : getChildren()) {
            if (node instanceof MoveAction<?> action) {
                vel = action.apply(vel, delta);
            }
        }

        // Move with velocity
        move(vel.mul(delta));

        // Update children
        super.update(delta);
    }
    
}
