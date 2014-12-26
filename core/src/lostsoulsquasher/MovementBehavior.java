package lostsoulsquasher;

import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;
import lostsoulsquasher.factormodifier.FactorModifierActor;

import java.util.ArrayList;
import java.util.List;

public class MovementBehavior implements Behavior {
    public Vector2 velocity;
    private List<FactorModifierActor> speedModifiers = new ArrayList<FactorModifierActor>();

    public MovementBehavior() {
        velocity = Vector2.Zero.cpy();
    }

    @Override
    public void run(float gameTime, Entity entity) {
        Vector2 pos = entity.getBodyBehavior().getPosition();
        pos.mulAdd(velocity, gameTime * getTotalSpeedModifiers());
        entity.getBodyBehavior().setPosition(pos);
    }

    public void addSpeedModifier(FactorModifierActor actor) {
        speedModifiers.add(actor);
        actor.expiredChanged.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                speedModifiers.remove((FactorModifierActor) sender);
            }
        });
    }

    private float getTotalSpeedModifiers() {
        float factor = 1.0f;
        for (FactorModifierActor modifier : speedModifiers) {
            if (!modifier.isExpired()) {
                factor *= modifier.getFactor();
            }
        }
        return factor;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
