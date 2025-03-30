package com.apcs.disunity.nodes.moveaction;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;

/**
 * A node that performs a movement action
 * 
 * @author Qinzhao Li
 */
public class MoveAction extends Node {

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public MoveAction() { super(); }
    public MoveAction(Node... children) { super(children); }

    /* ================ [ MOVEACTION ] ================ */

    // Apply action
    public Vector2 apply(Vector2 vel, double delta) { return vel; }
    
}
