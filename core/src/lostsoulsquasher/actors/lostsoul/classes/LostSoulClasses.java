package lostsoulsquasher.actors.lostsoul.classes;

import lostsoulsquasher.LostSoulGame;

public class LostSoulClasses {
    private static LostSoulSlow slow;
    private static LostSoulAverage average;
    private static LostSoulFast fast;
    private static LostSoulUltraFast ultraFast;
    private static LostSoulBig big;

    public static void loadContent(LostSoulGame game)  {
        slow = new LostSoulSlow();
        average = new LostSoulAverage();
        fast = new LostSoulFast();
        ultraFast = new LostSoulUltraFast();
        big = new LostSoulBig(game);
    }

    public static LostSoulSlow getSlow() {
        return slow;
    }

    public static LostSoulAverage getAverage() {
        return average;
    }

    public static LostSoulFast getFast() {
        return fast;
    }

    public static LostSoulUltraFast getUltraFast() {
        return ultraFast;
    }

    public static LostSoulBig getBig() {
        return big;
    }
}
