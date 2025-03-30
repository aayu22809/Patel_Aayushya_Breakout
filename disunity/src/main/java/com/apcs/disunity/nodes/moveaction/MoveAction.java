package com.apcs.disunity.nodes.moveaction;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.UndrawnNode;

/**
 * A node that performs a movement action
 * 
 * @author Qinzhao Li
 */
public abstract class MoveAction<T> extends UndrawnNode {

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public MoveAction() { super(); }
    public MoveAction(UndrawnNode... children) { super(children); }

    /* ================ [ MOVEACTION ] ================ */

    // Trigger the node
    public abstract void trigger(T data);

    // Apply action to a velocity
    public Vector2 apply(Vector2 vel, double delta) { return vel; }
    
}
