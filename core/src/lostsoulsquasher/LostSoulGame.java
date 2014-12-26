package lostsoulsquasher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.actors.lostsoul.classes.LostSoulClasses;
import lostsoulsquasher.audio.AudioSystem;
import lostsoulsquasher.exceptions.ErrorDump;

public class LostSoulGame extends ApplicationAdapter {
    private AudioSystem audio;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private ContentLoader content;
    private GameConfig config;
    private LostSoulWorld world;

    public LostSoulGame(GameConfig config) {
        this.config = config;
    }

    @Override
    public void create() {
        try {
            createWrapped();
        } catch (Throwable e) {
            ErrorDump.dumpExceptionToTempFile(e);
            throw new RuntimeException(e);
        }
    }

    private void createWrapped() {
        Gdx.input.setCursorCatched(true);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        audio = new AudioSystem();
        audio.setPanClamp(0.9f);

        content = new ContentLoader();
        content.loadContent();

        LostSoulClasses.loadContent(this);

        resetGame();
    }

    @Override
    public void render() {
        try {
            renderWrapped();
        } catch (Throwable e) {
            ErrorDump.dumpExceptionToTempFile(e);
            throw new RuntimeException(e);
        }
    }

    private void renderWrapped() {
        float elapsedTime = Gdx.graphics.getDeltaTime();
        update(elapsedTime);
        draw(elapsedTime);
    }

    public void resetGame() {
        world = new LostSoulWorld(this);
        world.loadContent();

        Vector2 center = world.PlayField.getCenter(new Vector2());
        audio.setListenerPosition(center);
        audio.setPanDivisor(world.PlayField.getWidth() / 2);
    }

    private void update(float elapsedTime) {
        world.update(elapsedTime);
        audio.fireSounds();
    }

    private void draw(float elapsedTime) {
        Color color = Color.TEAL;
        Gdx.gl.glClearColor(color.r, color.g, color.b, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.draw(elapsedTime);
    }

    public AudioSystem getAudio() {
        return audio;
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public ContentLoader getContent() {
        return content;
    }

    public LostSoulWorld getWorld() { return world; }

    public GameConfig getConfig() {
        return config;
    }

    public void exit() {
        dispose();
        Gdx.app.exit();
    }

    public void toggleFullScreen() {
        if (Gdx.graphics.isFullscreen()) {
            Gdx.graphics.setDisplayMode(800, 480, false);
        } else {
            Graphics.DisplayMode mode = Gdx.graphics.getDesktopDisplayMode();
            Gdx.graphics.setDisplayMode(mode.width, mode.height, true);
        }
    }
}
