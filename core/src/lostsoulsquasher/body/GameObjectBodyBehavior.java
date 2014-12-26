package lostsoulsquasher.body;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Entity;

public class GameObjectBodyBehavior extends BodyBehavior {
    public GameObjectBodyBehavior(Entity entity) {
        super(entity);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        Vector2 pos = getPosition();
        Vector2 size = getSize();
        return new Rectangle(pos.x - size.x / 2.0f, pos.y - size.y / 2.0f, size.x, size.y);
    }
}
