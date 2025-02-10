package disunity.input;

/**
 * Any multi-key keybind
 * 
 * @author Qinzhao Li
 */
public class Action {

    /* ================ [ FIELDS ] ================ */

    // Keys
    private final int[] keys;

    // Constructor
    public Action(int... keys) { this.keys = keys; }

    /* ================ [ METHODS ] ================ */

    // Get keys
    public int[] getKeys() { return keys; }
    
}
