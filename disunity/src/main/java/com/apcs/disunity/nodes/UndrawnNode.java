package com.apcs.disunity.nodes;

/**
 * A node that is guaranteed to not to have any drawing nodes
 * 
 * @author Toshiki Takeuchi
 */
public abstract class UndrawnNode extends Node<UndrawnNode> {

    /* ================ [ FIELDS ] ================ */

    // Constructors
    public UndrawnNode() { super(); }
    public UndrawnNode(UndrawnNode... nodes) { super(nodes); }

}
