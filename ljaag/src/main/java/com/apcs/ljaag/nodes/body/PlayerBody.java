package com.apcs.ljaag.nodes.body;

import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.body.Body;
import com.apcs.ljaag.nodes.moveaction.WalkAction;

/**
 * A body that is controlled by player inputs
 * 
 * @author Qinzhao Li
 */
public class PlayerBody extends Body {

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public PlayerBody() { super(); }
    public PlayerBody(Node<?>... children) { super(children); }
    public PlayerBody(Vector2 pos, Node<?>... children) { super(pos, children); }

    /* ================ [ NODE ] ================ */

    @Override
    public void update(double delta) {
        // Trigger movement nodes
        for (Node<?> node : getChildren()) {
            // Walk action
            if (node instanceof WalkAction) {
                Vector2 input = new Vector2(
                    (Inputs.getAction("left") ? -1 : 0) + (Inputs.getAction("right") ? 1 : 0),
                    (Inputs.getAction("up") ? -1 : 0) + (Inputs.getAction("down") ? 1 : 0)
                ).normalized();

                ((WalkAction) node).trigger(input.mul(100));
            }
        }

        super.update(delta);
    }
    
}
