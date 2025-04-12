package com.apcs.disunity.resources;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.apcs.disunity.files.FileUtil;

/**
 * Handles all resources in the game
 * 
 * @author Qinzhao Li
 */
public class Resources {

    /* ================ [ FIELDS ] ================ */

    // Resources map
    private static final Map<String, Resource> resources = new HashMap<>();

    /* ================ [ METHODS ] ================ */

    // Add resource
    public static void addResource(String name, Resource resource) { resources.put(name, resource); }

    // Add resource w/ file
    public static void addResource(File file, String dir) {
        try {
            // Grab file info
            String type = FileUtil.getType(file);
            String fileName = FileUtil.getName(file);

            String[] info = FileUtil.parseInfo(fileName);
            String name = info[0];

            // Directory prefix
            if (dir != null && !name.equals(dir)) {
                name = dir + "_" + name;
            }

            // Capitalize name
            name = name.toUpperCase();

            // Add resource by type
            switch (type) {
                case "image":
                    // Image sets
                    if (info.length == 2) addResource(name, new Resource(Resource.Type.IMAGESET, file.getAbsolutePath()));
                    // Regular images
                    else addResource(name, new Resource(Resource.Type.IMAGE, file.getAbsolutePath()));
                    break;
                default:
                    break;
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Load resource
    public static Object loadResource(String name) { return resources.get(name).load(); }
    public static <T> T loadResource(String name, Class<T> type) { return type.cast(resources.get(name).load()); }

    /* ================ [ SCANNER ] ================ */

    // Scan folder
    public static void scanFolder(String path) { scanFolder(path, null); }
    public static void scanFolder(String path, String dir) {
        File root = new File(path);
        File[] contents = root.listFiles();

        // Check contents
        if (contents != null) {
            for (File file : contents) {
                // Scan child folders
                if (dir == null && file.isDirectory()) {
                    scanFolder(file.getAbsolutePath(), file.getName());
                }
                // Add resource to game
                else {
                    addResource(file, dir);
                }
            }
        }
    }
    
}
