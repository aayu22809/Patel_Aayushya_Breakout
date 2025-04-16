package com.apcs.disunity.nodes.body;

import com.apcs.disunity.annotations.Requires;
import com.apcs.disunity.appliable.TransformAppliable;
import com.apcs.disunity.appliable.VelocityAppliable;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.controller.Controllable;
import com.apcs.disunity.nodes.controller.Controller;
import com.apcs.disunity.server.Util;

/**
 * A 2d node that can handle movement and collision
 * 
 * @author Qinzhao Li
 */
@Requires(nodes = {Controller.class})
public class Body extends Node2D {

    /* ================ [ FIELDS ] ================ */

    // Controller id
    private int controller;

    // Velocity
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
        for (Controllable action : getChildren(Controllable.class)) {
            action.setController(controller);
        }

        // Complete initialization
        super.initialize();
    }

    @Override
    public void update(double delta) {
        // Apply velocity nodes
        for (VelocityAppliable va : getChildren(VelocityAppliable.class)) {
            vel = va.apply(vel, delta);
        }

        // Apply transform nodes
        for (TransformAppliable ta : getChildren(TransformAppliable.class)) {
            transform = ta.apply(transform, delta);
        }

        // Move with velocity
        transform = transform.move(vel.mul(delta));

        // Update children
        super.update(delta);
    }

    /* ================ [ SYNCED ] ================ */

    @Override
    public byte[] supply(int recipient) {
        byte[] superPacket = super.supply(recipient);
        byte[] velPacket = vel.getBytes();
        byte[] packet = new byte[superPacket.length + velPacket.length];
        System.arraycopy(superPacket,0,packet,0, superPacket.length );
        System.arraycopy(velPacket,0,packet,superPacket.length,velPacket.length);
        return packet;
    }

    @Override
    public int receive(int sender, byte[] data) {
        int used = super.receive(sender,data);
        vel = Vector2.of(Util.getInt(data, used), Util.getInt(data, used + Integer.BYTES));
        return used + Integer.BYTES * 2;
    }
    
}
