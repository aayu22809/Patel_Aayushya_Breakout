package com.apcs.disunity.nodes;

import java.util.LinkedList;
import java.util.List;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.rendering.RenderObject;

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
    public List<RenderObject> getRenderObjects(Vector2 offset) {

        List<RenderObject> output = new LinkedList<>();
        // Draw children
        for (Node<?> node : getChildren()) {
            if (node instanceof DrawnNode drawnNode)
                output.addAll(drawnNode.getRenderObjects(offset));
        }

        return output;
    }

}
