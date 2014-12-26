package lostsoulsquasher.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class AnimationFrame {
    private Texture texture;
    private Vector2 origin = Vector2.Zero;

    public AnimationFrame(Texture texture) {
        this.texture = texture;
    }

    public static AnimationFrame mkCentered(Texture texture) {
        AnimationFrame result = new AnimationFrame(texture);
        result.setOrigin(new Vector2(texture.getWidth() / 2, texture.getHeight() / 2));
        return result;
    }

    public static AnimationFrame[] mkCentered(Texture[] textures) {
        AnimationFrame[] result = new AnimationFrame[textures.length];
        for (int i = 0; i < textures.length; ++i) {
            result[i] = mkCentered(textures[i]);
        }
        return result;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }
}
