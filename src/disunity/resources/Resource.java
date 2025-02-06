package disunity.resources;

/**
 * A resource to be used in the game
 * 
 * @author Qinzhao Li
 */
public class Resource {

    /* ================ [ TYPES ] ================ */

    /**
     * The type of resource
     * 
     * @author Qinzhao Li
     */
    public enum Type {
        IMAGE
    }

    /* ================ [ FIELDS ] ================ */

    // Resource type
    private final Resource.Type type;

    // Resource path
    private final String path;

    // Constructors
    public Resource(Resource.Type type, String path) { this.type = type; this.path = path; }

    /* ================ [ METHODS ] ================ */
    
    // Load resource
    public Object load() {

        // Check resource type
        switch (type) {
            case IMAGE: return new Image.Builder().load(path).getImage();
            default: return null;
        }

    }
    
}
