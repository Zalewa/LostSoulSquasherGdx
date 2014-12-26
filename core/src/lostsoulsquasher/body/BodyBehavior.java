package lostsoulsquasher.body;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.event.Event;

public abstract class BodyBehavior implements Behavior {
    private Entity entity;
    private Vector2 position;
    private Vector2 size;

    public Event positionChanged = new Event();

    public BodyBehavior(Entity entity) {
        this.entity = entity;
        this.position = Vector2.Zero.cpy();
        this.size = Vector2.Zero.cpy();
    }

    @Override
    public void run(float gameTime, Entity entity) {
    }

    public Entity getEntity() {
        return entity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        positionChanged.fire(this);
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public abstract Rectangle getBoundingRectangle();
}
