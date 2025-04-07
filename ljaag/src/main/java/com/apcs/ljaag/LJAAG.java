package com.apcs.ljaag;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.camera.Camera;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Body;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.Sprite;
import com.apcs.disunity.resources.Resources;
import com.apcs.disunity.scenes.Scenes;
import com.apcs.ljaag.nodes.controller.PlayerController;
import com.apcs.ljaag.nodes.moveaction.WalkAction;

/**
 * Untitled game
 * 
 * @author Aayushya Patel
 * @author Qinzhao Li
 * @author Sharvil Phadke
 * @author Toshiki Takeuchi
 */
public class LJAAG {

    /* ================ [ DRIVER ] ================ */

    public static void main(String[] args) {
        
        // Import resources from the assets folder
        Resources.scanFolder("ljaag/assets", true);

        // Import keybinds from a JSON file
        Inputs.fromJSON("ljaag/keybinds.json");

        // Create the game scenes
        Scenes.addScene("test", new Node2D(
            new Body(
                new PlayerController(),
                new Camera(),
                new Sprite("templayer"),
                new WalkAction()
            )
        ));

        // Create game application
        new App(
            "Title", 
            800, 450,
            new Game(
                Vector2.of(320, 180),
                "test"
            )
        );

    }
    
}
