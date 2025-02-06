package disunity.scenes;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

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
    public static void updateScene() { getScene().update(); }

    // Draw current scene
    public static void drawScene(Graphics2D g) { getScene().draw(g, 0, 0); }

}
