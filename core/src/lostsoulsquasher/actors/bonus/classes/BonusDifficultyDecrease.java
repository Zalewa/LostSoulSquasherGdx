package lostsoulsquasher.actors.bonus.classes;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulWorld;
import lostsoulsquasher.render.RenderBehavior;

public class BonusDifficultyDecrease extends BonusClass {
    @Override
    public void activate(LostSoulWorld world) {
        world.getGame().getAudio().playSound(world.getGame().getContent().babySound);
        world.modifyDifficultyByFactor(-0.25f);
    }

    @Override
    public RenderBehavior mkRender(Entity entity) {
        return mkBonusRender(entity, entity.getGame().getContent().bonusBaby);
    }

    @Override
    public float getWeight() {
        return 0.5f;
    }
}
