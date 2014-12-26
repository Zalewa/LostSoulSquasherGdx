package lostsoulsquasher.actors.lostsoul.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.audio.SoundDefinition;

public class LostSoulClass {
    public Color color = Color.WHITE;
    public float speed;
    // Lower factor = less difficulty.
    public float difficultyFactor;
    public Vector2 scale = new Vector2(1.0f, 1.0f);
    public int health = 1;
    public int killScore = 100;
    public int damageScore = 0;
    public SoundDefinition activationSound;
}
