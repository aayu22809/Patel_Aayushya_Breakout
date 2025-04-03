package com.apcs.disunity.nodes;

import com.apcs.disunity.server.Synced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * The base class for all nodes in the game
 * 
 * @author Qinzhao Li
 * @author Toshiki Takeuchi
 */
public abstract class Node<T extends Node<?>> implements Synced {

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

    /// Overload for default behavior of {@link #print(boolean, Function, List)}.
    /// Prints node names in tree structure
    public void print() {
        print(
          true,
          n -> "○ " + n.getClass().getSimpleName(),
          new ArrayList<>()
        );
    }

    /// method that prints information and children of node recursively in a tree format.
    /// @param isLast indicates if this node is last child of parent
    /// @param formatter function that formats provided node to printed string
    /// @param indent string used to indent this node
    private void print(boolean isLast, Function<Node<?>, String> formatter, List<String> indent) {
        indent.forEach(System.out::print);
        System.out.print(isLast ? "└ " : "├ ");
        System.out.println(formatter.apply(this));

        if (isLast) indent.add("  ");
        else indent.add("│ ");

        for (int i = 0; i < children.size() - 1; i++) {
            ((Node<?>) children.get(i)).print(false, formatter, indent);
        }
        if (!children.isEmpty()) {
            ((Node<?>) children.getLast()).print(true, formatter, indent);
        }

        indent.removeLast();
    }
}
