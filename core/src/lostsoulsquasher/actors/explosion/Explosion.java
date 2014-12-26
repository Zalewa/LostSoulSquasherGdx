package lostsoulsquasher.actors.explosion;

import lostsoulsquasher.CollisionBehavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.animation.AnimationBehavior;
import lostsoulsquasher.animation.AnimationFrame;
import lostsoulsquasher.body.GameObjectBodyBehavior;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;
import lostsoulsquasher.render.SpriteRenderBehavior;

public class Explosion extends Entity {
    private boolean afterFirstUpdate = false;

    public Explosion(LostSoulGame game) {
        super(game);

        bodyBehavior = new GameObjectBodyBehavior(this);
        renderBehavior = new SpriteRenderBehavior(game, game.getContent().explosion[0]);
        bodyBehavior.setSize(renderBehavior.getSize().cpy());

        AnimationBehavior animationBehavior = new AnimationBehavior(AnimationFrame.mkCentered(game.getContent().explosion), 0.1f);
        animationBehavior.setMarkEntityAsExpiredWhenDone(true);
        this.animationBehavior = animationBehavior;

        collisionBehavior = new CollisionBehavior(this);
        collisionBehavior.collisionDetected.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                damageCollided();
            }
        });
    }

    private void damageCollided() {
        for (Entity colliding : getCollisionBehavior().getColliding()) {
            if (colliding.getHealthBehavior() != null) {
                colliding.getHealthBehavior().setHealth(colliding.getHealthBehavior().getHealth() - 1);
            }
        }
    }

    @Override
    public void update(float elapsedTime) {
        super.update(elapsedTime);
        if (!afterFirstUpdate) {
            getGame().getAudio().playSound(getGame().getContent().explosionSound, getBodyBehavior().getPosition());
            getCollisionBehavior().setEnabled(false);
            afterFirstUpdate = true;
        }
    }
}
