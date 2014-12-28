package lostsoulsquasher;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lostsoulsquasher.event.EventArgs;
import lostsoulsquasher.event.EventHandler;
import lostsoulsquasher.hud.HudElement;
import lostsoulsquasher.hud.HudElementText;
import lostsoulsquasher.render.PrimitiveRectangleRenderBehavior;

public class LostSoulWorldHud {
    private LostSoulWorld world;
    private HudElement root;
    private HudElement gameHud;
    private HudElement gameOverHud;

    private HudElement gameOverTextBackground;
    private HudElementText scoreLabel;
    private HudElementText lostSoulsLabel;
    private HudElementText difficultyLabel;
    private HudElementText helpLabel;
    private HudElementText gameOverInstructions;

    private PrimitiveRectangleRenderBehavior gameOverBackgroundRender;

    private Color textColor = Color.RED.cpy();
    private Color gameOverTextColor = Color.WHITE.cpy();

    public LostSoulWorldHud(LostSoulWorld world) {
        this.world = world;
        world.getGame().getWorld().gameOverChanged.add(new EventHandler() {
            @Override
            public void run(Object sender, EventArgs args) {
                onGameOverChangedHandler(sender, args);
            }
        });
        root = new HudElement(world.getGame());
        root.getBodyBehavior().setSize(new Vector2(world.PlayField.getWidth(), world.PlayField.getHeight()));

        setupGameHud(world);
        setupGameOverHud(world);
    }

    private void setupGameHud(LostSoulWorld world) {
        gameHud = new HudElement(world.getGame());
        gameHud.getBodyBehavior().setSize(root.getBodyBehavior().getSize().cpy());
        root.addChild(gameHud);

        final float labelY = gameHud.getBodyBehavior().getSize().y - 2.0f;

        scoreLabel = new HudElementText(world.getGame());
        scoreLabel.setColor(textColor);
        scoreLabel.getBodyBehavior().setPosition(new Vector2(0.0f, labelY));
        gameHud.addChild(scoreLabel);

        lostSoulsLabel = new HudElementText(world.getGame());
        lostSoulsLabel.setColor(textColor);
        lostSoulsLabel.getBodyBehavior().setPosition(new Vector2(200.0f, labelY));
        lostSoulsLabel.setText("Lives: 100");
        gameHud.addChild(lostSoulsLabel);

        difficultyLabel = new HudElementText(world.getGame());
        difficultyLabel.setText("Difficulty: 100%");
        difficultyLabel.setColor(textColor);
        difficultyLabel.getBodyBehavior().setPosition(new Vector2(
                lostSoulsLabel.getBodyBehavior().getPosition().x + lostSoulsLabel.getBodyBehavior().getSize().x + 15.0f, labelY));
        gameHud.addChild(difficultyLabel);

        helpLabel = new HudElementText(world.getGame());
        if (world.getGame().getConfig().isFullscreenMakesSense()) {
            helpLabel.setText("F - Fullscreen");
        }
        helpLabel.setColor(textColor);
        helpLabel.getBodyBehavior().setPosition(new Vector2(
                difficultyLabel.getBodyBehavior().getPosition().x + difficultyLabel.getBodyBehavior().getSize().x + 15.0f, labelY));
        gameHud.addChild(helpLabel);

        PrimitiveRectangleRenderBehavior gameHudRender = new PrimitiveRectangleRenderBehavior(gameHud);
        gameHudRender.color = new Color(0.0f, 0.0f, 0.0f, 0.4f);
        gameHud.setRenderBehavior(gameHudRender);

        float backgroundY = helpLabel.getBodyBehavior().getPosition().y - helpLabel.getBodyBehavior().getSize().y - 2.0f;
        float backgroundHeight = gameHud.getBodyBehavior().getSize().y - backgroundY;

        gameHud.getBodyBehavior().setPosition(new Vector2(0.0f, backgroundY));
        Vector2 gameHudSize = Vector2.Zero.cpy();
        gameHudSize.x = helpLabel.getBodyBehavior().getPosition().x + helpLabel.getBodyBehavior().getSize().x + 5.0f;
        gameHudSize.y = backgroundHeight;
        gameHud.getBodyBehavior().setSize(gameHudSize);
    }

    private void setupGameOverHud(LostSoulWorld world) {
        gameOverHud = new HudElement(world.getGame());
        gameOverHud.setVisible(false);
        gameOverHud.getBodyBehavior().setSize(root.getBodyBehavior().getSize().cpy());
        root.addChild(gameOverHud);

        setupGameOverTextBackground(world);
        setupGameOverText(world);
        setupGameOverInstructions(world);
        adjustGameOverTextBackgroundSize();
    }

