package lostsoulsquasher.actors.lostsoul;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;
import lostsoulsquasher.actors.lostsoul.classes.LostSoulClass;
import lostsoulsquasher.actors.lostsoul.classes.LostSoulClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LostSoulSpawnerBehavior implements Behavior {
    private enum Edge {
        Left, Right, Top, Bottom
    }

    private final static float MaxDifficulty = 50.0f;
    private final static float MinDifficulty = 1.0f;
    private final static float ChanceOfFasterSpeed = 0.1f;
    private final static float MinSpawnCountdown = 0.2f;

    private Random random = new Random();
    private float countdownTillSpawn = 1.0f;
    private int maxSouls = 30;
    private List<Entity> souls = new ArrayList<Entity>();


    private float difficulty;

    @Override
    public void run(float elapsedTime, Entity entity) {
        difficulty += elapsedTime * 0.5f;
        if (difficulty > MaxDifficulty) {
            difficulty = MaxDifficulty;
        }
        countdownTillSpawn -= elapsedTime;
        if (shouldSpawn()) {
            spawn(entity);
            countdownTillSpawn = Math.max(1.0f - difficulty / MaxDifficulty, MinSpawnCountdown);
        }
    }

    private void spawn(Entity entity) {
        Edge edge = pickEdge();
        LostSoul soul = pickSoul(entity.getGame());
        soul.getBodyBehavior().setPosition(pickLocation(entity.getGame(), edge));
        soul.getMovementBehavior().setVelocity(pickVelocity(entity.getGame(), soul));
        soul.expiredChanged.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                souls.remove((Entity) sender);
            }
        });
        souls.add(soul);
        entity.getGame().getWorld().addActor(soul);
    }

    private LostSoul pickSoul(LostSoulGame game) {
        LostSoulClass klass = pickSoulClass();
        return new LostSoul(game, klass);
    }

    private LostSoulClass pickSoulClass() {
        float bigSkull = random.nextFloat();
        if (bigSkull < 0.05f) {
            return LostSoulClasses.getBig();
        }
        float roll = random.nextFloat() * difficulty;

        if (roll > MaxDifficulty * 0.85f) {
            return LostSoulClasses.getUltraFast();
        } else if (roll > MaxDifficulty * 0.65f) {
            return LostSoulClasses.getFast();
        } else if (roll > MaxDifficulty * 0.30f) {
            return LostSoulClasses.getAverage();
        } else {
            return LostSoulClasses.getSlow();
        }
    }

    private Vector2 pickLocation(LostSoulGame game, Edge edge) {
        Rectangle playField = game.getWorld().PlayField;
        switch (edge) {
            case Left:
                return new Vector2(playField.getX(), playField.getY() + random.nextFloat() * playField.getHeight());
            case Right:
                return new Vector2(playField.getX() + playField.getWidth(), playField.getY() + random.nextFloat() * playField.getHeight());
            case Top:
                return new Vector2(playField.getX() + random.nextFloat() * playField.getWidth(), playField.getY());
            case Bottom:
                return new Vector2(playField.getX() + random.nextFloat() * playField.getWidth(), playField.getY() + playField.getHeight());
            default:
                throw new UnsupportedOperationException("unknown edge " + edge);
        }
    }

    private Vector2 pickVelocity(LostSoulGame game, LostSoul soul) {
        float speed = soul.getKlass().speed;
        if (random.nextFloat() < ChanceOfFasterSpeed) {
            speed *= 2.0f;
        }

        Rectangle playField = game.getWorld().PlayField;
        Vector2 position = soul.getBodyBehavior().getPosition();
        Vector2 diff = playField.getCenter(new Vector2()).cpy().sub(position).nor();

        float angle = 22.5f - (45.0f * random.nextFloat());
        Vector2 rotated = new Vector2(
                diff.x * MathUtils.cosDeg(angle) - diff.y * MathUtils.sinDeg(angle),
                diff.y * MathUtils.cosDeg(angle) + diff.x * MathUtils.sinDeg(angle));
        return rotated.scl(speed);
    }

    private Edge pickEdge() {
        return Edge.values()[random.nextInt(Edge.values().length)];
    }

    private boolean shouldSpawn() {
        return countdownTillSpawn <= 0.0f && souls.size() < maxSouls;
    }

    public static float getMaxDifficulty() {
        return MaxDifficulty;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(float difficulty) {
        this.difficulty = Math.min(Math.max(difficulty, MinDifficulty), MaxDifficulty);
    }
}
