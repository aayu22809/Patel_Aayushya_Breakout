package com.apcs.ljaag;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.camera.Camera;
import com.apcs.disunity.input.Input;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.input.actions.Action;
import com.apcs.disunity.input.actions.ActionSet;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Sprite;
import com.apcs.disunity.resources.Resources;
import com.apcs.disunity.scenes.Scene;
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
        
        // Setup resources
        Resources.scanFolder("ljaag/assets", true);

        // Setup keybinds
        // TODO: json this
        Inputs.addAction("up", new ActionSet(new Action(Input.KEY_W), new Action(Input.KEY_UP)));
        Inputs.addAction("down", new ActionSet(new Action(Input.KEY_S), new Action(Input.KEY_DOWN)));
        Inputs.addAction("left", new ActionSet(new Action(Input.KEY_A), new Action(Input.KEY_LEFT)));
        Inputs.addAction("right", new ActionSet(new Action(Input.KEY_D), new Action(Input.KEY_RIGHT)));

        // Setup scenes

        Sprite player = new Sprite("templayer");
        player.addChild(new Movement(player));
        
        Scenes.addScene("test", new Scene(
            player
        ));

        // Create app
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
