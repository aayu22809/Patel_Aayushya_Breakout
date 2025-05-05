package com.apcs.disunity.app.input.actions;

import com.apcs.disunity.app.input.Input;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * A single or multi-key binding
 * 
 * @author Qinzhao Li
 */
public class Action {

    /* ================ [ FIELDS ] ================ */

    // Keys
    private final Input[] inputs;

    // Constructors
    @JsonCreator
    public Action(Input... inputs) { this.inputs = inputs; }

    /* ================ [ METHODS ] ================ */

    // Get keys
    @JsonValue
    public Input[] getInputs() { return inputs; }
    
}
