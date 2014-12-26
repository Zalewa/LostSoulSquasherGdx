package lostsoulsquasher.actors.bonus.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulWorld;
import lostsoulsquasher.render.RenderBehavior;
import lostsoulsquasher.render.SpriteRenderBehavior;

public abstract class BonusClass {
    static RenderBehavior mkBonusRender(Entity entity, Texture texture) {
        SpriteRenderBehavior render = new SpriteRenderBehavior(entity.getGame(), texture);
        render.setScale(new Vector2(0.5f, 0.5f));
        return render;
    }

    public int getScore() {
        return 10;
    }

    public abstract void activate(LostSoulWorld world);
    public abstract RenderBehavior mkRender(Entity entity);
    public abstract float getWeight();
}
