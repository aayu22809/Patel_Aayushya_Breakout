package com.apcs.disunity.input;

import java.util.HashMap;
import java.util.Map;

import com.apcs.disunity.input.actions.Action;
import com.apcs.disunity.input.actions.ActionSet;

/** 
 * Handles all player inputs recieved
 * 
 * @author Qinzhao Li
 */
public class Inputs {

    /* ================ [ FIELDS ] ================ */

    // Actions map
    private static final Map<String, ActionSet> actions = new HashMap<>();

    // Input map
    private static final Map<Input, Boolean> inputs = new HashMap<>();

    // Mouse position
    public static int mouseX = 0, mouseY = 0;

    /* ================ [ METHODS ] ================ */

    // Press input
    public static void press(Input input) { inputs.put(input, true); }

    // Release input
    public static void release(Input input) { inputs.put(input, false); }

    // Get input
    public static boolean get(Input input) { return inputs.getOrDefault(input, false); }

    // Add action
    public static void addAction(String name, ActionSet action) { actions.put(name, action); }

    // Get action
    public static boolean getAction(String name) {
        for (Action action : actions.get(name).getActions()) {
            boolean pressed = true;
            for (Input input : action.getInputs())
                pressed = pressed && get(input);
            
            if (pressed) return true;
        } return false;
    }

    // From a JSON file
    public static void fromJSON(String path) {
        
    }
    
}
