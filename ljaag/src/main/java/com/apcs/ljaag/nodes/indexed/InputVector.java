package com.apcs.ljaag.nodes.indexed;

import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;

public class InputVector extends VectorSupplier {
    private final String name;

    public InputVector(String name) {
        this.name = name;
    }

    @Override
    public String index() {
        return name;
    }

    @Override
    public Vector2 get() {
        return Vector2.of(
            (Inputs.getAction("left") ? -1 : 0) + (Inputs.getAction("right") ? 1 : 0),
            (Inputs.getAction("up") ? -1 : 0) + (Inputs.getAction("down") ? 1 : 0)
        ).normalized().mul(100);
    }
}
