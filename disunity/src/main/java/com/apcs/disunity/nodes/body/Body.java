package com.apcs.disunity.nodes.body;

import com.apcs.disunity.annotations.Requires;
import com.apcs.disunity.annotations.SyncedField;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.action.MoveAction;
import com.apcs.disunity.nodes.controller.Controlled;
import com.apcs.disunity.nodes.controller.Controller;
import com.apcs.disunity.server.Util;

/**
 * A 2d node that can handle movement and collision
 * 
 * @author Qinzhao Li
 */
@Requires(nodes = {Controller.class})
public class Body extends Node2D {
    // MUST DO: remove this
    public int clientId;

    /* ================ [ FIELDS ] ================ */

    // Controller id
    private int controller;

    // Velocity
    @SyncedField
    private Vector2 vel = Vector2.ZERO;

    // Constructors
    public Body() { super(); }
    public Body(Node<?>... children) { super(children); }
    public Body(Transform transform, Node<?>... children) { super(transform, children); }

    /* ================ [ METHODS ] ================ */

    // Set velocity
    public void setVel(Vector2 vel) { this.vel = vel; }

    // Get velocity
    public Vector2 getVel() { return vel; }

    /* ================ [ NODE ] ================ */

    @Override
    public void initialize() {
        // Grab controller id
        this.controller = getChild(Controller.class).getId();

        // Update children controllers
        for (Controlled action : getChildren(Controlled.class)) {
            action.setController(controller);
        }

        // Complete initialization
        super.initialize();
    }

    @Override
    public void update(double delta) {
        // Apply movement nodes
        for (MoveAction<?> action : getChildren(MoveAction.class)) {
            vel = action.apply(vel, delta);
        }

        // Move with velocity
        transform = transform.move(vel.mul(delta));

        // Update children
        super.update(delta);
    }

}
