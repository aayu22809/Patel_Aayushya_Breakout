package com.apcs.ljaag;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.camera.Camera;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Sprite;
import com.apcs.disunity.resources.Resources;
import com.apcs.disunity.scenes.Scenes;

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
        Scenes.addScene("test", new Node2D( // Represents the scene
            new Node2D( // Represents the player (temp)
                new Sprite("templayer")
            )
        ));

        // Create game application
        new App(
            "Title", 
            800, 450,
            new Game(
                Vector2.of(320, 180),
                "test",
                new Camera()
            )
        );

    }
    
}
