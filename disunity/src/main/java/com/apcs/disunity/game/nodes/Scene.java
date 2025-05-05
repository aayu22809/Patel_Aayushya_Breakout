package com.apcs.disunity.game.nodes;

import com.apcs.disunity.game.nodes.selector.Indexed;

public class Scene extends Node<Node<?>> implements Indexed<String> {
    private final String name;
    public Scene(String name, Node<?>... children) {
        super(children);
        this.name = name;
    }
    @Override
    public String index() {
        return name;
    }
}
