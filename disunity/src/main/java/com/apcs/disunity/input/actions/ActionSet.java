package com.apcs.disunity.input.actions;

/**
 * A set of keybinds for an action
 * 
 * @author Qinzhao Li
 */
public class ActionSet {

    /* ================ [ FIELDS ] ================ */

    // Actions
    private final Action[] actions;

    // Constructor
    public ActionSet(Action... actions) { this.actions = actions; }

    /* ================ [ METHODS ] ================ */

    // Get actions
    public Action[] getActions() { return actions; }
    
}
