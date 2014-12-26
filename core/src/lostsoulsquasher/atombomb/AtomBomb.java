package lostsoulsquasher.atombomb;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;

public class AtomBomb extends Entity {
    public AtomBomb(LostSoulGame game) {
        super(game);
        actionBehavior = new AtomBombAction();
    }
}
