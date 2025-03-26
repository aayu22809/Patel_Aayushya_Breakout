package disunity.scenes;

import java.util.HashMap;
import java.util.Map;

import disunity.math.Vector2;

/**
 * Manages the scenes in the game
 * 
 * @author Qinzhao Li
 */
public class Scenes {

    /* ================ [ FIELDS ] ================ */

    // Current scene
    private static String scene = "";

    // Scene map
    private static final Map<String, Scene> scenes = new HashMap<>();

    /* ================ [ METHODS ] ================ */

    // Add scene
    public static void addScene(String name, Scene scene) { scenes.put(name, scene); }

    // Set current scene
    public static void setScene(String scene) { Scenes.scene = scene; }
    
    // Get current scene
    public static Scene getScene() { return scenes.get(scene); }

    // Update current scene
    public static void updateScene(double delta) { getScene().update(delta); }

    // Draw current scene
    public static void drawScene(Vector2 offset) { getScene().draw(offset); }

}
