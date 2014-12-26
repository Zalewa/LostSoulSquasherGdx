package lostsoulsquasher.factormodifier;

import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;

public class FactorModifierBehavior implements Behavior {
    private float countdown = 0.0f;
    private boolean started = false;
    private float time;
    private float factor = 1.0f;

    public FactorModifierBehavior(float factor, float countdown) {
        this.time = countdown;
        this.factor = factor;
    }

    @Override
    public void run(float gameTime, Entity entity) {
        if (!started) {
            countdown = time;
            started = true;
        }
        countdown -= (float) gameTime;
        if (countdown < 0.0f) {
            entity.setExpired(true);
        }
    }

    public float getFactor() {
        return factor;
    }
}
