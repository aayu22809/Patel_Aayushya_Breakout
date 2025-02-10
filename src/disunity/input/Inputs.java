package disunity.input;

import java.util.HashMap;
import java.util.Map;

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
    private static final Map<Integer, Boolean> inputs = new HashMap<>();

    /* ================ [ METHODS ] ================ */

    // Press input
    public static void press(int input) { inputs.put(input, true); }

    // Release input
    public static void release(int input) { inputs.put(input, false); }

    // Get input
    public static boolean get(int input) { return inputs.getOrDefault(input, false); }

    // Add action
    public static void addAction(String name, ActionSet action) { actions.put(name, action); }

    // Get action
    public static boolean getAction(String name) {
        for (Action action : actions.get(name).getActions()) {
            boolean pressed = true;
            for (int input : action.getKeys())
                pressed = pressed && get(input);
            
            if (pressed) return true;
        } return false;
    }
    
}
