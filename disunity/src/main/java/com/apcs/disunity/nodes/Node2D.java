package com.apcs.disunity.nodes;

import com.apcs.disunity.annotations.SyncedField;
import com.apcs.disunity.math.Transform;

/**
 * A base class for 2D nodes with position
 * 
 * @author Qinzhao Li
 */
public class Node2D extends DrawnNode {

    /* ================ [ FIELDS ] ================ */
    
    // Transform
    @SyncedField
    public Transform transform = new Transform();

    // Constructors
    public Node2D() { super(); }
    public Node2D(Node<?>... children) { super(children); }
    public Node2D(Transform transform, Node<?>... children) { super(children); this.transform = transform; }
    
    /* ================ [ NODE ] ================ */

    @Override
    public void draw(Transform offset) {
        // Draw children relative to this
        super.draw(transform.apply(offset));
    }

    /* ================ [ SYNCED ] ================ */
}
