package lostsoulsquasher.actors.background;

import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;
import lostsoulsquasher.render.SpriteRenderBehavior;

import java.util.Random;

public class BackgroundAnimationBehavior implements Behavior {
    private final float backgroundChangeInterval = 20.0f;

    private Entity entity;
    private float countdownTillBackgroundChange = 0.0f;
    private int backgroundIndex = -1;
    private Random random = new Random();

    public BackgroundAnimationBehavior(Entity entity) {
        this.entity = entity;
        registerGameOverEvent();
        randomizeBackgroundIndex(entity);
    }

    private void registerGameOverEvent() {
        entity.getGame().getWorld().gameOverChanged.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                SpriteRenderBehavior render = (SpriteRenderBehavior) entity.getRenderBehavior();
                render.setTexture(entity.getGame().getContent().gameOverBackground);
            }
        });
    }

    @Override
    public void run(float gameTime, Entity entity) {
        if (!entity.getGame().getWorld().isGameOver()) {
            countdownTillBackgroundChange -= gameTime;
            if (countdownTillBackgroundChange < 0.0f) {
                switchToNextBackground(entity);
                countdownTillBackgroundChange = backgroundChangeInterval;
            }
        }
    }

    private void switchToNextBackground(Entity entity) {
        SpriteRenderBehavior render = (SpriteRenderBehavior) entity.getRenderBehavior();
        randomizeBackgroundIndex(entity);
        render.setTexture(entity.getGame().getContent().backgrounds[backgroundIndex]);
    }

    private void randomizeBackgroundIndex(Entity entity) {
        int currentIndex = backgroundIndex;
        while (backgroundIndex == currentIndex) {
            backgroundIndex = random.nextInt(entity.getGame().getContent().backgrounds.length);
        }
    }
}
