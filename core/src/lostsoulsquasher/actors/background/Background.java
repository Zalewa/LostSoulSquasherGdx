package lostsoulsquasher.actors.background;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.body.PlaneBodyBehavior;
import lostsoulsquasher.render.SpriteRenderBehavior;

public class Background extends Entity {
    private int currentBackgroundIndex = 0;

    public Background(LostSoulGame game) {
        super(game);
        bodyBehavior = new PlaneBodyBehavior(this);
        renderBehavior = new SpriteRenderBehavior(game, game.getContent().backgrounds[currentBackgroundIndex]);
        animationBehavior = new BackgroundAnimationBehavior(this);
    }

    public void cycleBackground() {
        ++currentBackgroundIndex;
        if (currentBackgroundIndex >= getGame().getContent().backgrounds.length)
        {
            currentBackgroundIndex = 0;
        }
        SpriteRenderBehavior render = (SpriteRenderBehavior)renderBehavior;
        render.setTexture(getGame().getContent().backgrounds[currentBackgroundIndex]);
    }
}
