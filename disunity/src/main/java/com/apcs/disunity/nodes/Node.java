package com.apcs.disunity.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The base class for all nodes in the game
 * 
 * @author Qinzhao Li
 * @author Toshiki Takeuchi
 */
public abstract class Node<T extends Node<?>> {

    /* ================ [ FIELDS ] ================ */

    // List of children
    private final List<T> children;

    // Constructor
    public Node() { this.children = new ArrayList<>(); }
    @SafeVarargs
    public Node(T... children) { this.children = new ArrayList<>(Arrays.asList(children)); }

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
