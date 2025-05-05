package com.apcs.disunity.game.nodes.signals;

import com.apcs.disunity.game.nodes.selector.SelectorNode;

import java.util.function.Consumer;

/**
 * Handles node communication through signals
 * 
 * @author Qinzhao Li
 */
public class SignalBus extends SelectorNode<Class<?>, Signal<?>> {

    public SignalBus(Signal<?> fallback) {
        super(fallback);
    }

    /* ================ [ METHODS ] ================ */

    public <T> Signal<T> select(Class<T> type) {
        return (Signal<T>) super.select(type);
    }

    // Connect a signal to a function
    public <T> void connect(Class<T> type, Consumer<T> function) {
        Signal<T> signal = select(type);
        if (signal == null) {
            signal = new Signal<>(type);
            addChild(signal);
        }

        signal.connect(function);
    }

    // Disconnect a signal from a function
    public <T> void disconnect(Class<T> type, Consumer<T> function) {
        Signal<T> signal = select(type);
        if (signal != null) {
            signal.disconnect(function);
        }
    }

    // Trigger all functions connected to a signal
    @SuppressWarnings("unchecked")
    public <T> void emit(T message) {
        Signal<T> signal = (Signal<T>) select(message.getClass());
        if (signal != null) { signal.emit(message); }
    }
}
