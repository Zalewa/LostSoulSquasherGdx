package lostsoulsquasher.atombomb;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.actors.explosion.Explosion;

import java.util.ArrayList;
import java.util.List;

public class AtomBombAction implements Behavior {
    private float boomTime = 0.2f;
    private float countDownUntilNextBoom = 0.0f;
    private float intervalBetweenBooms = 0.0f;
    private boolean started = false;
    private List<Vector2> positions;

    @Override
    public void run(float elapsedTime, Entity entity) {
        if (!started) {
            start(entity);
        }

        if (positions.size() != 0) {
            runExplosion(elapsedTime, entity.getGame());
        } else {
            entity.setExpired(true);
        }
    }

    private void runExplosion(float elapsedTime, LostSoulGame game) {
        countDownUntilNextBoom -= elapsedTime;
        if (countDownUntilNextBoom <= 0.0f) {
            spawnExplosion(game, positions.get(0));
            positions.remove(0);
            countDownUntilNextBoom = intervalBetweenBooms;
        }
    }

    private void spawnExplosion(LostSoulGame game, Vector2 position) {
        Explosion explosion = new Explosion(game);
        explosion.getBodyBehavior().setPosition(position);
        game.getWorld().addActor(explosion);
    }

    private void start(Entity entity) {
        positions = calculateExplosionPositions(entity.getGame());
        intervalBetweenBooms = boomTime / (float) positions.size();
        entity.getGame().getAudio().playSound(entity.getGame().getContent().atomBoomSound);
        started = true;
    }

    private List<Vector2> calculateExplosionPositions(LostSoulGame game) {
        List<Vector2> result = new ArrayList<Vector2>();
        Explosion explosionPrototype = new Explosion(game);
        Vector2 size = explosionPrototype.getBodyBehavior().getSize();
        Rectangle playField = game.getWorld().PlayField;
        for (float x = size.x; x < playField.getWidth(); x += size.x) {
            for (float y = size.y; y < playField.getHeight(); y += size.y) {
                result.add(new Vector2(x, y));
            }
        }
        return result;
    }
}