    private void setupGameOverTextBackground(LostSoulWorld world) {
        gameOverTextBackground = new HudElement(world.getGame());
        Vector2 center = gameOverHud.getBodyBehavior().getSize().cpy().scl(0.5f);
        Vector2 size = new Vector2(200.0f, 200.0f);
        gameOverTextBackground.getBodyBehavior().setSize(size);
        gameOverTextBackground.getBodyBehavior().setPosition(new Vector2(
                center.x - size.x / 2.0f,
                center.y - size.y / 2.0f));
        gameOverBackgroundRender = new PrimitiveRectangleRenderBehavior(gameOverTextBackground);

        Color color = Color.BLACK.cpy();
        color.a = 0.8f;
        gameOverBackgroundRender.color = color;
        gameOverTextBackground.setRenderBehavior(gameOverBackgroundRender);
        gameOverHud.addChild(gameOverTextBackground);
    }

    private void setupGameOverText(LostSoulWorld world) {
        Vector2 center = world.PlayField.getCenter(new Vector2());
        HudElementText label = new HudElementText(world.getGame(), "Game Over");
        Vector2 position = center.sub(label.getRenderBehavior().getSize().cpy().scl(0.5f));
        position.y -= label.getRenderBehavior().getSize().y;
        label.getBodyBehavior().setPosition(position);
        label.setColor(gameOverTextColor);
        gameOverTextBackground.addChild(label);
    }

    private void setupGameOverInstructions(LostSoulWorld world) {
        Vector2 center = world.PlayField.getCenter(new Vector2());
        HudElementText label = new HudElementText(world.getGame(), "Press RMB to restart or Escape to exit.");
        Vector2 position = center.sub(label.getRenderBehavior().getSize().cpy().scl(0.5f));
        position.y += label.getRenderBehavior().getSize().y;
        label.getBodyBehavior().setPosition(position);
        label.setColor(gameOverTextColor);
        gameOverInstructions = label;
        gameOverTextBackground.addChild(label);
    }

    private void adjustGameOverTextBackgroundSize() {
        Vector2 topLeft = Vector2.Zero.cpy();
        Vector2 bottomRight = Vector2.Zero.cpy();
        for (HudElement element : gameOverTextBackground.getChildren()) {
            Rectangle candidate = element.getBodyBehavior().getBoundingRectangle();
            if (topLeft == Vector2.Zero) {
                topLeft = new Vector2(candidate.getX(), candidate.getY());
            }
            topLeft.x = Math.min(candidate.getX(), topLeft.x);
            topLeft.y = Math.min(candidate.getY(), topLeft.y);
            bottomRight.x = Math.max(candidate.getX() + candidate.getWidth(), bottomRight.x);
            bottomRight.y = Math.max(candidate.getY() + candidate.getHeight(), bottomRight.y);
        }
        float margin = 10.0f;
        Vector2 size = bottomRight.cpy().sub(topLeft);
        size.x += margin * 2.0f;
        size.y += margin * 2.0f;
        gameOverTextBackground.getBodyBehavior().setSize(size);
        Vector2 center = gameOverTextBackground.getParent().getBodyBehavior().getBoundingRectangle().getCenter(new Vector2());
        gameOverTextBackground.getBodyBehavior().setPosition(new Vector2(center.x - size.x / 2.0f, center.y - size.y / 2.0f));
    }

    public void update(float elapsedTime) {
        scoreLabel.setText("Score: " + world.getPlayer().getScore());
        lostSoulsLabel.setText("Lives: " + world.getPlayer().getHealthBehavior().getHealth());
        difficultyLabel.setText(String.format("Difficulty: %1.0f%%",
                (world.getDifficulty() / world.getMaxDifficulty()) * 100.0f));
        updateGameOverBackground();
        updateGameOverHelp();

        root.update(elapsedTime);
    }

    private void updateGameOverHelp() {
        if (!world.isGameOver() || !world.getGame().getConfig().isTouchScreen()) {
            return;
        }
        if (world.canExitGameOverByTouching()) {
            gameOverInstructions.setText("Touch to restart.");
        } else {
            gameOverInstructions.setText("");
        }
    }

    private void updateGameOverBackground() {
        if (!world.isGameOver()) {
            return;
        }
        if (world.canExitGameOverByTouching()) {
            gameOverBackgroundRender.color.r = 0.0f;
            gameOverBackgroundRender.color.g = 0.2f;
            gameOverBackgroundRender.color.b = 0.0f;
        } else {
            gameOverBackgroundRender.color.r = 0.0f;
            gameOverBackgroundRender.color.g = 0.0f;
            gameOverBackgroundRender.color.b = 0.0f;
        }
    }

    public void draw(float elapsedTime) {
        root.draw(elapsedTime);
    }

    void onGameOverChangedHandler(Object sender, EventArgs e) {
        LostSoulWorld world = (LostSoulWorld) sender;
        gameHud.setVisible(!world.isGameOver());
        gameOverHud.setVisible(world.isGameOver());
        if (world.isGameOver()) {
            scoreLabel.setColor(gameOverTextColor);
            gameOverHud.addChild(scoreLabel);
        } else {
            scoreLabel.setColor(textColor);
            gameHud.addChild(scoreLabel);
        }
    }
}
