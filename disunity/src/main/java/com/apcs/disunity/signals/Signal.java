package com.apcs.disunity.signals;

import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.selector.Indexed;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Signal<T> extends Node<Node<?>> implements Indexed<Class<?>> {
    private final Class<T> type;
    private final List<Consumer<T>> connections = new ArrayList<>();

    public Signal(Class<T> type) {
        this.type = type;
    }

    public void connect(Consumer<T> callable) {
        connections.add(callable);
    }
    public void disconnect(Consumer<T> callable) {
        connections.remove(callable);
    }
    public void emit(T message) {
        connections.forEach(c -> c.accept(message));
    }

    @Override
    public Class<T> index() {
        return type;
    }
}
