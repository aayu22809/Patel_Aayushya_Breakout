package com.apcs.ljaag;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.annotations.ClientUnique;
import com.apcs.disunity.camera.Camera;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Body;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.Sprite;
import com.apcs.disunity.nodes.controller.Controller;
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
          new Sprite("BACKGROUND")
        ));

        Scenes.setScene("test");
        for(int i=0; i<4; i++) {
          Scenes.getScene().addChildren(instantiateCharacter(i));
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
    private static Body instantiateCharacter(int playerId) {
        @ClientUnique()
        class UnsyncedBody extends Body {}

      boolean isPlayer = SyncHandler.getInstance().getEndpointId() == playerId + 1;
      Body body = isPlayer ? new UnsyncedBody() : new Body();
      body.addChildren(
        isPlayer ? new PlayerController() : new Controller(){},
        new Camera(),
        new Sprite("PLAYER"),
        new WalkAction()
      );
      return body;
    }
    private static void registerNodeRecursive(Node<?> node) {
     SyncHandler.getInstance().register(node);
     for(Node<?> child: node.getChildren()) {
       registerNodeRecursive(child);
     }
   }
}