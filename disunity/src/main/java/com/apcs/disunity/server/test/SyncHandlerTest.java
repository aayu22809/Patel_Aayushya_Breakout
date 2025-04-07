package com.apcs.disunity.server.test;

import java.io.IOException;
import java.util.Scanner;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.Sprite;
import com.apcs.disunity.nodes.Body;
import com.apcs.disunity.nodes.moveaction.MoveAction;
import com.apcs.disunity.resources.Resources;
import com.apcs.disunity.scenes.Scenes;
import com.apcs.disunity.server.ClientSideSyncHandler;
import com.apcs.disunity.server.HostSideSyncHandler;
import com.apcs.disunity.server.SyncHandler;
import static com.apcs.disunity.server.test.TestingUtils.spawnProcess;

/// # current status of this test:
/// ## working
/// - server to client node sync
/// - control character over network from client
/// ## issue
/// - jittery movement
/// - slight flicker?
/// - horizontal movement is more fluid for some reason
/// ## todo
/// - multi-client test
public class SyncHandlerTest {
  /// only client has control over character, position and velocity gets synced to host.
  @SuppressWarnings("resource")
  public static void main(String[] args) throws IOException {
    spawnProcess(ServerTest.class);
    spawnProcess(ClientTest.class);
    new Scanner(System.in).nextLine();
  };
  public static class ServerTest {
    public static void main(String[] args) throws IOException {
      HostSideSyncHandler hsh = new HostSideSyncHandler();
      makeApp("[SERVER]",hsh,true);
      hsh.start();
    }
  }
  public static class ClientTest {
    public static void main(String[] args) throws IOException {
      ClientSideSyncHandler csh = new ClientSideSyncHandler(
        "0:0:0:0:0:0:0:0",
        3000
      );
      makeApp("[CLIENT1]",csh,false);
      csh.start();
    }
  }

  private static App makeApp(String windowName, SyncHandler syncHandler, boolean isHost) {
    // Import resources from the assets folder
    Resources.scanFolder("ljaag/assets", true);

    // Import keybinds from a JSON file
    Inputs.fromJSON("ljaag/keybinds.json");

    // Create the game scenes
    // playerbody and walkaction is in ljaag, and using it will cause circular dependency.
    Scenes.addScene("test", new Node2D( // Represents the scene
      new Body(
        new Sprite("templayer"),
        new MoveAction() {
          @Override
          public Vector2 apply(Vector2 vel, double delta) {
            if(isHost) return vel;
            return new Vector2(
              (Inputs.getAction("left") ? -1 : 0) + (Inputs.getAction("right") ? 1 : 0),
              (Inputs.getAction("up") ? -1 : 0) + (Inputs.getAction("down") ? 1 : 0)
            ).normalized().mul(100);
          }


          public void trigger(Object data) {}
          public byte[] supply(int recipient) { return new byte[0]; }
          public int receive(int sender, byte[] data) {
            return 0;
          }
          
        }
        
      ) {
        @Override
        public int receive(int sender, byte[] data) {
          // this is really bad practice
          if (!isHost) return 0;
          else return super.receive(sender, data);
        }
      },


      // client, should ignore server
      new Body(
          new Sprite("templayer"),
          new MoveAction() {
            @Override
            public Vector2 apply(Vector2 vel, double delta) {
              if(!isHost) return vel;
              return new Vector2(
                (Inputs.getAction("left") ? -1 : 0) + (Inputs.getAction("right") ? 1 : 0),
                (Inputs.getAction("up") ? -1 : 0) + (Inputs.getAction("down") ? 1 : 0)
              ).normalized().mul(100);
            }


            public void trigger(Object data) {}
          }
        ){
          @Override
          public void update(double delta) {
            super.update(delta);
  //          System.out.printf("%s s = %s, v = %s\n",windowName, getPos(), getVel());
          }

          @Override
          public int receive(int sender, byte[] data) {
            if (isHost) return 0;
            else return super.receive(sender, data);
          }
        }
    ));

    Scenes.setScene("test");
    registerNodeRecursive(Scenes.getScene(), syncHandler);

    // Create game application
    return new App(
      windowName,
      800, 450,
      new Game(
        Vector2.of(320, 180),
        "test"
      )
    );

  }
  private static void registerNodeRecursive(Node<?> node, SyncHandler sh) {
    sh.register(node);
    for(Node<?> child: node.getChildren()) {
      registerNodeRecursive(child,sh);
    }
  }
}
