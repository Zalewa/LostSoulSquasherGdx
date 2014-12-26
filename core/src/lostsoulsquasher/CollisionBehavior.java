package lostsoulsquasher;

import lostsoulsquasher.event.Event;

import java.util.HashSet;

public class CollisionBehavior implements Behavior {
    private boolean enabled = true;
    private HashSet<Entity> colliding = new HashSet<Entity>();

    public Event collisionDetected = new Event();

    public CollisionBehavior(Entity entity) {
        entity.getGame().getWorld().registerCollision(this);
    }

    public boolean collides(Entity e1, Entity e2) {
        return e1.getBodyBehavior().getBoundingRectangle().overlaps(e2.getBodyBehavior().getBoundingRectangle());
    }

    @Override
    public void run(float gameTime, Entity entity) {
        for (Entity other : entity.getGame().getWorld().getActors()) {
            if (shouldCheckCollision(entity, other) && collides(entity, other)) {
                other.getCollisionBehavior().addColliding(entity);
                addColliding(other);
            }
        }
    }

    public void firePendingCollisionEvents() {
        if (colliding.size() > 0) {
            collisionDetected.fire(this);
        }
    }

    public void clearCollisions() {
        colliding.clear();
    }

    private boolean shouldCheckCollision(Entity us, Entity other) {
        return us != other && isEnabled()
                && !us.isExpired() && !other.isExpired()
                && other.getCollisionBehavior() != null
                && other.getCollisionBehavior().isEnabled()
                && !colliding.contains(other);
    }

    private void addColliding(Entity entity) {
        colliding.add(entity);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public HashSet<Entity> getColliding() {
        return colliding;
    }
}
