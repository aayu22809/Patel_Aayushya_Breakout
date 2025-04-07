package com.apcs.disunity.input;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.apcs.disunity.input.actions.Action;
import com.apcs.disunity.input.actions.ActionSet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/** 
 * Handles all player inputs recieved
 * 
 * @author Qinzhao Li
 */
public class Inputs {

    /* ================ [ FIELDS ] ================ */

    // Maps actions to their respective bindings
    private static Map<String, ActionSet> actions = new HashMap<>();

    // Maps inputs to their pressed state
    private static final Map<Input, Boolean> inputs = new HashMap<>();

    // Mouse position on the screen
    public static int mouseX = 0, mouseY = 0;

    /* ================ [ METHODS ] ================ */

    // Press an input
    public static void press(Input input) { inputs.put(input, true); }

    // Release an input
    public static void release(Input input) { inputs.remove(input); }

    // Get if input is pressed
    public static boolean get(Input input) { return inputs.getOrDefault(input, false); }

    // Add an action to the map
    public static void addAction(String name, ActionSet action) { actions.put(name, action); }

    // Get if an action is pressed
    public static boolean getAction(String name) {
        for (Action action : actions.get(name).getActions()) {
            boolean pressed = true;
            for (Input input : action.getInputs())
                pressed = pressed && get(input);
            
            if (pressed) return true;
        } return false;
    }

    /* ================ [ LOADER ] ================ */

    // Load from a JSON file
    public static void fromJSON(String path) {
        try {
            File file = new File(path);
            
            ObjectMapper om = new ObjectMapper();
            actions = om.readValue(file, new TypeReference<Map<String, ActionSet>>() {});
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Save to a JSON file
    public static void toJSON(String path) {
        try {
            File file = new File(path);
            
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.INDENT_OUTPUT);

            om.writeValue(file, actions);
        } catch (IOException e) { e.printStackTrace(); }
    }
    
}
