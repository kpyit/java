package oop_lab1;
/**
 * Hawkeye(снайпер)
 */
class Hawkeye extends Fighter {

    public Hawkeye() {
        super(100, 150, 3, 10);
        super.mana = daggerMastery(super.mana);
        super.range += longShot(super.range);
    }

    // Владение Броней
    private int daggerMastery(int defArmor) {
        return defArmor + 10;
    }
    
    // Двойной Выстрел
    public void doubleShot(Human human) {
        
        if(super.mana >= 17)        
        {
            mana-=18;  
            int newHp = ((human.getHp()-super.damage+15) < 0)? 0:(human.getHp()-super.damage+15);
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
        return defRange+200;// Дальность стрельбы из лука +200.
    }

}