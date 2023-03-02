package oop_lab4.human_classes;

import static oop_lab4.constants.*;

import java.util.ArrayList;

/**
 * Hawkeye(снайпер) продвинутый стрелок
 */
public class Hawkeye extends Arbalester {

    public Hawkeye(String name) {
        super(12, 5,
         15, 0, 4,
                9, 11,
                55,
                false, false,
                name);

        this.range = 20;
        this.className = "Hawkeye";
        this.Symbol = 'H';
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
            // int newHp = ((human.getHp() - super.damage + 15) < 0) ? 0 : (human.getHp() - super.damage + 15);
            int newHp = 100;
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