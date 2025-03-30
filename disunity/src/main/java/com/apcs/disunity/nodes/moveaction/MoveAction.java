package com.apcs.disunity.nodes.moveaction;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.NonDrawingNode;

/**
 * A node that performs a movement action
 * 
 * @author Qinzhao Li
 */
public abstract class MoveAction extends NonDrawingNode{

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public MoveAction() { super(); }
    public MoveAction(NonDrawingNode... children) { super(children); }

    /* ================ [ MOVEACTION ] ================ */

    // Apply action
    public Vector2 apply(Vector2 vel, double delta) { return vel; }
    
}
