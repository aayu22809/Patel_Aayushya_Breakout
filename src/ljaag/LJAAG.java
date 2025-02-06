package ljaag;

import disunity.App;
import disunity.Game;
import disunity.nodes.Node2D;
import disunity.nodes.Sprite;
import disunity.resources.Image;
import disunity.resources.Resource;
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
        Resources.addResource("player", new Resource(Resource.Type.IMAGE, "assets/player.png"));

        // Setup scenes
        Scenes.addScene("test", new Scene(
            new Node2D(
                new Sprite(
                    "player"
                )
            )
        ));

        // Create app
        new App(
            "Literally Just Another APCS Game", 
            800, 450,
            new Game(320, 180, "test")
        );

    }
    
}
