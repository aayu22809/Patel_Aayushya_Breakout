package com.apcs.disunity.nodes.selector;

import com.apcs.disunity.nodes.Node;

import java.util.HashMap;
import java.util.List;

public class SelectorNode<K, T extends Node<?> & Indexed<K>> extends Node<T> {
    private final T fallback;
    private HashMap<K, T> children = new HashMap<>();
    private K index;

    public SelectorNode(T fallback) {
        this.fallback = fallback;
    }

    @Override
    public void addChild(T node) {
        children.put(node.index(), node);
    }

    @Override
    public List<T> getChildren() {
        return children.values().stream().toList();
    }

    public T getSelected() { return children.getOrDefault(index, fallback); }
    public T select(K index) {
        this.index = index;
        return getSelected();
    }
}
