package com.apcs.disunity.nodes;

import com.apcs.disunity.annotations.syncedfield.SyncedBoolean;
import com.apcs.disunity.math.Transform;

/**
 * A node that has children which may draw to a buffer
 * 
 * @author Toshiki Takeuchi
 */
public abstract class DrawnNode extends Node<Node<?>> {
    
    /* ================ [ FIELDS ] ================ */

    @SyncedBoolean
    private boolean isVisible = true;

    // Constructors
    public DrawnNode() { super(); }
    public DrawnNode(boolean visible) { super(); isVisible = visible; }
    public DrawnNode(Node<?>... nodes) { super(nodes); }
    public DrawnNode(boolean visible, Node<?>... nodes) { super(nodes); isVisible = visible; }

    /* ================ [ METHODS ] ================ */

    // Draws this node and its children to the buffer
    public void draw(Transform offset) {
        for (DrawnNode node : getChildren(DrawnNode.class)) {
            if (node.isVisible()) {
                node.draw(offset);
            }
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

}
