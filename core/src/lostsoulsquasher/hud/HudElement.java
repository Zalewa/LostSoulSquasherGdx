package lostsoulsquasher.hud;

import lostsoulsquasher.Entity;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.body.PlaneBodyBehavior;
import lostsoulsquasher.exceptions.InvalidOperationException;

import java.util.ArrayList;
import java.util.List;

// libGDX has Scene2D, but this is a port from XNA, so let's do it 1-to-1.
public class HudElement extends Entity {
    private HudElement parent = null;
    private List<HudElement> children = new ArrayList<HudElement>();
    private boolean visible = true;

    public HudElement(LostSoulGame game) {
        super(game);
        bodyBehavior = new PlaneBodyBehavior(this);
    }

    public void addChild(HudElement element) {
        if (element == this) {
            throw new InvalidOperationException("HudElement cannot be its own child");
        }
        if (element.parent != null) {
            element.parent.removeChild(element);
        }
        element.parent = this;
        children.add(element);
    }

    public void removeChild(HudElement element) {
        children.remove(element);
    }

    @Override
    public void update(float elapsedTime) {
        if (visible) {
            super.update(elapsedTime);
            for (HudElement child : children) {
                child.update(elapsedTime);
            }
        }
    }

    @Override
    public void draw(float elapsedTime) {
        if (visible) {
            super.draw(elapsedTime);
            for (HudElement child : children) {
                child.draw(elapsedTime);
            }
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<HudElement> getChildren() {
        return children;
    }

    public HudElement getParent() {
        return parent;
    }
}
