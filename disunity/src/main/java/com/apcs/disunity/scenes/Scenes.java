package com.apcs.disunity.scenes;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.nodes.DrawnNode;
import com.apcs.disunity.rendering.RenderObject;
import com.apcs.disunity.resources.Image;
import com.apcs.disunity.resources.Resources;

/**
 * Manages the scenes in the game
 * 
 * @author Qinzhao Li
 */
public class Scenes {

    /* ================ [ FIELDS ] ================ */

    // Current scene id
    private static String scene = "";

    // Maps scene ids to scenes
    private static final Map<String, DrawnNode> scenes = new HashMap<>();

    /* ================ [ METHODS ] ================ */

    // Add a scene
    public static void addScene(String name, DrawnNode scene) { scenes.put(name, scene); }

    // Set current scene
    public static void setScene(String scene) { Scenes.scene = scene; }
    
    // Get current scene
    public static DrawnNode getScene() { return scenes.get(scene); }

    // Update current scene
    public static void updateScene(double delta) { getScene().update(delta); }

    // Draw current scene
    public static void drawScene(Vector2 offset) {
        List<RenderObject> objs = getScene().getRenderObjects(offset);

        for (RenderObject imageInfo : objs) {

            // Draw sprite image
            BufferedImage img = Resources.loadResource(imageInfo.name, Image.class).getImage();

            Game.getInstance().getBuffer().drawImage(
                img,
                imageInfo.offset,
                imageInfo.scale
            );

        }
    }

}
