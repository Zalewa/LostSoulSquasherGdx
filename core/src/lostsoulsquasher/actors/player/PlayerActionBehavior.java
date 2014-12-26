package lostsoulsquasher.actors.player;

import lostsoulsquasher.Entity;
import lostsoulsquasher.actors.explosion.Explosion;

public class PlayerActionBehavior implements lostsoulsquasher.Behavior {
    private static final float RefireTime = 0.02f;

    private float countdownUntilRefire = -1.0f;
    private boolean firing;

    @Override
    public void run(float elapsedTime, Entity entity) {
        if (countdownUntilRefire > 0.0f) {
            countdownUntilRefire -= elapsedTime;
        }
        if (firing) {
            if (canFire()) {
                fire(entity);
                countdownUntilRefire = RefireTime;
            }
            firing = false;
        }
    }

    private void fire(Entity entity)
    {
        Explosion boomActor = new Explosion(entity.getGame());
        boomActor.getBodyBehavior().setPosition(entity.getBodyBehavior().getPosition().cpy());
        entity.getGame().getWorld().addActor(boomActor);
    }

    private boolean canFire()
    {
        return countdownUntilRefire <= 0.0f;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public boolean isFiring() {
        return firing;
    }
}
