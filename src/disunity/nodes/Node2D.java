package disunity.nodes;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A base class for 2D nodes with position and children
 * 
 * @author Qinzhao Li
 */
public class Node2D extends Node {

    /* ================ [ FIELDS ] ================ */
    
    // List of children
    protected final List<Node> children;
    
    // Node position
    protected int x = 0, y = 0;

    // Constructors
    public Node2D() { this.children = new ArrayList<>(); }
    public Node2D(Node... children) { this.children = Arrays.asList(children); }
    public Node2D(int x, int y) { this.x = x; this.y = y; this.children = new ArrayList<>(); }
    public Node2D(int x, int y, Node... children) { this.x = x; this.y = y; this.children = Arrays.asList(children); }

    /* ================ [ METHODS ] ================ */

    // Add child
    public void addChild(Node node) { children.add(node); }

    // Remove child
    public void removeChild(Node node) { children.remove(node); }
    
    /* ================ [ NODE ] ================ */

    @Override
    public void update() {
        for (Node node : children) {
            node.update();
        }
    }

    @Override
    public void draw(Graphics2D g, int dx, int dy) {
        for (Node node : children) {
            node.draw(g, x + dx, y + dy);
        }
    }
    
}
