package ljaag.components;

import ljaag.input.InputHandler;
import ljaag.util.Vector2;

public class PlayerComponent extends SpriteComponent {

    /* ================ [ FIELDS ] ================ */

    /**
     * Construct a PlayerComponent.
     * 
     * @param children Components to add as children
     */
    public PlayerComponent(Component... children) {
        super("assets/player.png", children);
    }

    /**
     * Construct a PlayerComponent w/ position.
     *
     * @param pos The position of the component
     * @param children Components to add as children
     */
    public PlayerComponent(Vector2 pos, Component... children) {
        super("assets/player.png", pos, children);
    }

    /* ================ [ COMPONENT ] ================ */

    @Override
    public void update() {
        super.update();

        if (InputHandler.inputs.getOrDefault("up", false))
            move(new Vector2(0, -1));
        if (InputHandler.inputs.getOrDefault("down", false))
            move(new Vector2(0, 1));
        if (InputHandler.inputs.getOrDefault("left", false))
            move(new Vector2(-1, 0));
        if (InputHandler.inputs.getOrDefault("right", false))
            move(new Vector2(1, 0));
    }
    
}
