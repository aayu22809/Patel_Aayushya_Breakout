package com.apcs.ljaag;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.JOptionPane;

import com.apcs.disunity.App;
import com.apcs.disunity.Game;
import com.apcs.disunity.animation.Animation;
import com.apcs.disunity.animation.AnimationSet;
import com.apcs.disunity.camera.Camera;
import com.apcs.disunity.input.Inputs;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.Node;
import com.apcs.disunity.nodes.Node2D;
import com.apcs.disunity.nodes.body.Body;
import com.apcs.disunity.nodes.controller.Controller;
import com.apcs.disunity.nodes.sprite.AnimatedSprite;
import com.apcs.disunity.nodes.sprite.Sprite;
import com.apcs.disunity.scenes.Scenes;
import com.apcs.disunity.server.ClientSideSyncHandler;
import com.apcs.disunity.server.HostSideSyncHandler;
import com.apcs.disunity.server.SyncHandler;
import com.apcs.ljaag.nodes.action.TurnAction;
import com.apcs.ljaag.nodes.action.WalkAction;
import com.apcs.ljaag.nodes.controller.PlayerController;

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
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        String input = JOptionPane.showInputDialog("DISUNITY STARTUP WIZARD\n\nWould you like to start or join a session? (s/j)");
        if (input == null) System.exit(0);
        if (input.startsWith("s") || input.startsWith("S")) {
            Server.main(args);
        } else {
            Client.main(args);
        }
    }

    public static final int NUM_PLAYERS = 8;

    public static class Server {
        public static void main(String[] args) throws IOException {
            HostSideSyncHandler host = new HostSideSyncHandler();
            runApp(NUM_PLAYERS, false);
            host.start();
            JOptionPane.showMessageDialog(null, String.format("DISUNITY STARTUP WIZARD\n\nServer running on address %s on port %d\n\nClose this window to stop the server", host.getAddress(), host.getPort()));
            System.exit(0);
        }
    }

    public static class Client {
        public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
            SyncHandler handler;
            while (true) {
                String address = JOptionPane.showInputDialog("DISUNITY STARTUP WIZARD\n\nEnter the Server address. Leave blank for your local Server instance.");
                if (address == null) System.exit(0);
                String port = JOptionPane.showInputDialog("DISUNITY STARTUP WIZARD\n\nEnter the Server port.");
                if (port == null) System.exit(0);
                if (address.isBlank()) address = "0:0:0:0:0:0:0:0";
                try {
                    Field instanceField = SyncHandler.class.getDeclaredField("instance");
                    instanceField.setAccessible(true);
                    instanceField.set(null, null);
                    handler = new ClientSideSyncHandler(address, (int) Integer.parseInt(port));
                    System.out.println("able to connect");
                    break;
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "DISUNITY STARTUP WIZARD\nCould not connect to this Server");
                    ioe.printStackTrace();
                }
            }
            runApp(NUM_PLAYERS, true);
            handler.start();
        }
    }

    private static void runApp(int players, boolean viewable) {

        // Import keybinds from a JSON file
        Inputs.fromJSON("keybinds.json");

        // Create the game scenes
        Scenes.addScene("test", new Node2D(
            new Sprite("background.png")
        ));

        Scenes.setScene("test");
        for (int i = 1; i <= players+1; i++) {
            Scenes.getScene().addChildren(instantiateCharacter(i));
        }

        registerNodeRecursive(Scenes.getScene());

        int endpointId = SyncHandler.getInstance().getEndpointId();
        // Create game application

        Game game = new Game(
            Vector2.of(480, 270),
            "test"
        );

        if (viewable) {
            new App(
                endpointId == 0 ? "[SERVER]" : "[CLIENT_" + endpointId + "]",
                800, 
                450,
                game);
        }

        game.start();

    }

    private static Body instantiateCharacter(int clientId) {
        boolean isPlayer = SyncHandler.getInstance().getEndpointId() == clientId;
        Body body = new Body(
            isPlayer ? new Camera() : new Node<>() {
            },
            new AnimatedSprite(
                new AnimationSet("player/player.png",
                    new Animation("run", "player/run.png", 0.15, 0.15, 0.15, 0.15, 0.15, 0.15)
                ),
                isPlayer
            ),
            isPlayer ? new PlayerController() : new Controller() {
            },
            new WalkAction(),
            new TurnAction()
        );
        own(body, clientId);
        return body;
    }

    private static void registerNodeRecursive(Node<?> node) {
        SyncHandler.getInstance().register(node);
        for (Node<?> child : node.getChildren()) {
            registerNodeRecursive(child);
        }
    }

    // TODO: implement proper client reconciliation
    private static void own(Node<?> node, int clientId) {
        node.owner = clientId;
        for (Node<?> child : node.getChildren()) {
            own(child, clientId);
        }
    }
}
