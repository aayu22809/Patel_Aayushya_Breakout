package com.apcs.disunity.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.apcs.disunity.math.Vector2;

/**
 * The base class for all nodes in the game
 * 
 * @author Qinzhao Li
 */
public abstract class Node {

    /* ================ [ FIELDS ] ================ */

    // List of children
    private final List<Node> children;

    // Constructor
    public Node() { this.children = new ArrayList<>(); }
    public Node(Node... children) { this.children = Arrays.asList(children); }

    /* ================ [ METHODS ] ================ */

    // Add child
    public void addChild(Node node) { children.add(node); }

    // Remove child
    public void removeChild(Node node) { children.remove(node); }

    // Get children
    public List<Node> getChildren() { return children; }

    // Clear children
    public void clearChildren() { children.clear(); }

    /* ================ [ NODE ] ================ */

    // Update node
    public void update(double delta) {
        // Update children
        for (Node node : children) node.update(delta);
    }
    
    // Draw node
    public void draw(Vector2 offset) {
        // Draw nodes
        for (Node node : children) node.draw(offset);
    }
    
}
