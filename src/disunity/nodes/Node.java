package disunity.nodes;

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
    public abstract void draw(double dx, double dy);
    
}
