package com.apcs.disunity.nodes.body;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.moveaction.MoveAction;
import com.apcs.disunity.server.Util;

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
        if (sender == 0) return 0;
        int used = super.receive(sender,data);
        vel = Vector2.of(Util.getInt(data, used), Util.getInt(data, used + Integer.BYTES));
        return used + Integer.BYTES * 2;
    }
}
