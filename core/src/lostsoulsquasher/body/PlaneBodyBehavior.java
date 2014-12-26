package lostsoulsquasher.body;

import com.badlogic.gdx.math.Rectangle;
import lostsoulsquasher.Entity;

public class PlaneBodyBehavior extends BodyBehavior {
    public PlaneBodyBehavior(Entity entity) {
        super(entity);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        return new Rectangle(getPosition().x, getPosition().y, getSize().x, getSize().y);
    }
}
