package lostsoulsquasher.actors.lostsoul.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.LostSoulGame;

public class LostSoulBig extends LostSoulClass {
    public LostSoulBig(LostSoulGame game) {
        killScore = 300;
        damageScore = 50;
        health = 3;
        speed = 45.0f;
        color = Color.WHITE;
        difficultyFactor = 3.0f;
        scale = new Vector2(3.0f, 3.0f);
        activationSound = game.getContent().skullShowSound;
    }
}
