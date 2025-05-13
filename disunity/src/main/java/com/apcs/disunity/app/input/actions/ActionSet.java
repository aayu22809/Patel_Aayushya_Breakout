package com.apcs.disunity.app.input.actions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * defines a flag that can be triggered by multiple Actions.
 * in other words, takes an || on all Actions
 * 
 * @author Qinzhao Li
 */
public class ActionSet {

    /* ================ [ FIELDS ] ================ */

    // Actions
    private final Action[] actions;

    // Constructors
    @JsonCreator
    public ActionSet(Action... actions) { this.actions = actions; }

    /* ================ [ METHODS ] ================ */

    // Get actions
    @JsonValue
    public Action[] getActions() { return actions; }
    
}
