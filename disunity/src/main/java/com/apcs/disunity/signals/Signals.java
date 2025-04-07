package com.apcs.disunity.signals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Handles node communication through signals
 * 
 * @author Qinzhao Li
 */
@SuppressWarnings("unchecked")
public class Signals {

    /* ================ [ FIELDS ] ================ */

    // Maps signal names to their signal
    private static final Map<String, Signal<?>> signalIds = new HashMap<>();

    // Maps signals to their functions
    private static final Map<Signal<?>, List<Consumer<?>>> signals = new HashMap<>();

    /* ================ [ METHODS ] ================ */

    // Get a signal by name
    public static <T> Signal<T> getSignal(int controller, String name) { return getSignal(controller + "_" + name); };
    public static <T> Signal<T> getSignal(String name) { return (Signal<T>) signalIds.get(name); }

    // Register a signal
    public static void registerSignal(String name) { signalIds.put(name, new Signal<>(name)); }

    // Connect a signal to a function
    public static <T> void connect(Signal<T> signal, Consumer<T> function) {
        if (!signals.containsKey(signal)) {
            signals.put(signal, new ArrayList<>());
        }

        signals.get(signal).add(function);
    }

    // Disconnect a signal from a function
    public static <T> void disconnect(Signal<T> signal, Consumer<T> function) {
        if (signals.containsKey(signal)) {
            signals.get(signal).remove(function);
        }
    }

    // Trigger all functions connected to a signal
    public static <T> void trigger(Signal<T> signal, T data) {
        if (signals.containsKey(signal)) {
            for (Consumer<?> consumer : signals.get(signal)) {
                ((Consumer<T>) consumer).accept(data);
            }
        }
    }
    
}
