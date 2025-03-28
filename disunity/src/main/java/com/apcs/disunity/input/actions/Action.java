package com.apcs.disunity.input.actions;

import com.apcs.disunity.input.Input;

/**
 * Any multi-key keybind
 * 
 * @author Qinzhao Li
 */
public class Action {

    /* ================ [ FIELDS ] ================ */

    // Keys
    private final Input[] inputs;

    // Constructor
    public Action(Input... inputs) { this.inputs = inputs; }

    /* ================ [ METHODS ] ================ */

    // Get keys
    public Input[] getInputs() { return inputs; }
    
}
