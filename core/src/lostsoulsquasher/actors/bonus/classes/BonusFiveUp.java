package lostsoulsquasher.actors.bonus.classes;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulWorld;
import lostsoulsquasher.render.RenderBehavior;

public class BonusFiveUp extends BonusClass {
    @Override
    public void activate(LostSoulWorld world) {
        world.getGame().getAudio().playSound(world.getGame().getContent().oneUpSound);
        world.addLives(5);
    }

    @Override
    public RenderBehavior mkRender(Entity entity) {
        return mkBonusRender(entity, entity.getGame().getContent().bonusFiveUp);
    }

    @Override
    public float getWeight() {
        return 0.1f;
    }
}
