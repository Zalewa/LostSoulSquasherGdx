package lostsoulsquasher.actors.bonus.classes;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulWorld;
import lostsoulsquasher.atombomb.AtomBomb;
import lostsoulsquasher.render.RenderBehavior;

public class BonusAtomBomb extends BonusClass {
    @Override
    public void activate(LostSoulWorld world) {
        world.addActor(new AtomBomb(world.getGame()));
    }

    @Override
    public RenderBehavior mkRender(Entity entity) {
        return mkBonusRender(entity, entity.getGame().getContent().bonusAtom);
    }

    @Override
    public float getWeight() {
        return 0.7f;
    }
}
