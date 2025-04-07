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
    public static void addResource(File file, String dir) {
        try {
            String type = Files.probeContentType(file.toPath()).split("/")[0];
            String fileName= file.getName();

            // Clean up file name
            if (fileName.indexOf('.') != -1) {
                fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            }

            // Grab info from file name
            String[] info = fileName.split("-");
            String name = info[0];

            // Add resource by type
            switch (type) {
                case "image":
                    addResource(name, new Resource(Resource.Type.IMAGE, file.getAbsolutePath()));
                    // anim stuff
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

        if (contents != null) {
            for (File file : contents) {
                if (dir == null && file.isDirectory()) {
                    scanFolder(file.getAbsolutePath(), file.getName());
                } else {
                    addResource(file, dir);
                }
            }
        }
    }
    
}
