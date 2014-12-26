package lostsoulsquasher.actors.lostsoul;

import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.body.BodyBehavior;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;

public class LostSoulPositionObserver {
    private boolean wasInPlayfield = false;

    public LostSoulPositionObserver(LostSoul entity) {
        entity.getBodyBehavior().positionChanged.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                handlePositionChanged((LostSoul) ((BodyBehavior) sender).getEntity());
            }
        });
    }

    public void handlePositionChanged(LostSoul entity) {
        Vector2 pos = entity.getBodyBehavior().getPosition();
        if (!entity.getGame().getWorld().PlayField.contains(pos)) {
            if (wasInPlayfield) {
                entity.getGame().getAudio().playSound(entity.getGame().getContent().skullEscapeSound);
                entity.getGame().getWorld().addLives(-1);
                entity.setExpired(true);
            }
        } else {
            wasInPlayfield = true;
        }
    }
}
