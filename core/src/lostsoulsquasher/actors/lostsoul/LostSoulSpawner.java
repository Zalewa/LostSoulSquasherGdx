package lostsoulsquasher.actors.lostsoul;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;

public class LostSoulSpawner extends Entity {
    public LostSoulSpawner(LostSoulGame game)
    {
        super(game);
        actionBehavior = new LostSoulSpawnerBehavior();
    }

    public void modifyDifficultyByFactor(float factor) {
        setDifficulty(getDifficulty() + factor * getMaxDifficulty());
    }

    public float getDifficulty()
    {
        return ((LostSoulSpawnerBehavior)actionBehavior).getDifficulty();
    }

    public void setDifficulty(float value)
    {
        ((LostSoulSpawnerBehavior)actionBehavior).setDifficulty(value);
    }

    public float getMaxDifficulty()
    {
        return LostSoulSpawnerBehavior.getMaxDifficulty();
    }
}
