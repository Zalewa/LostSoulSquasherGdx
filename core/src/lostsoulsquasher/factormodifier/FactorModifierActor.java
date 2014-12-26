package lostsoulsquasher.factormodifier;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;

public class FactorModifierActor extends Entity {
    public FactorModifierActor(LostSoulGame game, float factor, float countdown) {
        super(game);
        actionBehavior = new FactorModifierBehavior(factor, countdown);
    }

    public float getFactor() {
        return ((FactorModifierBehavior)actionBehavior).getFactor();
    }
}
