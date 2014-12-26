package lostsoulsquasher;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.actors.background.Background;
import lostsoulsquasher.actors.player.Player;
import lostsoulsquasher.actors.bonus.BonusSpawner;
import lostsoulsquasher.event.Event;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;
import lostsoulsquasher.factormodifier.FactorModifierActor;
import lostsoulsquasher.factormodifier.FactorModifierActorAddedEventArgs;
import lostsoulsquasher.actors.lostsoul.LostSoulSpawner;

import java.util.ArrayList;
import java.util.List;

public class LostSoulWorld extends Entity {
    public final Rectangle PlayField = new Rectangle(0, 0, 640, 480);
    public final int StartingLives = 10;

    private Background background;
    private Player player;
    private LostSoulSpawner enemySpawner;
    private BonusSpawner bonusSpawner;
    private LostSoulWorldHud hud;
    private OrthographicCamera camera;
    private List<Entity> actors = new ArrayList<Entity>();
    private List<Entity> expiredActors = new ArrayList<Entity>();
    private List<Entity> newActors = new ArrayList<Entity>();
    private List<CollisionBehavior> collisions = new ArrayList<CollisionBehavior>();
    private List<FactorModifierActor> enemySpeedModifierActors = new ArrayList<FactorModifierActor>();
    private float gameOverTime = 0.0f;

    public Event gameOverChanged = new Event();
    public Event speedModifierActorAdded = new Event();

    public LostSoulWorld(LostSoulGame game) {
        super(game);
    }

    public void addActor(Entity entity) {
        newActors.add(entity);
    }

    public void addSpeedModifierActor(FactorModifierActor actor) {
        newActors.add(actor);
        enemySpeedModifierActors.add(actor);
        speedModifierActorAdded.fire(this, new FactorModifierActorAddedEventArgs(actor));
    }

    private void moveNewActorsToActors() {
        for (Entity e : newActors) {
            actors.add(e);
        }
        newActors.clear();
    }


    public void loadContent() {
        getGame().getAudio().playMusic(getGame().getContent().ambient1);

        inputBehavior = new LostSoulWorldInputBehavior(this);

        background = new Background(getGame());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, PlayField.getWidth(), PlayField.getHeight());

        player = new Player(getGame());
        player.getBodyBehavior().setPosition(PlayField.getCenter(new Vector2()));
        player.getHealthBehavior().deathEvent.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                goToGameOver();
            }
        });
        player.getHealthBehavior().setHealthOmittingEventsAndChecks(StartingLives);

        enemySpawner = new LostSoulSpawner(getGame());
        bonusSpawner = new BonusSpawner(getGame());

        hud = new LostSoulWorldHud(this);
    }

    public void update(float gameTime) {
        if (isGameOver()) {
            gameOverTime += gameTime;
        }
        moveNewActorsToActors();
        background.update(gameTime);
        if (!isGameOver()) {
            enemySpawner.update(gameTime);
            bonusSpawner.update(gameTime);
            player.update(gameTime);
        }
        for (Entity e : actors) {
            e.update(gameTime);
            if (e.isExpired()) {
                expiredActors.add(e);
            }
        }
        firePendingCollisions();
        clearCollisions();
        removeExpiredActors();
        hud.update(gameTime);
        super.update(gameTime);
    }

    private void firePendingCollisions() {
        for (CollisionBehavior collision : collisions) {
            collision.firePendingCollisionEvents();
        }
    }

    private void clearCollisions() {
        for (CollisionBehavior collision : collisions) {
            collision.clearCollisions();
        }
    }

    private void removeExpiredActors() {
        for (Entity actor : expiredActors) {
            actors.remove(actor);
            if (actor.getCollisionBehavior() != null) {
                collisions.remove(actor.getCollisionBehavior());
            }
            if (actor instanceof FactorModifierActor) {
                enemySpeedModifierActors.remove((FactorModifierActor) actor);
            }
        }
        expiredActors.clear();
    }

    public void draw(float gameTime) {
        camera.update();
        getGame().getSpriteBatch().setProjectionMatrix(camera.combined);
        getGame().getSpriteBatch().begin();
        background.draw(gameTime);
        if (!isGameOver()) {
            enemySpawner.draw(gameTime);
            bonusSpawner.draw(gameTime);
        }
        for (Entity e : actors) {
            e.draw(gameTime);
        }
        if (!isGameOver()) {
            player.draw(gameTime);
        }
        hud.draw(gameTime);
        getGame().getSpriteBatch().end();
        super.draw(gameTime);
    }

    private void goToGameOver() {
        getGame().getAudio().playMusic(getGame().getContent().gameOverMusic);
        player.setExpired(true);
        enemySpawner.setExpired(true);
        bonusSpawner.setExpired(true);
        for (Entity actor : actors) {
            actor.setExpired(true);
        }
        gameOverChanged.fire(this);
    }

    public boolean isGameOver() {
        return player.getHealthBehavior().isDead();
    }

    public void registerCollision(CollisionBehavior collisionBehavior) {
        collisions.add(collisionBehavior);
    }

    public List<Entity> getActors() {
        return actors;
    }

    public Player getPlayer() {
        return player;
    }

    public float getMaxDifficulty() {
        return enemySpawner.getMaxDifficulty();
    }

    public float getDifficulty() {
        return enemySpawner.getDifficulty();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void addLives(int amount) {
        player.getHealthBehavior().setHealth(player.getHealthBehavior().getHealth() + amount);
    }

    public List<FactorModifierActor> getEnemySpeedModifierActors() {
        return enemySpeedModifierActors;
    }

    public void modifyDifficultyByFactor(float factor) {
        enemySpawner.modifyDifficultyByFactor(factor);
    }

    public Background getBackground() {
        return background;
    }

    public boolean canExitGameOverByTouching() {
        return gameOverTime > 3.0f;
    }
}
