public class Brick extends Node2D<Brick> implement Collider {

    private int pointValue;
    private final Color color;
    private boolean destroyed = false;

    public Brick(Vector2D position,  int pointValue, Color color) {
        this.pointValue = pointValue;
        this.color = color;
        setPos(position);
    }

    public void hit() {
        if (!destroyed) {
            destroyed = true;
            // Remove the node from the scene
            // TODO: add animations
            getParent().removeChild(this);
        }
    }

    public int getPointValue() {
        return pointValue;
    }
    
}
