package com.apcs.ljaag.nodes.action;

import com.apcs.disunity.appliable.TransformAppliable;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.UndrawnNode;

/**
 * A move action that allows turning
 *
 * @author Qinzhao Li
 */
public class TurnAction extends UndrawnNode implements TransformAppliable {

    /* ================ [ FIELDS ] ================ */

    // Previous facing
    private int facing = 1;

    // Previous x position
    private double prevX = 0;

    /* ================ [ APPLIABLE ] ================ */

    @Override
    public Transform apply(Transform transform, double delta) {
        Transform res = transform;

        // Move direction
        double moving = transform.pos.x - prevX;

        // Check for turn
        if (Math.abs(moving) > 0.1 && (int) Math.signum(moving) != facing) {
            res = res.scale(Vector2.of(-1, 1));
            facing = -facing;
        }

        // Update previous x
        prevX = transform.pos.x;
        return res;
    }
    
}
