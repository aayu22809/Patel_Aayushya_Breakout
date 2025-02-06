package disunity.nodes;

import java.awt.Graphics2D;

/**
 * The base class for all nodes in the game
 * 
 * @author Qinzhao Li
 */
public abstract class Node {

    /* ================ [ METHODS ] ================ */

    // Update node
    public abstract void update();
    
    // Draw node
    public abstract void draw(Graphics2D g, int dx, int dy);
    
}
