package com.apcs.disunity.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.apcs.disunity.math.Vector2;

/**
 * A base class for 2D nodes with position and children
 * 
 * @author Qinzhao Li
 */
public class Node2D extends Node {

    /* ================ [ FIELDS ] ================ */
    
    // List of children
    protected final List<Node> children;
    
    // Node position
    protected Vector2 pos = new Vector2();

    // Constructors
    public Node2D() { this.children = new ArrayList<>(); }
    public Node2D(Node... children) { this.children = Arrays.asList(children); }
    public Node2D(Vector2 pos) { this.pos = pos; this.children = new ArrayList<>(); }
    public Node2D(Vector2 pos, Node... children) { this.pos = pos; this.children = Arrays.asList(children); }

    /* ================ [ METHODS ] ================ */

    // Add child
    public void addChild(Node node) { children.add(node); }

    // Remove child
    public void removeChild(Node node) { children.remove(node); }

    // Move
    public void move(Vector2 amt) { pos = pos.add(amt); }

    // Get position
    public Vector2 getPos() { return pos; }
    
    /* ================ [ NODE ] ================ */

    @Override
    public void update(double delta) {
        // Update children
        for (Node node : children) node.update(delta);
    }

    @Override
    public void draw(Vector2 offset) {
        // Draw nodes
        for (Node node : children) node.draw(pos.add(offset));
    }
    
}
