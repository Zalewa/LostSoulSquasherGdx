package lostsoulsquasher.actors.lostsoul;

import com.badlogic.gdx.graphics.Texture;
import lostsoulsquasher.*;
import lostsoulsquasher.render.SpriteRenderBehavior;

public class LostSoulAnimation implements Behavior {
    private Texture left;
    private Texture right;

    public LostSoulAnimation(LostSoulGame game)
    {
        left = game.getContent().skullLeft;
        right = game.getContent().skullRight;
    }

    @Override
    public void run(float elapsedTime, Entity entity)
    {
        int comparison = Float.compare(entity.getMovementBehavior().getVelocity().x, 0.0f);
        if (comparison == 0)
        {
            setTexture(entity, entity.getMovementBehavior().getVelocity().y > 0.0f ? left : right);
        }
        else if (comparison < 0)
        {
            setTexture(entity, left);
        }
        else
        {
            setTexture(entity, right);
        }
    }

    private void setTexture(Entity entity, Texture texture)
    {
        SpriteRenderBehavior render = (SpriteRenderBehavior)entity.getRenderBehavior();
        if (render.getTexture() != texture) {
            render.setTexture(texture);
            render.centerOrigin();
        }
    }
}
