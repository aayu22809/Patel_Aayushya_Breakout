package com.apcs.disunity.nodes;

import com.apcs.disunity.math.Vector2;

/**
 * A base class for 2D nodes with position
 * 
 * @author Qinzhao Li
 */
public class Node2D extends DrawnNode {

    /* ================ [ FIELDS ] ================ */
    
    // Position
    private Vector2 pos = new Vector2();

    // Constructors
    public Node2D() { super(); }
    public Node2D(Node<?>... children) { super(children); }
    public Node2D(Vector2 pos, Node<?>... children) { super(children); this.pos = pos; }

    /* ================ [ METHODS ] ================ */

    // Move an amount
    public void move(Vector2 amt) { pos = pos.add(amt); }

    // Set position
    public void setPos(Vector2 pos) { this.pos = pos; }

    // Get position
    public Vector2 getPos() { return pos; }
    
    /* ================ [ NODE ] ================ */

    @Override
    public void draw(Vector2 offset) {
        // Draw children relative to this
        super.draw(pos.add(offset));
    }
}
