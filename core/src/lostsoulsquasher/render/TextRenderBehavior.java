package lostsoulsquasher.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Entity;


public class TextRenderBehavior extends RenderBehavior {
    public String text;
    public BitmapFont font;
    public Color color;

    public TextRenderBehavior(BitmapFont font, String text) {
        this.text = text;
        this.font = font;
        this.color = Color.WHITE.cpy();
    }

    @Override
    public void run(float gameTime, Entity entity) {
        SpriteBatch spriteBatch = entity.getGame().getSpriteBatch();
        Color oldColor = font.getColor();
        font.setColor(color);
        font.draw(spriteBatch, text, entity.getBodyBehavior().getPosition().x, entity.getBodyBehavior().getPosition().y);
        font.setColor(oldColor);
    }

    @Override
    public Vector2 getSize() {
        BitmapFont.TextBounds bounds = font.getBounds(text);
        return new Vector2(bounds.width, bounds.height);
    }
}
