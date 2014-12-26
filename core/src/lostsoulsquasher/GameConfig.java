package lostsoulsquasher;

public class GameConfig {
    private boolean touchScreen = false;
    private boolean fullscreenMakesSense = true;

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
}
