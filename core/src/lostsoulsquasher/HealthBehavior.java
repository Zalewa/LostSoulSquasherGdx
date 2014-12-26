package lostsoulsquasher;

import lostsoulsquasher.event.Event;

public class HealthBehavior implements Behavior {
    private boolean dead = false;
    private Entity entity;
    private int health = 0;

    public Event damagedEvent = new Event();
    public Event deathEvent = new Event();

    public HealthBehavior(Entity entity) {
        this.entity = entity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int value) {
        int oldHealth = health;
        health = value;
        if (!dead && health <= 0) {
            dead = true;
            deathEvent.fire(this);
        } else if (!dead && health > 0 && health < oldHealth) {
            damagedEvent.fire(this);
        }
    }

    /**
     * Doesn't summon any events. Doesn't revive from dead state.
     */
    public void setHealthOmittingEventsAndChecks(int health) {
        this.health = health;
    }

    @Override
    public void run(float gameTime, Entity entity) {
        // no-op
    }

    public boolean isDead() {
        return dead;
    }
}
