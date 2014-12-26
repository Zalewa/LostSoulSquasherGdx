package lostsoulsquasher.audio;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AudioSystem {
    private Vector2 listenerPosition = Vector2.Zero;
    private float panDivisor = 1.0f;
    private float panClamp = 1.0f;
    private Random random = new Random();
    private List<SoundRequest> soundRequests = new ArrayList<SoundRequest>();
    private Music currentlyPlayingMusic;

    public AudioSystem() {
    }

    public void playSound(SoundDefinition sound) {
        SoundRequest request = new SoundRequest(sound);
        soundRequests.add(request);
    }

    public void playSound(SoundDefinition sound, Vector2 position) {
        SoundRequest request = new SoundRequest(sound, position);
        soundRequests.add(request);
    }

    public void fireSounds() {
        for (SoundRequest soundRequest : soundRequests) {
            fireSound(soundRequest);
        }
        soundRequests.clear();
    }

    private void fireSound(SoundRequest request) {
        float pan = 1.0f;
        float pitch = 1.0f + (request.getSound().getMaxPitchVariation() / 2.0f) -
                (float) random.nextFloat() * request.getSound().getMaxPitchVariation();
        if (request.getPosition() != null) {
            Vector2 relative = request.getPosition().cpy().sub(listenerPosition);
            pan = MathUtils.clamp(relative.x / panDivisor, -panClamp, panClamp);
        }
        request.getSound().getSound().play(1.0f, pitch, pan);
    }

    public void playMusic(Music music) {
        if (currentlyPlayingMusic != null) {
            currentlyPlayingMusic.stop();
        }
        currentlyPlayingMusic = music;
        music.play();
    }

    public void stopMusic() {
        if (currentlyPlayingMusic != null) {
            currentlyPlayingMusic.stop();
        }
    }

    public void setListenerPosition(Vector2 listenerPosition) {
        this.listenerPosition = listenerPosition;
    }

    public void setPanDivisor(float panDivisor) {
        this.panDivisor = panDivisor;
    }

    public void setPanClamp(float panClamp) {
        this.panClamp = panClamp;
    }

    public float getPanClamp() {
        return panClamp;
    }
}
