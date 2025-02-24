package ljaag;

import disunity.App;
import disunity.Game;
import disunity.camera.Camera;
import disunity.input.Input;
import disunity.input.Inputs;
import disunity.input.actions.Action;
import disunity.input.actions.ActionSet;
import disunity.math.Vector2;
import disunity.nodes.Node2D;
import disunity.nodes.Sprite;
import disunity.resources.Resources;
import disunity.scenes.Scene;
import disunity.scenes.Scenes;

/**
 * Literally Just Another APCS Game
 * 
 * @author Qinzhao Li
 */
public class LJAAG {

    /* ================ [ DRIVER ] ================ */

    public static void main(String[] args) {
        
        // Setup resources
        Resources.scanFolder("assets", true);

        // Setup keybinds
        Inputs.addAction("up", new ActionSet(new Action(Input.KEY_W), new Action(Input.KEY_UP)));
        Inputs.addAction("down", new ActionSet(new Action(Input.KEY_S), new Action(Input.KEY_DOWN)));
        Inputs.addAction("left", new ActionSet(new Action(Input.KEY_A), new Action(Input.KEY_LEFT)));
        Inputs.addAction("right", new ActionSet(new Action(Input.KEY_D), new Action(Input.KEY_RIGHT)));

        // Setup scenes
        Scenes.addScene("test", new Scene(
            new Node2D(
                new Sprite("templayer")
            )
        ));

        // Create app
        new App(
            "Literally Just Another APCS Game", 
            800, 450,
            new Game(
                Vector2.of(320, 180),
                "test",
                new Camera()
            )
        );

    }
    
}
