package com.apcs.disunity.game.signals;

import com.apcs.disunity.game.selector.Selector;

import java.util.function.Consumer;

/**
 * bundles multiple signals into one object to communicate various information
 * 
 * @author Qinzhao Li
 */
public class SignalBus {
    private static final Signal<Object> FALLBACK = new Signal<>(Object.class) {
        @Override public void connect(Consumer<Object> callable) {}
        @Override public void disconnect(Consumer<Object> callable) {}
        @Override public void emit(Object message) {}
    };
    private final Selector<Class<?>, Signal<?>> SIGNALS = new Selector<>(FALLBACK);

    /* ================ [ METHODS ] ================ */

    public <T> Signal<T> getSignal(Class<T> type) { return (Signal<T>) SIGNALS.select(type); }

    // Connect a signal to a function
    public <T> void connect(Class<T> type, Consumer<T> function) {
        Signal<T> signal = getSignal(type);
        if (signal == FALLBACK) {
            signal = new Signal<>(type);
            SIGNALS.add(signal);
        }

        signal.connect(function);
    }

    // Disconnect a signal from a function
    public <T> void disconnect(Class<T> type, Consumer<T> function) {
        getSignal(type).disconnect(function);
    }

    // Trigger all functions connected to a signal
    @SuppressWarnings("unchecked")
    public <T> void emit(T message) {
        ((Signal<T>) getSignal(message.getClass())).emit(message);
    }
}
