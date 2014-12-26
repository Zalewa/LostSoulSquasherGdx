package lostsoulsquasher.actors.player;

import com.badlogic.gdx.graphics.Texture;
import lostsoulsquasher.Entity;
import lostsoulsquasher.HealthBehavior;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.body.GameObjectBodyBehavior;
import lostsoulsquasher.render.SpriteRenderBehavior;

public class Player extends Entity {
    private int score = 0;

    public Player(LostSoulGame game) {
        super(game);

        Texture texture = game.getContent().crosshair;
        bodyBehavior = new GameObjectBodyBehavior(this);
        if (!game.getConfig().isTouchScreen()) {
            renderBehavior = new SpriteRenderBehavior(game, texture);
            renderBehavior.centerOrigin();
        }

        inputBehavior = new PlayerInputBehavior(game);
        actionBehavior = new PlayerActionBehavior();
        healthBehavior = new HealthBehavior(this);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
