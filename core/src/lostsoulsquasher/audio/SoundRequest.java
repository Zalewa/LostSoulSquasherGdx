package lostsoulsquasher.audio;

import com.badlogic.gdx.math.Vector2;

public class SoundRequest {
    private SoundDefinition sound;
    private Vector2 position;

    public SoundRequest(SoundDefinition sound) {
        this.sound = sound;
    }

    public SoundRequest(SoundDefinition sound, Vector2 position) {
        this.sound = sound;
        this.position = position;
    }

    public SoundDefinition getSound() {
        return sound;
    }

    public void setSound(SoundDefinition sound) {
        this.sound = sound;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
