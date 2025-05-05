package com.apcs.disunity.game.nodes.selector;

import com.apcs.disunity.app.network.packet.annotation.SyncedObject;
import com.apcs.disunity.math.Transform;
import com.apcs.disunity.game.nodes.Node;

import java.util.HashMap;
import java.util.List;

public class SelectorNode<K, T extends Node<?> & Indexed<K>> extends Node<T> {

    private final T fallback;
    protected final HashMap<K, T> children = new HashMap<>();

    @SyncedObject
    private K index;

    public SelectorNode(T fallback, T... children) {
        super();
        this.fallback = fallback;
        this.index = fallback.index();
        addChild(fallback);
        addChildren(children);
    }

    // stop update propagation
    @Override
    public void update(double delta) {
        getSelected().update(delta);
    }

    @Override
    public void draw(Transform transform) {
        getSelected().draw(transform);
    }

    @Override
    public void addChild(T node) {
        children.put(node.index(), node);
    }

    @Override
    public List<T> getDynamicChildren() {
        return children.values().stream().toList();
    }

    public T getSelected() { return children.getOrDefault(index, fallback); }
    public T select(K index) {
        this.index = index;
        return getSelected();
    }
}
