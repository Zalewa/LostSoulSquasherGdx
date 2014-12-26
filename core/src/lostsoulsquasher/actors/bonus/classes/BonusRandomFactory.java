package lostsoulsquasher.actors.bonus.classes;

import java.util.Random;

public class BonusRandomFactory {
    private static class BonusClassItem {
        public BonusClass klass;
        public float minWeight;
        public float maxWeight;
    }

    private static Random random = null;
    private static BonusClassItem[] _bonuses = null;

    public static BonusClass PickRandomBonus() {
        if (random == null) {
            random = new Random();
        }
        float bonusWeight = random.nextFloat() * getBonuses()[getBonuses().length - 1].maxWeight;
        for (BonusClassItem item : getBonuses()) {
            if (bonusWeight >= item.minWeight && bonusWeight <= item.maxWeight) {
                return item.klass;
            }
        }
        return null;
    }

    private static BonusClassItem[] getBonuses() {
        if (_bonuses == null) {
            BonusClass[] klasses = new BonusClass[]{
                    new BonusAtomBomb(),
                    new BonusOneUp(),
                    new BonusFiveUp(),
                    new BonusDifficultyDecrease(),
                    new BonusEnemySlowDown()
            };
            BonusClassItem[] items = new BonusClassItem[klasses.length];
            float weight = 0.0f;
            for (int i = 0; i < klasses.length; ++i) {
                items[i] = new BonusClassItem();
                items[i].klass = klasses[i];
                items[i].minWeight = weight;
                items[i].maxWeight = weight + klasses[i].getWeight();
                weight += klasses[i].getWeight();
            }
            _bonuses = items;
        }
        return _bonuses;
    }
}
