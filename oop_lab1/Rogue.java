package oop_lab1;

/**
 * разбойник
 */
class Rogue extends Human {

    public Rogue() {
        super(100, 150, 3, 25);
        super.mana = daggerMastery(super.mana);
    }

    /**
     * Владение Броней
     * 
     * @param defArmor
     * @return
     */
    private int daggerMastery(int defArmor) {
        return defArmor + 10;
    }

    /**
     * Критический урон
     * 
     * @param human
     */
    public void criticalDamage(Human human) {
        int defstrike = super.damage;
        if (Math.random() < 0.1)
            defstrike += 16;

        int newHp = ((human.getHp() - defstrike) < 0) ? 0 : (human.getHp() - defstrike);

        human.setHp(newHp);
    }
}
