package lostsoulsquasher.body;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Entity;
import lostsoulsquasher.exceptions.InvalidOperationException;

public class RenderDependentBodyBehavior extends BodyBehavior {
    public RenderDependentBodyBehavior(Entity entity) {
        super(entity);
    }


    @Override
    public Rectangle getBoundingRectangle() {
        Vector2 pos = getPosition();
        Vector2 origin = getEntity().getRenderBehavior().getOrigin();
        Vector2 size = getSize();
        return new Rectangle(pos.x - origin.x, pos.y - origin.y, size.x, size.y);
    }

    @Override
    public Vector2 getSize() {
        return getEntity().getRenderBehavior().getSize();
    }

    @Override
    public void setSize(Vector2 size) {
        throw new InvalidOperationException("size cannot be set on this body");
    }
}
