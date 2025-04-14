package com.apcs.disunity.nodes.action;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.UndrawnNode;
import com.apcs.disunity.nodes.controller.Controlled;
import com.apcs.disunity.signals.Signals;

/**
 * A node that performs a movement action
 * 
 * @author Qinzhao Li
 */
public abstract class MoveAction<T> extends UndrawnNode implements Controlled {

    /* ================ [ FIELDS ] ================ */

    // Controller id
    private int controller;

    // Constructors
    public MoveAction() { super(); }
    public MoveAction(UndrawnNode... children) { super(children); }

    /* ================ [ CONTROLLED ] ================ */

    // Set controller id
    public void setController(int controller) { this.controller = controller; }

    /* ================ [ NODE ] ================ */

    @Override
    public void initialize() {
        // Connect to signal
        Signals.connect(Signals.getSignal(controller, this.actionId()), this::trigger);

        // Complete initialization
        super.initialize();
    }

    /* ================ [ MOVEACTION ] ================ */

    // Get action id
    public abstract String actionId();

    // Trigger the action
    public abstract void trigger(T data);

    // Apply action to a velocity
    public Vector2 apply(Vector2 vel, double delta) { return vel; }
    
}
