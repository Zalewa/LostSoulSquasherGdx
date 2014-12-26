package lostsoulsquasher.actors.bonus;

import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.*;
import lostsoulsquasher.body.GameObjectBodyBehavior;
import lostsoulsquasher.actors.bonus.classes.BonusClass;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;

public class Bonus extends Entity {
    private BonusClass klass;

    public Bonus(LostSoulGame game, BonusClass klass) {
        super(game);
        this.klass = klass;

        bodyBehavior = new GameObjectBodyBehavior(this);
        bodyBehavior.setSize(new Vector2(32.0f, 32.0f));

        renderBehavior = klass.mkRender(this);
        renderBehavior.centerOrigin();

        healthBehavior = new HealthBehavior(this);
        healthBehavior.deathEvent.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                onDeathHandler();
            }
        });

        collisionBehavior = new CollisionBehavior(this);
        actionBehavior = new ExpirationActionBehavior(2.0f);
    }

    void onDeathHandler() {
        getGame().getWorld().getPlayer().addScore(klass.getScore());
        klass.activate(getGame().getWorld());
        setExpired(true);
    }
}
