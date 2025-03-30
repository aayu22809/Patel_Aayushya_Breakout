package com.apcs.disunity.nodes.moveaction;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.NonDrawingNode;

/**
 * A move action that performs the first action under it that succeeds
 * 
 * @author Qinzhao Li
 */
public class MoveSwitch extends MoveAction {

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public MoveSwitch() { super(); }
    public MoveSwitch(NonDrawingNode... children) { super(children); }

    /* ================ [ MOVEACTION ] ================ */

    @Override
    public Vector2 apply(Vector2 vel, double delta) {
        // Try to apply children
        for (NonDrawingNode node : getChildren()) {
            if (node instanceof MoveAction) {
                Vector2 _vel = ((MoveAction) node).apply(vel, delta);
                if (!vel.equals(_vel)) return _vel;
            }
        }
        
        return super.apply(vel, delta);
    }
    
}
