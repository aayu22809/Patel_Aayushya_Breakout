package com.apcs.disunity.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

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
    public static void addResource(File file) {
        try {
            String type = Files.probeContentType(file.toPath()).split("/")[0];
            String name = file.getName();

            if (name.indexOf('.') != -1)
                name = name.substring(0, name.lastIndexOf('.'));

            switch (type) {
                case "image": addResource(name, new Resource(Resource.Type.IMAGE, file.getAbsolutePath())); break;
                default: break;
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Load resource
    public static Object loadResource(String name) { return resources.get(name).load(); }
    public static <T> T loadResource(String name, Class<T> type) { return type.cast(resources.get(name).load()); }

    // Scan folder
    public static void scanFolder(String path) { scanFolder(path, false); }
    public static void scanFolder(String path, boolean deep) {
        File root = new File(path);
        File[] contents = root.listFiles();

        if (contents != null) {
            for (File file : contents) {
                if (deep && file.isDirectory())
                    scanFolder(file.getAbsolutePath(), true);

                addResource(file);
            }
        }
    }
    
}
