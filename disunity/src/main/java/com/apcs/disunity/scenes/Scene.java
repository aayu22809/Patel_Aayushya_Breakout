package com.apcs.disunity.scenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;

/** 
 * A collection of nodes to be rendered in the game
 * 
 * @author Qinzhao Li
 */
public class Scene extends Node {

    /* ================ [ FIELDS ] ================ */

    // List of nodes
    protected final List<Node> nodes;

    // Scene position
    protected Vector2 pos = new Vector2();

    // Constructors
    public Scene() { this.nodes = new ArrayList<>(); }
    public Scene(Node... nodes) { this.nodes = Arrays.asList(nodes); }
    public Scene(Vector2 pos) { this.pos = pos; this.nodes = new ArrayList<>(); }
    public Scene(Vector2 pos, Node... nodes) { this.pos = pos; this.nodes = Arrays.asList(nodes); }

    /* ================ [ METHODS ] ================ */

    // Add node
    public void addNode(Node node) { nodes.add(node); }

    /* ================ [ NODE ] ================ */

    @Override
    public void update(double delta) {
        // Update nodes
        for (Node node : nodes) node.update(delta);
    }

    @Override
    public void draw(Vector2 offset) {
        // Draw nodes
        for (Node node : nodes) node.draw(pos.add(offset));
    }
    
}
