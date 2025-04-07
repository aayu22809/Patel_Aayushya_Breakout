package com.apcs.disunity.nodes.moveaction;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.UndrawnNode;
import com.apcs.disunity.signals.Signals;

/**
 * A node that performs a movement action
 * 
 * @author Qinzhao Li
 */
public abstract class MoveAction<T> extends UndrawnNode {

    /* ================ [ FIELDS ] ================ */

    // Action id
    protected static final String actionId = "move";

    // Controller id
    private int controller;

    // Constructors
    public MoveAction() { super(); }
    public MoveAction(UndrawnNode... children) { super(children); }

    /* ================ [ METHODS ] ================ */

    // Set controller id
    public void setController(int controller) { this.controller = controller; }

    /* ================ [ NODE ] ================ */

    @Override
    public void initialize() {
        // Connect to signal
        Signals.connect(Signals.getSignal(controller, actionId), this::trigger);

        // Complete initialization
        super.initialize();
    }

    /* ================ [ MOVEACTION ] ================ */

    // Trigger the action
    public abstract void trigger(T data);

    // Apply action to a velocity
    public Vector2 apply(Vector2 vel, double delta) { return vel; }
    
}
