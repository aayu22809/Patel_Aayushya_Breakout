package com.apcs.disunity.app.resources;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A manager that handles all of the resources in the game.
 * 
 * @author Qinzhao Li
 * @author Sharvil Phadke
 */
public class Resources {

    /* ================ [ FIELDS ] ================ */

    // Maps resource ids to resources
    private static final Map<String, Object> resources = new HashMap<>();
    public static final Map<Class<?>, Function<URL, ?>> loaders = new HashMap<>();
    static {
        loaders.put(Image.class, Image::new);
        loaders.put(Sound.class, Sound::new);
    }

    /* ================ [ METHODS ] ================ */

    // Add resource to map
    public static void addResource(String name, Object resource) { resources.put(name, resource); }

    // Add resource to map w/ file
    public static void addResource(String path, Class<?> type) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            lookup.ensureInitialized(type);
        } catch (IllegalAccessException e) {
        }
        resources.put(path, loaders.get(type).apply(Resources.class.getClassLoader().getResource(path)));
    }

    // Load resource from map
    public static <T> T loadResource(String path, Class<T> type) {
        if (!resources.containsKey(path))
            addResource(path, type);
        Object o = resources.get(path);
        if (o == null)
            throw new RuntimeException("Unable to load resource: " + path);
        return type.cast(o);
    }
}
