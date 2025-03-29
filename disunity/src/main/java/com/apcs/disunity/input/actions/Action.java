package com.apcs.disunity.input.actions;

import com.apcs.disunity.input.Input;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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
    @JsonCreator
    public Action(Input... inputs) { this.inputs = inputs; }

    /* ================ [ METHODS ] ================ */

    // Get keys
    @JsonValue
    public Input[] getInputs() { return inputs; }
    
}
