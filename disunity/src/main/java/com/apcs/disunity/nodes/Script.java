package com.apcs.disunity.nodes;

import java.util.function.Consumer;

import com.apcs.disunity.math.Vector2;

public abstract class Script<T extends Node> extends Node {

    protected final T parent;

    public Script(T parent) {
        this.parent = parent;
    }


    @Override
    public final void draw(Vector2 offset) { }

    public static <T extends Node> Script<T> of(T parent, Consumer<Double> action) {
        Script<T> s = new Script<>(parent) {

            @Override
            public void update(double delta) {
                action.accept(delta);
            }

        };

        return s;
    }
    
}
