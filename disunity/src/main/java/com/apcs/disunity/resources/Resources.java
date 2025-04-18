package com.apcs.disunity.resources;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.apcs.disunity.files.FileUtil;

/**
 * Handles all of the resources in the game
 * 
 * @author Qinzhao Li
 */
public class Resources {

    /* ================ [ FIELDS ] ================ */

    // Maps resource ids to resources
    private static final Map<String, Resource> resources = new HashMap<>();

    /* ================ [ METHODS ] ================ */

    // Create id from directory and name
    public static String createId(String dir, String name) {
        if (dir != null && !name.equals(dir))
            return dir + "_" + name;
        return name;
    }

    // Add resource to map
    public static void addResource(String name, Resource resource) { resources.put(name, resource); }

    // Add resource to map w/ file
    public static void addResource(File file, String dir) {
        try {
            // Grab file info
            String type = FileUtil.getType(file);
            String fileName = FileUtil.getName(file);
            String id = createId(dir, fileName);
            
            // Add resource by type
            switch (type) {
                case "image":
                    addResource(id, new Resource(Resource.Type.IMAGE, file.getAbsolutePath()));
                    break;
                default:
                    break;
            }
        } catch (IOException e) { return; }
        catch (NullPointerException e) { return; }
    }

    // Load resource from map
    public static Object loadResource(String name) { return resources.get(name).load(); }

    // Load resource from map w/ type
    public static <T> T loadResource(String name, Class<T> type) { return type.cast(resources.get(name).load()); }

    /* ================ [ SCANNER ] ================ */

    // Scan assets folder
    public static void scanFolder(String path) { scanFolder(path, null); }

    // Scan subfolders
    public static void scanFolder(String path, String dir) {
        File root = new File(path);
        File[] contents = root.listFiles();

        // Check contents
        if (contents != null) {
            for (File file : contents) {
                // Scan child folders
                if (dir == null && file.isDirectory()) 
                    scanFolder(file.getAbsolutePath(), file.getName());
                // Add resource to game
                else addResource(file, dir);
            }
        }
    }
    
}
