package lostsoulsquasher.hud;

import com.badlogic.gdx.graphics.Color;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.body.RenderDependentBodyBehavior;
import lostsoulsquasher.render.TextRenderBehavior;

public class HudElementText extends HudElement {
    public HudElementText(LostSoulGame game) {
        this(game, "");
    }

    public HudElementText(LostSoulGame game, String text) {
        super(game);
        bodyBehavior = new RenderDependentBodyBehavior(this);
        renderBehavior = new TextRenderBehavior(game.getContent().font, text);
    }

    public String getText() {
        return getRender().text;
    }

    public void setText(String text) {
        getRender().text = text;
    }

    public Color getColor() {
        return getRender().color;
    }

    public void setColor(Color color) {
        getRender().color = color;
    }

    private TextRenderBehavior getRender()  {
        return (TextRenderBehavior)renderBehavior;
    }

    @Override
    public String toString()  {
        return super.toString() + " (" + getText() + ")";
    }
}
