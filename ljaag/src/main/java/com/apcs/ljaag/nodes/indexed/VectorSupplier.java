package com.apcs.ljaag.nodes.indexed;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.game.nodes.Node;
import com.apcs.disunity.game.nodes.selector.Indexed;

import java.util.function.Supplier;

/// a selector entree that generates a velocity
public abstract class VectorSupplier extends Node<Node<?>> implements Supplier<Vector2>, Indexed<String> {
    public static final VectorSupplier ZERO = new VectorSupplier() {
        public String index() { return "ZERO"; }
        public Vector2 get() { return Vector2.ZERO; }
    };
}
