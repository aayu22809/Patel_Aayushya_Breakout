package com.apcs.ljaag;

import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Script;
import com.apcs.disunity.nodes.Sprite;

public class Movement extends Script<Sprite> {

    public Movement(Sprite parent) {
        super(parent);
    }

    @Override
    public void update(double delta) {
        Vector2 input = new Vector2(
            (Inputs.getAction("left") ? -1 : 0) + (Inputs.getAction("right") ? 1 : 0),
            (Inputs.getAction("up") ? -1 : 0) + (Inputs.getAction("down") ? 1 : 0)
        ).normalized();

        parent.move(input.mul(delta).mul(100));
    }
    
}
