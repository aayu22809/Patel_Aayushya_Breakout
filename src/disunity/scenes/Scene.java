package disunity.scenes;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import disunity.nodes.Node;

/** 
 * A collection of nodes to be rendered in the game
 * 
 * @author Qinzhao Li
 */
public class Scene extends Node {

    /* ================ [ FIELDS ] ================ */

    // List of nodes
    private final List<Node> nodes;

    // Scene position
    private int x = 0, y = 0;

    // Constructors
    public Scene() { this.nodes = new ArrayList<>(); }
    public Scene(Node... nodes) { this.nodes = Arrays.asList(nodes); }
    public Scene(int x, int y) { this.x = x; this.y = y; this.nodes = new ArrayList<>(); }
    public Scene(int x, int y, Node... nodes) { this.x = x; this.y = y; this.nodes = Arrays.asList(nodes); }

    /* ================ [ METHODS ] ================ */

    // Add node
    public void addNode(Node node) { nodes.add(node); }

    /* ================ [ NODE ] ================ */

    @Override
    public void update() {
        for (Node node : nodes) {
            node.update();
        }
    }

    @Override
    public void draw(Graphics2D g, int dx, int dy) {
        for (Node node : nodes) {
            node.draw(g, x + dx, y + dy);
        }
    }
    
}
