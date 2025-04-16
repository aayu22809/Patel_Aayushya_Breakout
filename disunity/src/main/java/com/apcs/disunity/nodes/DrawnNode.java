package com.apcs.disunity.nodes;

import com.apcs.disunity.math.Transform;

/**
 * A node that has children which may draw to a buffer
 * 
 * @author Toshiki Takeuchi
 */
public abstract class DrawnNode extends Node<Node<?>> {
    
    /* ================ [ FIELDS ] ================ */

    // Constructors
    public DrawnNode() { super(); }
    public DrawnNode(Node<?>... nodes) { super(nodes); }

    /* ================ [ METHODS ] ================ */

    // Draws this node and its children to the buffer
    public void draw(Transform offset) {
        for (DrawnNode node : getChildren(DrawnNode.class)) {
            node.draw(offset);
        }
    }

}
