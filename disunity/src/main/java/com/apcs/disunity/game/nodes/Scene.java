package com.apcs.disunity.game.nodes;

import com.apcs.disunity.game.nodes.selector.Indexed;
import com.apcs.disunity.game.physics.PhysicsEngine;
import com.apcs.disunity.game.signals.SignalBus;

public class Scene extends Node<Node<?>> implements Indexed<String> {
    public final SignalBus GLOBAL_SIGNAL_BUS = new SignalBus();
    private final String name;
    public Scene(String name, Node<?>... children) {
        super(children);
        this.name = name;
    }
    @Override
    public String index() {
        return name;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        PhysicsEngine.run(this,delta);
    }
}
