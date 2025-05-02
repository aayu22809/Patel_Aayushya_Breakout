package com.apcs.disunity.nodes;

import com.apcs.disunity.annotations.syncedfield.SyncedObject;
import com.apcs.disunity.math.Transform;

/**
 * A base class for 2D nodes with position
 * 
 * @author Qinzhao Li
 */
public class Node2D<T extends Node<?>> extends Node<T> {

    /* ================ [ FIELDS ] ================ */
    
    // Transform
    @SyncedObject
    public Transform transform;

    // Constructors
    public Node2D(T... children) { this(new Transform(), children); }
    public Node2D(Transform transform, T... children) { super(children); this.transform = transform; }

    /* ================ [ NODE ] ================ */

    @Override
    public void draw(Transform offset) {
        // Draw children relative to this
        super.draw(transform.apply(offset));
    }
}
