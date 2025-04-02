package com.apcs.disunity.nodes;

import java.util.List;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.rendering.RenderObject;

/**
 * A base class for 2D nodes with position
 * 
 * @author Qinzhao Li
 */
public class Node2D extends DrawnNode {

    /* ================ [ FIELDS ] ================ */
    
    // Position
    private Vector2 pos = new Vector2();
    private Vector2 scale = Vector2.getCongruenceScaleFactor();

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

    // Get position
    public Vector2 getPos() { return pos; }

    public Vector2 getScale() { return scale; }

    public void setScale(Vector2 scale) { this.scale = scale; }
    
    /* ================ [ NODE ] ================ */

    @Override
    public List<RenderObject> getRenderObjects(Vector2 offset) {
        // Draw children relative to this
        return super.getRenderObjects(pos.add(offset));
    }
}
