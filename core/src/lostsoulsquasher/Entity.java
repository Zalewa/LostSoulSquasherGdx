package lostsoulsquasher;

import lostsoulsquasher.body.BodyBehavior;
import lostsoulsquasher.event.Event;
import lostsoulsquasher.render.RenderBehavior;

public class Entity {
    private LostSoulGame game;
    private boolean expired = false;

    protected BodyBehavior bodyBehavior = null;
    protected Behavior inputBehavior = null;
    protected Behavior actionBehavior = null;
    protected RenderBehavior renderBehavior = null;
    protected Behavior animationBehavior = null;
    protected MovementBehavior movementBehavior = null;
    protected CollisionBehavior collisionBehavior = null;
    protected HealthBehavior healthBehavior = null;

    public Event expiredChanged = new Event();

    public Entity(LostSoulGame game) {
        this.game = game;
    }

    public void update(float elapsedTime) {
        runIfNotNull(bodyBehavior, elapsedTime);
        runIfNotNull(inputBehavior, elapsedTime);
        runIfNotNull(actionBehavior, elapsedTime);
        runIfNotNull(healthBehavior, elapsedTime);
        runIfNotNull(movementBehavior, elapsedTime);
        runIfNotNull(animationBehavior, elapsedTime);
        runIfNotNull(collisionBehavior, elapsedTime);
    }

    public void draw(float elapsedTime) {
        if (renderBehavior != null) {
            renderBehavior.run(elapsedTime, this);
        }
    }

    private void runIfNotNull(Behavior behavior, float elapsedTime) {
        if (behavior != null) {
            behavior.run(elapsedTime, this);
        }
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
        expiredChanged.fire(this, null);
    }

    public LostSoulGame getGame() {
        return game;
    }

    public BodyBehavior getBodyBehavior() {
        return bodyBehavior;
    }

    public RenderBehavior getRenderBehavior() {
        return renderBehavior;
    }

    public void setRenderBehavior(RenderBehavior render) {
        this.renderBehavior = render;
    }

    public CollisionBehavior getCollisionBehavior() {
        return collisionBehavior;
    }

    public HealthBehavior getHealthBehavior() {
        return healthBehavior;
    }

    public Behavior getActionBehavior() {
        return actionBehavior;
    }

    public MovementBehavior getMovementBehavior() {
        return movementBehavior;
    }
}
