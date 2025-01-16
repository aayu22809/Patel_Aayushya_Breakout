package ljaag.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ljaag.util.ImageUtil;
import ljaag.util.Vector2;

public class SpriteComponent extends Component {

    /* ================ [ FIELDS ] ================ */

    /** The sprite texture */
    protected BufferedImage texture;

    /**
     * Construct a Sprite.
     * 
     * @param texture The sprite texture
     * @param children Components to add as children
     */
    public SpriteComponent(BufferedImage texture, Component... children) {
        super(children);
        setTexture(texture);
    }

    /**
     * Construct a Sprite w/ texture path.
     * 
     * @param path The path to the sprite texture
     * @param children Components to add as children
     */
    public SpriteComponent(String path, Component... children) {
        super(children);
        setTexture(path);
    }

    /**
     * Construct a Sprite w/ position.
     * 
     * @param texture The sprite texture
     * @param pos The sprite position
     * @param children Components to add as children
     */
    public SpriteComponent(BufferedImage texture, Vector2 pos, Component... children) {
        super(pos, children);
        setTexture(texture);
    }

    /**
     * Construct a Sprite w/ texture path & position.
     * 
     * @param path The path to the sprite texture
     * @param pos The sprite position
     * @param children Components to add as children
     */
    public SpriteComponent(String path, Vector2 pos, Component... children) {
        super(pos, children);
        setTexture(path);
    }
    
    /* ================ [ METHODS ] ================ */

    /**
     * Set the sprite texture.
     * 
     * @param texture The sprite texture
    */
    public void setTexture(BufferedImage texture) { this.texture = texture; }
    /**
     * Set the sprite texture from a path.
     * 
     * @param path The path to the sprite texture
    */
    public void setTexture(String path) { this.texture = new ImageUtil(path).getImage(); }

    /* ================ [ COMPONENT ] ================ */

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(texture, (int) position.x, (int) position.y, null);
    }
    
}
