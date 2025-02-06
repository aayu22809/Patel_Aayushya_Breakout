package disunity.resources;

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

    // Load resource
    public static Object loadResource(String name) { return resources.get(name).load(); }
    
}
