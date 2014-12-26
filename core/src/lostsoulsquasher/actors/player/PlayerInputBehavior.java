package lostsoulsquasher.actors.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;

public class PlayerInputBehavior implements Behavior {
    private LostSoulGame game;
    private boolean wasLeftPressed = false;

    public PlayerInputBehavior(LostSoulGame game) {
        this.game = game;
    }

    @Override
    public void run(float elapsedTime, Entity entity) {
        processMousePosition(entity);
        processButtons(entity);
    }

    private void processButtons(Entity entity) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (!wasLeftPressed) {
                PlayerActionBehavior action = (PlayerActionBehavior) entity.getActionBehavior();
                action.setFiring(true);
                wasLeftPressed = true;
            }
        } else {
            wasLeftPressed = false;
        }
    }

    private void processMousePosition(Entity entity) {
        Vector2 pos = entity.getBodyBehavior().getPosition();
        if (game.getConfig().isTouchScreen()) {
            Vector3 screenCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f);
            Vector3 gameCoords = entity.getGame().getWorld().getCamera().unproject(screenCoords);
            pos.x = gameCoords.x;
            pos.y = gameCoords.y;
        } else {
            pos.x += Gdx.input.getDeltaX();
            pos.y -= Gdx.input.getDeltaY();
        }

        Rectangle playField = game.getWorld().PlayField;

        if (pos.x < playField.getX()) {
            pos.x = playField.getX();
        } else if (pos.x > playField.getX() + playField.getWidth()) {
            pos.x = playField.getX() + playField.getWidth();
        }

        if (pos.y < playField.getY()) {
            pos.y = playField.getY();
        } else if (pos.y > playField.getY() + playField.getHeight()) {
            pos.y = playField.getY() + playField.getHeight();
        }

        entity.getBodyBehavior().setPosition(pos);
    }
}
