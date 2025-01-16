package ljaag.components;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ljaag.util.Vector2;

public class Component {

    /* ================ [ FIELDS ] ================ */

    /** List of the component's children */
    protected final List<Component> children = new ArrayList<>();

    /** 2D position */
    protected Vector2 position = new Vector2(0, 0);
    /** Bounding box size */
    protected Vector2 bounds = new Vector2(0, 0);

    /**
     * Construct a Component.
     * 
     * @param children Components to add as children
     */
    public Component(Component... children) {
        this.children.addAll(Arrays.asList(children));
    }

    /**
     * Construct a Component w/ position.
     *
     * @param pos The position of the component
     * @param children Components to add as children
     */
    public Component(Vector2 pos, Component... children) {
        this.children.addAll(Arrays.asList(children));
        moveTo(pos);
    }

    /* ================ [ METHODS ] ================ */

    /**
     * Move to a position.
     * 
     * @param pos The position to move to
     */
    public void moveTo(Vector2 pos) { position = pos; }
    /**
     * Move by an amount.
     * 
     * @param pos The amount to move by
     */
    public void move(Vector2 pos) { position = position.add(pos); }

    /**
     * Set the bounds.
     * 
     * @param bnd The new bounds
     */
    public void setBounds(Vector2 bnd) { bounds = bnd; }
    /**
     * Scale the bounds by an amount.
     * 
     * @param amt The amount to scale by
     */
    public void scale(int amt) { bounds = new Vector2(bounds.x * amt, bounds.y * amt); }

    /**
     * Get the position.
     * 
     * @return The position
     */
    public Vector2 getPosition() { return new Vector2(position.x, position.y); }
    /**
     * Get the bound.
     * 
     * @return The bounds
     */
    public Vector2 getBounds() { return new Vector2(bounds.x, bounds.y); }

    /* ================ [ COMPONENT ] ================ */

    /** Update the component. */
    public void update() {
        for (Component child : children)
            child.update();
    }
    
    /** Draw the component. */
    public void draw(Graphics g) {
        for (Component child : children)
            child.draw(g);
    }
    
}
