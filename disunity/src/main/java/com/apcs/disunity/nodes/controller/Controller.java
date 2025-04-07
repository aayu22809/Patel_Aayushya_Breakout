package com.apcs.disunity.nodes.controller;

import com.apcs.disunity.nodes.UndrawnNode;

/**
 * Controls a body node
 * 
 * @author Qinzhao Li
 */
public abstract class Controller extends UndrawnNode {

    /* ================ [ FIELDS ] ================ */

    // Global controller number
    private static int controllers = 0;

    // Controller id
    private int id;

    // Constructors
    public Controller() { super(); this.id = controllers++; }
    public Controller(UndrawnNode... children) { super(children); }

    /* ================ [ METHODS ] ================ */

    // Get controller id
    public int getId() { return id; }

}
