package com.apcs.disunity.app.resources;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Handles all of the resources in the game
 * 
 * @author Qinzhao Li
 * @author Sharvil Phadke
 */
public class Resources {

    /* ================ [ FIELDS ] ================ */

    // Maps resource ids to resources
    private static final Map<String, Object> resources = new HashMap<>();
    public static final Map<Class<?>,Function<InputStream,?>> loaders = new HashMap<>();

    /* ================ [ METHODS ] ================ */

    // Add resource to map
    public static void addResource(String name, Object resource) { resources.put(name, resource); }

    // Add resource to map w/ file
    public static void addResource(String path, Class<?> type) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try { lookup.ensureInitialized(type); }
        catch (IllegalAccessException e) { }
        resources.put(path, loaders.get(type).apply(loadFileAsInputStream(path)));
    }

    // Load resource from map
    public static <T> T loadResource(String path, Class<T> type) {
        if (!resources.containsKey(path)) addResource(path, type);
        Object o = resources.get(path);
        if (o == null) throw new RuntimeException("Unable to load resource: "+path);
        return type.cast(o);
    }

    // Loads a file as an input stream
    public static final InputStream loadFileAsInputStream(String path) {
        InputStream output = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (output == null) throw new RuntimeException("Unable to load file: "+path);
        return output;
    }
    
}
