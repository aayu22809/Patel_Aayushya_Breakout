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
public abstract class Node<T extends Node> {

    /* ================ [ FIELDS ] ================ */

    // List of children
    private final List<T> children;

    // Constructor
    public Node() { this.children = new ArrayList<>(); }
    public Node(T... children) { this.children = Arrays.asList(children); }

    /* ================ [ METHODS ] ================ */

    // Add child
    public void addChild(T node) { children.add(node); }

    // Remove child
    public void removeChild(T node) { children.remove(node); }

    // Get children
    public List<T> getChildren() { return children; }

    // Clear children
    public void clearChildren() { children.clear(); }

    /* ================ [ NODE ] ================ */

    // Update node
    public void update(double delta) {
        // Update children
        for (T node : children) node.update(delta);
    }
}
