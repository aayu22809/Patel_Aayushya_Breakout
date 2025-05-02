package com.apcs.ljaag;

import java.io.IOException;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.camera.Camera;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.sprite.Sprite;
import com.apcs.disunity.scenes.Scenes;
import com.apcs.disunity.server.MultiplayerLauncher;
import com.apcs.disunity.server.SyncHandler;
import com.apcs.ljaag.nodes.body.LJCharacter;

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
    
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        MultiplayerLauncher launcher = new MultiplayerLauncher(LJAAG::runApp);
        launcher.lauch();
    }

    public static final int NUM_PLAYERS = 2;

    private static void runApp(boolean isServer) {

        // Import keybinds from a JSON file
        Inputs.fromJSON("keybinds.json");

        // Create the game scenes
        Scenes.addScene("test", new Node<Node<?>>(
            new Camera(),
            new Sprite("background.png")
        ){});

        Scenes.setScene("test");
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            Scenes.getScene().addChild(new LJCharacter(30*i-30, 0, i));
        }

        registerNodeRecursive(Scenes.getScene());

        int endpointId = SyncHandler.getInstance().getEndpointId();
        // Create game application

        Game game = new Game(
            Vector2.of(480, 270),
            "test"
        );

        if (!isServer) {
            new App(
                endpointId == 0 ? "[SERVER]" : "[CLIENT_" + endpointId + "]",
                800, 
                450,
                game);
        }

        game.start();

    }

    private static void registerNodeRecursive(Node<?> node) {
        SyncHandler.getInstance().register(node);
        for (Node<?> child : node.getChildren()) {
            registerNodeRecursive(child);
        }
    }

    // TODO: implement proper client reconciliation
    public static void own(Node<?> node, int clientId) {
        node.owner = clientId;
        for (Node<?> child : node.getChildren()) {
            own(child, clientId);
        }
    }
}
