package com.apcs.disunity.game.signals;

import com.apcs.disunity.game.nodes.selector.Indexed;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/// allows nodes to send data across tree structure.
public class Signal<T> implements Indexed<Class<?>> {
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
