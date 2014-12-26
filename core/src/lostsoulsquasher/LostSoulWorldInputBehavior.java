package lostsoulsquasher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;

public class LostSoulWorldInputBehavior implements Behavior {
    private LostSoulWorld world;

    public LostSoulWorldInputBehavior(LostSoulWorld world) {
        this.world = world;
    }

    @Override
    public void run(float elapsedTime, Entity entity) {
        if (world.isGameOver()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) || isGameOverTouchResetAllowed()) {
                world.getGame().resetGame();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            world.getGame().exit();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT_BRACKET))
        {
            world.getBackground().cycleBackground();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F))
        {
            world.getGame().toggleFullScreen();
        }
    }

    private boolean isGameOverTouchResetAllowed() {
        return world.canExitGameOverByTouching() && Gdx.input.justTouched();
    }
}
