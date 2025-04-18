package com.apcs.ljaag.nodes.action;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.UndrawnNode;
import com.apcs.disunity.nodes.action.MoveAction;

/**
 * A move action that allows directional movement
 *
 * @author Qinzhao Li
 */
public class WalkAction extends MoveAction<Vector2> {
    
    /* ================ [ ATTRIBUTES ] ================ */

    // Speed
    private double speed = 100;

    /* ================ [ FIELDS ] ================ */

    // Walk direction
    private Vector2 dir = Vector2.ZERO;

    // Constructors
    public WalkAction() { super(); }
    public WalkAction(UndrawnNode... children) { super(children); }

    /* ================ [ APPLIABLE ] ================ */

    @Override
    public Vector2 apply(Vector2 vel, double delta) { return dir.mul(speed); }

    /* ================ [ ACTION ] ================ */

    @Override
    public String actionId() { return "walk"; }
    
    @Override
    public void trigger(Vector2 data) { this.dir = data; }

}
