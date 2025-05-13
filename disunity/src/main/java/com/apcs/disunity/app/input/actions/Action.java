package com.apcs.disunity.app.input.actions;

import com.apcs.disunity.app.input.Input;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * defines a flag that is true only when all the inputs are present.
 * in other words, this takes an && on every input
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
