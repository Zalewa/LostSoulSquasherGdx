package lostsoulsquasher.actors.bonus.classes;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulWorld;
import lostsoulsquasher.factormodifier.FactorModifierActor;
import lostsoulsquasher.render.RenderBehavior;

public class BonusEnemySlowDown extends BonusClass {
    @Override
    public void activate(LostSoulWorld world) {
        world.getGame().getAudio().playSound(world.getGame().getContent().turtleSound);
        world.addSpeedModifierActor(new FactorModifierActor(world.getGame(), 0.2f, 3.0f));
    }

    @Override
    public RenderBehavior mkRender(Entity entity) {
        return mkBonusRender(entity, entity.getGame().getContent().bonusTurtle);
    }

    @Override
    public float getWeight() {
        return 1.0f;
    }
}
