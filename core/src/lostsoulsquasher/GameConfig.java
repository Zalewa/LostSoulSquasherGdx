package lostsoulsquasher;

public class GameConfig {
    private boolean touchScreen = false;
    private boolean fullscreenMakesSense = true;
    private int fontSize = 22;

    public boolean isTouchScreen() {
        return touchScreen;
    }

    public void setTouchScreen(boolean touchScreen) {
        this.touchScreen = touchScreen;
    }

    public boolean isFullscreenMakesSense() {
        return fullscreenMakesSense;
    }

    public void setFullscreenMakesSense(boolean fullscreenMakesSense) {
        this.fullscreenMakesSense = fullscreenMakesSense;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
