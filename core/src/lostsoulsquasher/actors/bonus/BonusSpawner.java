package lostsoulsquasher.actors.bonus;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;

public class BonusSpawner extends Entity {
    public BonusSpawner(LostSoulGame game) {
        super(game);
        actionBehavior = new BonusSpawnerBehavior();
    }
}
