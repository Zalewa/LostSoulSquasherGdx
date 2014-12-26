package lostsoulsquasher.render;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Behavior;

public abstract class RenderBehavior implements Behavior {
    private Vector2 origin = Vector2.Zero.cpy();

    public void centerOrigin() {
        origin = Vector2.Zero.cpy().mulAdd(getSize(), 0.5f);
    }

    public Rectangle getBoundingRectangle() {
        Vector2 size = getSize();
        return new Rectangle(-origin.x, -origin.y, size.x, size.y);
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }

    public abstract Vector2 getSize();
}
