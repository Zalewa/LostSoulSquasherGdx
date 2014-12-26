package lostsoulsquasher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import lostsoulsquasher.audio.SoundDefinition;

public class ContentLoader {
    public Texture[] backgrounds;
    public Texture gameOverBackground;
    public Texture skullLeft;
    public Texture skullRight;
    public Texture crosshair;

    public Texture[] explosion;

    public BitmapFont font;

    public SoundDefinition atomBoomSound;
    public SoundDefinition explosionSound;
    public SoundDefinition oneUpSound;
    public SoundDefinition babySound;
    public SoundDefinition turtleSound;
    public SoundDefinition skullShowSound;
    public SoundDefinition skullEscapeSound;

    public Music ambient1;
    public Music gameOverMusic;

    public Texture bonusOneUp;
    public Texture bonusFiveUp;
    public Texture bonusAtom;
    public Texture bonusBaby;
    public Texture bonusTurtle;

    public void loadContent() {
        backgrounds = new Texture[]{
                new Texture("backgrounds/bg1.png"),
                new Texture("backgrounds/bg2.png"),
                new Texture("backgrounds/bg3.png"),
                new Texture("backgrounds/bg4.png"),
                new Texture("backgrounds/bg5.png"),
                new Texture("backgrounds/bg6.png"),
                new Texture("backgrounds/bg7.png"),
        };
        gameOverBackground = new Texture("backgrounds/gameover1.png");
        skullLeft = new Texture("SKUL_left.png");
        skullRight = new Texture("SKUL.png");
        crosshair = new Texture("crosshair.png");

        explosion = new Texture[]{
                new Texture("MISLB0.png"),
                new Texture("MISLC0.png"),
                new Texture("MISLD0.png")
        };

        font = new BitmapFont();
        font.scale(0.2f);

        atomBoomSound = new SoundDefinition(loadSound("atomboom.wav"));
        explosionSound = new SoundDefinition(loadSound("DSBAREXP.wav"));
        oneUpSound = new SoundDefinition(loadSound("1up.wav"));
        babySound = new SoundDefinition(loadSound("baby.wav"));
        babySound.setMaxPitchVariation(0.15f);
        turtleSound = new SoundDefinition(loadSound("turtle.wav"));
        skullShowSound = new SoundDefinition(loadSound("DSDMACT.wav"));
        skullEscapeSound = new SoundDefinition(loadSound("DSSKLATK.wav"));

        ambient1 = loadMusic("Carefree.mp3");
        gameOverMusic = loadMusic("ecfike__the-end-of-the-world.mp3");

        bonusOneUp = new Texture("bonus_1up.png");
        bonusFiveUp = new Texture("bonus_5up.png");
        bonusAtom = new Texture("bonus_atom.png");
        bonusBaby = new Texture("bonus_baby.png");
        bonusTurtle = new Texture("bonus_turtle.png");
    }

    private Sound loadSound(String internal) {
        return Gdx.audio.newSound(Gdx.files.internal(internal));
    }

    private Music loadMusic(String internal) {
        return Gdx.audio.newMusic(Gdx.files.internal(internal));
    }
}
