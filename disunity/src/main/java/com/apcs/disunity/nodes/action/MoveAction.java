package com.apcs.disunity.nodes.action;

import com.apcs.disunity.appliable.VelocityAppliable;
import com.apcs.disunity.nodes.UndrawnNode;
import com.apcs.disunity.nodes.controller.Controllable;

/**
 * A node that performs a movement action
 * 
 * @author Qinzhao Li
 */
public abstract class MoveAction<T> extends GameAction<T> implements VelocityAppliable, Controllable {

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public MoveAction() { super(); }
    public MoveAction(UndrawnNode... children) { super(children); }

    /* ================ [ CONTROLLABLE ] ================ */

    // Set controller id
    public void setController(int controller) { setSource(controller); }
    
}
