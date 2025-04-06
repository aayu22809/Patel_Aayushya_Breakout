package com.apcs.disunity.nodes;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.server.Util;

/**
 * A base class for 2D nodes with position
 * 
 * @author Qinzhao Li
 */
public class Node2D extends DrawnNode {

    /* ================ [ FIELDS ] ================ */
    
    // Position
    private Vector2 pos = Vector2.ZERO;
    private Vector2 scale = Vector2.ONE;

    // Constructors
    public Node2D() { super(); }
    public Node2D(Node<?>... children) { super(children); }
    public Node2D(Vector2 pos, Node<?>... children) { super(children); this.pos = pos; }
    public Node2D(Vector2 pos, Vector2 scale, Node<?>... children) { super(children); this.pos = pos; this.scale = scale; }

    /* ================ [ METHODS ] ================ */

    // Move an amount
    public void move(Vector2 amt) { pos = pos.add(amt); }

    // Set position
    public void setPos(Vector2 pos) { this.pos = pos; }

    // Set scale
    public void setScale(Vector2 scale) { this.scale = scale; }

    // Get position
    public Vector2 getPos() { return pos; }

    // Get scale
    public Vector2 getScale() { return scale; }
    
    /* ================ [ NODE ] ================ */

    @Override
    public void draw(Vector2 offset) {
        // Draw children relative to this
        super.draw(pos.add(offset));
    }

    /* ================ [ SYNCED ] ================ */

    @Override
    public byte[] supply(int recipient) {
        return pos.getBytes();
    }

    @Override
    public int receive(int sender, byte[] data) {
        if (sender == 0) return 0;
        pos = Vector2.of(Util.getInt(data, 0), Util.getInt(data, Integer.BYTES));
        return Integer.BYTES * 2;
    }

}
