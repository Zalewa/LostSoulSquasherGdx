package lostsoulsquasher.audio;

import com.badlogic.gdx.audio.Sound;

public class SoundDefinition {
    private Sound sound;
    private float maxPitchVariation;

    public SoundDefinition(Sound sound) {
        this.sound = sound;
        this.maxPitchVariation = 0.3f;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public float getMaxPitchVariation() {
        return maxPitchVariation;
    }

    public void setMaxPitchVariation(float maxPitchVariation) {
        this.maxPitchVariation = maxPitchVariation;
    }
}
