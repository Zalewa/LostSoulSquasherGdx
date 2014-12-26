package lostsoulsquasher.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;

public class PrimitiveRectangleRenderBehavior extends RenderBehavior {
    private static Texture pixel = null;
    private Entity entity;
    public Color color;

    public PrimitiveRectangleRenderBehavior(Entity entity) {
        if (pixel == null) {
            initPixel();
        }
        this.color = Color.BLACK.cpy();
        this.entity = entity;
    }

    private void initPixel() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void run(float gameTime, Entity entity) {
        Rectangle rect = entity.getBodyBehavior().getBoundingRectangle();
        LostSoulGame game = entity.getGame();
        Color oldColor = game.getSpriteBatch().getColor();
        game.getSpriteBatch().setColor(color);
        game.getSpriteBatch().draw(pixel, rect.x - getOrigin().x, rect.y - getOrigin().y, getOrigin().x, getOrigin().y,
                1, 1, rect.getWidth(), rect.getHeight(),
                0.0f, 0, 0, 1, 1,
                false, false);
        game.getSpriteBatch().setColor(oldColor);
    }

    @Override
    public Vector2 getSize() {
        return entity.getBodyBehavior().getSize();
    }
}
