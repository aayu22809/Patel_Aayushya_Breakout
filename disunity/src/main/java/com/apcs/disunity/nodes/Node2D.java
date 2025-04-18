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

    // TODO: Fix these with transform implementation

    @Override
    public byte[] supply(int recipient) {
        return new byte[0];
        // return transform.getBytes();
    }

    @Override
    public int receive(int sender, byte[] data) {
        return 0;
        // pos = Vector2.of(Util.getInt(data, 0), Util.getInt(data, Integer.BYTES));
        // return Integer.BYTES * 2;
    }

}
