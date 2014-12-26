package lostsoulsquasher.animation;

import lostsoulsquasher.Behavior;
import lostsoulsquasher.Entity;
import lostsoulsquasher.render.SpriteRenderBehavior;

public class AnimationBehavior implements Behavior {
    private AnimationFrame[] frames;
    private float countdownToNextFrame = 0.0f;
    private int frameIndex = 0;
    private float interval;

    private boolean markEntityAsExpiredWhenDone = false;

    public AnimationBehavior(AnimationFrame[] frames) {
        this(frames, 1.0f);
    }

    public AnimationBehavior(AnimationFrame[] frames, float interval) {
        this.frames = frames;
        this.interval = interval;
    }


    @Override
    public void run(float elapsedTime, Entity entity) {
        countdownToNextFrame -= elapsedTime;
        while (isSkipTime()) {
            SpriteRenderBehavior render = (SpriteRenderBehavior) entity.getRenderBehavior();
            render.setTexture(frames[frameIndex].getTexture());
            render.setOrigin(frames[frameIndex].getOrigin());
            ++frameIndex;
            countdownToNextFrame += interval;
        }

        if (isDone() && markEntityAsExpiredWhenDone) {
            entity.setExpired(true);
        }
    }

    private boolean isSkipTime() {
        return !isDone() && countdownToNextFrame <= 0.0f && interval > 0.0f;
    }

    public boolean isDone() {
        return frameIndex >= frames.length && countdownToNextFrame <= 0.0f;
    }

    public boolean isMarkEntityAsExpiredWhenDone() {
        return markEntityAsExpiredWhenDone;
    }

    public void setMarkEntityAsExpiredWhenDone(boolean markEntityAsExpiredWhenDone) {
        this.markEntityAsExpiredWhenDone = markEntityAsExpiredWhenDone;
    }
}
