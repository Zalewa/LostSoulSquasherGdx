package lostsoulsquasher.actors.lostsoul;

import lostsoulsquasher.*;
import lostsoulsquasher.body.GameObjectBodyBehavior;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;
import lostsoulsquasher.factormodifier.FactorModifierActor;
import lostsoulsquasher.factormodifier.FactorModifierActorAddedEventArgs;
import lostsoulsquasher.actors.lostsoul.classes.LostSoulClass;
import lostsoulsquasher.render.SpriteRenderBehavior;

public class LostSoul extends Entity {
    private LostSoulPositionObserver positionObserver;
    private boolean started = false;
    private LostSoulClass klass;

    public LostSoul(LostSoulGame game, LostSoulClass klass) {
        super(game);
        this.klass = klass;
        bodyBehavior = new GameObjectBodyBehavior(this);

        SpriteRenderBehavior renderBehavior = new SpriteRenderBehavior(game, game.getContent().skullLeft);
        renderBehavior.centerOrigin();
        renderBehavior.setColor(klass.color);
        renderBehavior.setScale(klass.scale);
        this.renderBehavior = renderBehavior;

        bodyBehavior.setSize(renderBehavior.getSize().cpy().scl(klass.scale));

        movementBehavior = new MovementBehavior();
        animationBehavior = new LostSoulAnimation(game);
        collisionBehavior = new CollisionBehavior(this);
        setupHealthBehavior(klass);

        positionObserver = new LostSoulPositionObserver(this);
        for (FactorModifierActor actor : game.getWorld().getEnemySpeedModifierActors()) {
            if (!actor.isExpired()) {
                movementBehavior.addSpeedModifier(actor);
            }
        }
        game.getWorld().speedModifierActorAdded.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                onSpeedModifierActorAdded((FactorModifierActorAddedEventArgs) args);
            }
        });
    }

    private void setupHealthBehavior(final LostSoulClass klass) {
        healthBehavior = new HealthBehavior(this);
        healthBehavior.setHealth(klass.health);
        healthBehavior.deathEvent.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                getGame().getWorld().getPlayer().addScore(klass.killScore);
                setExpired(true);
            }
        });
        healthBehavior.damagedEvent.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                getGame().getWorld().getPlayer().addScore(klass.damageScore);
            }
        });
    }

    @Override
    public void update(float elapsedTime) {
        if (!started) {
            if (klass.activationSound != null) {
                getGame().getAudio().playSound(klass.activationSound);
            }
            started = true;
        }
        super.update(elapsedTime);
    }

    void onSpeedModifierActorAdded(FactorModifierActorAddedEventArgs e) {
        FactorModifierActorAddedEventArgs args = (FactorModifierActorAddedEventArgs) e;
        movementBehavior.addSpeedModifier(args.getActor());
    }

    public SpriteRenderBehavior getRender() {
        return (SpriteRenderBehavior) renderBehavior;
    }

    public LostSoulClass getKlass() {
        return klass;
    }
}
