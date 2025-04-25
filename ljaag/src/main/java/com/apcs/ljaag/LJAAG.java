package com.apcs.ljaag;

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
import com.apcs.disunity.server.SingletonViolationException;
import com.apcs.disunity.server.SyncHandler;
import com.apcs.ljaag.nodes.action.TurnAction;
import com.apcs.ljaag.nodes.action.WalkAction;
import com.apcs.ljaag.nodes.controller.PlayerController;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.BindException;

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
        SyncHandler handler;
        try {
            handler = new HostSideSyncHandler();
        } catch (BindException e) {
            Field instanceField = SyncHandler.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, null);
            handler = new ClientSideSyncHandler("0:0:0:0:0:0:0:0", 3000);
        }
        runApp();
        handler.start();
    }

    public static class Server {
        public static void main(String[] args) throws IOException {
            HostSideSyncHandler host = new HostSideSyncHandler();
            runApp();
            host.start();
        }
    }

    public static class Client {
        public static void main(String[] args) throws IOException {
            ClientSideSyncHandler client = new ClientSideSyncHandler("0:0:0:0:0:0:0:0", 3000);
            runApp();
            client.start();
        }
    }

    private static void runApp() {

        // Import keybinds from a JSON file
        Inputs.fromJSON("keybinds.json");

        // Create the game scenes
        Scenes.addScene("test", new Node2D(
            new Sprite("background.png")
        ));

        Scenes.setScene("test");
        for (int i = 1; i <= 4; i++) {
            Scenes.getScene().addChildren(instantiateCharacter(i));
        }

        registerNodeRecursive(Scenes.getScene());

        int endpointId = SyncHandler.getInstance().getEndpointId();
        // Create game application
        new App(
            endpointId == 0 ? "[SERVER]" : "[CLIENT_" + endpointId + "]",
            800, 450,
            new Game(
                Vector2.of(480, 270),
                "test"
            )
        );

    }

    private static Body instantiateCharacter(int clientId) {
        boolean isPlayer = SyncHandler.getInstance().getEndpointId() == clientId;
        Body body = new Body(
            isPlayer ? new Camera() : new Node<>() {
            },
            new AnimatedSprite(
                new AnimationSet("player/player.png",
                    new Animation("run", "player/run.png", 0.15, 0.15, 0.15, 0.15, 0.15, 0.15)
                )
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
