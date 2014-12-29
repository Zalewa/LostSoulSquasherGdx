package lostsoulsquasher.body;

import com.badlogic.gdx.math.Rectangle;
import lostsoulsquasher.Entity;

public class PlaneBodyBehavior extends BodyBehavior {
    private Rectangle boundingRect = new Rectangle();

    public PlaneBodyBehavior(Entity entity) {
        super(entity);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        boundingRect.set(getPosition().x, getPosition().y, getSize().x, getSize().y);
        return boundingRect;
    }
}
