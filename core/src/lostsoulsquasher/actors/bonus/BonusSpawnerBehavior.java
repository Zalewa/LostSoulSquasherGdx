package lostsoulsquasher.actors.bonus;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulWorld;
import lostsoulsquasher.actors.bonus.classes.BonusClass;
import lostsoulsquasher.actors.bonus.classes.BonusRandomFactory;
import lostsoulsquasher.exceptions.InvalidOperationException;

import java.util.Random;

public class BonusSpawnerBehavior implements Behavior {
    private static final float bonusStartingCountUp = -5.0f;
    private static final float bonusProbabilityInterval = 100.0f;
    private float timeSinceLastBonus = bonusStartingCountUp;
    private Random random = new Random();

    @Override
    public void run(float elapsedTime, Entity entity) {
        timeSinceLastBonus += elapsedTime;
        if (random.nextFloat() < getBonusProbability()) {
            spawnBonus(entity.getGame().getWorld());
            timeSinceLastBonus = bonusStartingCountUp;
        }
    }

    private void spawnBonus(LostSoulWorld world) {
        BonusClass klass = BonusRandomFactory.PickRandomBonus();
        if (klass == null) {
            throw new InvalidOperationException("null bonus class returned");
        }
        Bonus entity = new Bonus(world.getGame(), klass);
        Rectangle spawnField = new Rectangle(world.PlayField);
        float margin = entity.getBodyBehavior().getSize().x;
        spawnField.setWidth(spawnField.getWidth() - margin * 2.0f);
        spawnField.setHeight(spawnField.getHeight() - margin * 2.0f);
        spawnField.x += margin;
        spawnField.y += margin;
        entity.getBodyBehavior().setPosition(new Vector2(
                spawnField.x + random.nextFloat() * spawnField.getWidth(),
                spawnField.y + random.nextFloat() * spawnField.getHeight()));
        world.addActor(entity);
    }

    private float getBonusProbability() {
        return Math.max(0.0f, timeSinceLastBonus / bonusProbabilityInterval);
    }
}
