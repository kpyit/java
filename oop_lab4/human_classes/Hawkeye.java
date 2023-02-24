package oop_lab4.human_classes;

/**
 * Hawkeye(снайпер)
 */
public class Hawkeye extends Fighter {

    public Hawkeye(String name) {
        super(12, 9, 15, 0, 10,
                8, 10,
                32,
                false, false,
                name);
        this.className = "Hawkeye";
    }

    public Hawkeye() {
        this("");
    }

    @Override
    public Human create() {
        return new Hawkeye();
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
    }

    // Владение Броней
    private int daggerMastery(int defArmor) {
        return defArmor + 10;
    }

    // Двойной Выстрел
    public void doubleShot(Human human) {

        if (super.mana >= 17) {
            mana -= 18;
            int newHp = ((human.getHp() - super.damage + 15) < 0) ? 0 : (human.getHp() - super.damage + 15);
            human.setHp(newHp);
        }
    }

    // Глаз Ястреба
    public void hawkEye() {
        if (super.range <= 400)
            super.range += 100;
    }

    // Дальнобойность
    private int longShot(int defRange) {
        return defRange + 200;// Дальность стрельбы из лука +200.
    }

}