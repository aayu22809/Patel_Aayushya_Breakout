package com.apcs.disunity.nodes;

import com.apcs.disunity.math.Vector2;

/**
 * The base class for all nodes in the game
 * 
 * @author Qinzhao Li
 */
public abstract class Node {

    /* ================ [ METHODS ] ================ */

    // Update node
    public abstract void update(double delta);
    
    // Draw node
    public abstract void draw(Vector2 offset);
    
}
