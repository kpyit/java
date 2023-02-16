package oop_lab1;

/**
 * арбалетчик
 */
class Arbalester extends Fighter {

    public Arbalester() {
        super(100, 150, 13, 10);
        super.damage += crossbowMastery(super.damage);
        super.range += increaseRange(super.range);
        super.armor += lightArmorMastery(super.armor);
    }
 
    /**
     * Увеличивает дальность стрельбы из арбалета на 400
     * 
     * @param defRange
     * @return
     */
    private int increaseRange(int defRange) {
        return defRange+400;
    }
 
    /**
     * Владение Арбалетом
     * 
     * @param defDamage
     * @return
     */
    private int crossbowMastery(int defDamage) {
        return defDamage + 15;
    }

    /**
     * @param defArmor
     * @return
     */
    private int lightArmorMastery(int defArmor) {
        return defArmor + 16;
    }
}