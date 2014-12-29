package lostsoulsquasher.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;

public class SpriteRenderBehavior extends RenderBehavior {
    private LostSoulGame game;
    private Color color;
    private Texture texture;
    private Vector2 scale;
    private Vector2 textureSize = new Vector2(0.0f, 0.0f);

    public SpriteRenderBehavior(LostSoulGame game, Texture texture) {
        this.game = game;
        this.color = Color.WHITE.cpy();
        this.texture = texture;
        this.scale = new Vector2(1.0f, 1.0f);
    }


    @Override
    public void run(float elapsedTime, Entity entity) {
        Vector2 pos = entity.getBodyBehavior().getPosition();
        Color oldColor = game.getSpriteBatch().getColor();
        game.getSpriteBatch().setColor(color);
        game.getSpriteBatch().draw(texture, pos.x - getOrigin().x, pos.y - getOrigin().y, getOrigin().x, getOrigin().y,
                getSize().x, getSize().y, getScale().x, getScale().y,
                0.0f, 0, 0, (int)getSize().x, (int)getSize().y,
                false, false);
        game.getSpriteBatch().setColor(oldColor);
    }

    @Override
    public Vector2 getSize() {
        textureSize.x = texture.getWidth();
        textureSize.y = texture.getHeight();
        return textureSize;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }
}
