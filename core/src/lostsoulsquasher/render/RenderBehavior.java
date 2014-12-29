package lostsoulsquasher.render;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Behavior;

public abstract class RenderBehavior implements Behavior {
    private Vector2 origin = Vector2.Zero.cpy();
    private Rectangle boundingRect = new Rectangle();

    public void centerOrigin() {
        Vector2 size = getSize();
        origin.x = size.x / 2.0f;
        origin.y = size.y / 2.0f;
    }

    public Rectangle getBoundingRectangle() {
        Vector2 size = getSize();
        boundingRect.set(-origin.x, -origin.y, size.x, size.y);
        return boundingRect;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        this.origin.x = origin.x;
        this.origin.y = origin.y;
    }

    public abstract Vector2 getSize();
}
