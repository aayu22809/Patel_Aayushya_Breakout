package com.apcs.ljaag;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.animation.Animation;
import com.apcs.disunity.animation.AnimationFrame;
import com.apcs.disunity.animation.AnimationSet;
import com.apcs.disunity.camera.Camera;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.controller.Controller;
import com.apcs.disunity.nodes.body.Body;
import com.apcs.disunity.nodes.sprite.AnimatedSprite;
import com.apcs.disunity.nodes.sprite.Sprite;
import com.apcs.disunity.resources.Resources;
import com.apcs.disunity.scenes.Scenes;
import com.apcs.disunity.server.ClientSideSyncHandler;
import com.apcs.disunity.server.HostSideSyncHandler;
import com.apcs.disunity.server.SyncHandler;
import com.apcs.ljaag.nodes.controller.PlayerController;
import com.apcs.ljaag.nodes.moveaction.WalkAction;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
      HostSideSyncHandler host = new HostSideSyncHandler();
        runApp();
        host.start();
    }

    // ISSUE: using try resource with SH terminates sender reciever thread (isInturrupted becomes true)
    public static class Client {
      public static void main(String[] args) throws IOException {
        ClientSideSyncHandler client = new ClientSideSyncHandler( "0:0:0:0:0:0:0:0", 3000);
        runApp();
        client.start();
      }
    }

    private static void runApp() {
        // Import resources from the assets folder
        Resources.scanFolder("ljaag/assets");

        // Import keybinds from a JSON file
        Inputs.fromJSON("ljaag/keybinds.json");

        // Create the game scenes
        Scenes.addScene("test", new Node2D(
            new Sprite("background")
        ));

        Scenes.setScene("test");
        for(int i=0; i<4; i++) {
          Scenes.getScene().addChildren(instantiateCharacter(i+1));
        }

        registerNodeRecursive(Scenes.getScene());

        int endpointId = SyncHandler.getInstance().getEndpointId();
        // Create game application
        new App(
            endpointId == 0 ? "[SERVER]" : "[CLIENT_"+endpointId+"]",
            800, 450,
            new Game(
                Vector2.of(480, 270),
                "test"
            )
        );
    }
    private static Body instantiateCharacter(int clientId) {
      boolean isPlayer = SyncHandler.getInstance().getEndpointId() == clientId;
      Body body = new Body();
      body.addChildren(
          isPlayer ? new Camera() : new Node<Node<?>>() {},
          new AnimatedSprite(
              new AnimationSet("player",
                  new Animation("run",
                      new AnimationFrame("player_run_0", 0.15),
                      new AnimationFrame("player_run_1", 0.15),
                      new AnimationFrame("player_run_2", 0.15),
                      new AnimationFrame("player_run_3", 0.15),
                      new AnimationFrame("player_run_4", 0.15),
                      new AnimationFrame("player_run_5", 0.15)
                  )
              )
          ),
          isPlayer ? new PlayerController() : new Controller(){},
          new WalkAction()
      );
      body.clientId = clientId;
      return body;
    }
    private static void registerNodeRecursive(Node<?> node) {
     SyncHandler.getInstance().register(node);
     for(Node<?> child: node.getChildren()) {
       registerNodeRecursive(child);
     }
   }
}
