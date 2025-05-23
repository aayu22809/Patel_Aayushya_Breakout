package com.apcs.disunity.app.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.apcs.disunity.app.input.actions.Action;
import com.apcs.disunity.app.input.actions.ActionSet;

import com.apcs.disunity.math.Vector2;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A manager that handles all inputs and their actions.
 * 
 * @author Qinzhao Li
 */
public class Inputs {

    /* ================ [ FIELDS ] ================ */

    // Maps actions to their respective bindings
    private static Map<String, ActionSet> actions = new HashMap<>();

    // Maps inputs to their pressed state
    private static final Set<Input> inputs = new HashSet<>();

    // Mouse position on the screen
    public static Vector2 mousePos = Vector2.of(-1);
    public static Vector2 mouseVel = Vector2.ZERO;

    /* ================ [ METHODS ] ================ */

    // Press an input
    public static void press(Input input) { inputs.add(input); }

    // Release an input
    public static void release(Input input) { inputs.remove(input); }

    // Release all keys
    public static void releaseAll() { inputs.clear(); }

    // Get if input is pressed
    public static boolean get(Input input) { return inputs.contains(input); }

    // Add an action to the map
    public static void addAction(String name, ActionSet action) { actions.put(name, action); }

    // Get if an action is pressed
    public static boolean getAction(String name) {
        for (Action action : actions.get(name).getActions()) {
            boolean pressed = true;
            for (Input input : action.getInputs()) {
                pressed = pressed && get(input);
            }

            if (pressed) {
                return true;
            }
        }
        return false;
    }

    /* ================ [ LOADER ] ================ */

    // Load from a JSON file
    public static void fromJSON(String path) {
        try {
            InputStream file = Inputs.class.getClassLoader().getResourceAsStream(path);
            ObjectMapper om = new ObjectMapper();
            actions = om.readValue(file, new TypeReference<Map<String, ActionSet>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
