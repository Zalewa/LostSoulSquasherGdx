package lostsoulsquasher.actors.lostsoul.classes;

import com.badlogic.gdx.graphics.Color;

public class LostSoulUltraFast extends LostSoulClass {
    public LostSoulUltraFast()  {
        killScore = 200;
        speed = 180.0f;
        color = new Color(220.0f / 255.0f, 20.0f / 255.0f, 60.0f / 255.0f, 1.0f);
        difficultyFactor = 3.0f;
    }
}
