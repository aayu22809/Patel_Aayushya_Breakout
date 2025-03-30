package com.apcs.disunity.nodes;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;

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

    /// draws content of this node to buffer.
    /// use {@link Game#getInstance()} and {@link Game#getBuffer()} to access current buffer.
    /// @param offset where the node should be drawn
    public abstract void draw(Vector2 offset);

}
