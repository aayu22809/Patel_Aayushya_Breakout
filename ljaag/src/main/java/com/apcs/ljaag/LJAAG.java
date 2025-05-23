package com.apcs.ljaag;

import java.io.IOException;

import com.apcs.disunity.app.App;
import com.apcs.disunity.game.Game;
import com.apcs.disunity.game.nodes.Scene;
import com.apcs.disunity.game.nodes.twodim.Camera;
import com.apcs.disunity.app.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.game.nodes.Node;
import com.apcs.disunity.game.nodes.sprite.Sprite;
import com.apcs.disunity.app.network.MultiplayerLauncher;
import com.apcs.disunity.app.network.packet.SyncHandler;
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

    public static void main(String[] args) throws IOException {
        MultiplayerLauncher launcher = new MultiplayerLauncher(LJAAG::runApp);
        launcher.lauch();
    }

    public static final int NUM_PLAYERS = 2;

    private static void runApp(boolean isServer) {

        // Import keybinds from a JSON file
        Inputs.fromJSON("keybinds.json");

        // Create the game scenes
        Scene scene = new Scene("test", new Camera(), new Sprite("background.png"));

        for (int i = 1; i <= NUM_PLAYERS; i++) {
            scene.addChild(new LJCharacter(30 * i - 30, 0, i));
        }

        registerNodeRecursive(scene);

        // Create game application
        Game game = new Game(Vector2.of(480, 270));
        game.addScene(scene);
        game.setScene("test");

        int endpointId = SyncHandler.getInstance().getEndpointId();
        if (!isServer) {
            new App(endpointId == 0 ? "[SERVER]" : "[CLIENT_" + endpointId + "]", 800, 450, game);
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
