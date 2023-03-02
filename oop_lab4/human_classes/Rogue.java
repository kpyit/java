package oop_lab4.human_classes;

import static oop_lab4.constants.*;

import java.util.ArrayList;
/**
 * разбойник
 */
public class Rogue extends Fighter {

    public Rogue(String name) {
        super(8,6, 10, 0, 2,
        4,7,
        0,
        false,
        false,
        name); 

        this.className = "Rogue"; 
        this.Symbol = 'R';
    }

    public Rogue()
    {
        this("");
    }

    @Override
    public Human create() {
        return new Rogue();
    }

    @Override
    public String getInfo() { 
        return super.getInfo();
    }
    
    @Override
    public void step() {
        // TODO Auto-generated method stub
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
        int defstrike = (int)super.damage;
        if (Math.random() < 0.1)
            defstrike += 16;

        int newHp = ((human.getHp() - defstrike) < 0) ? 0 : (human.getHp() - defstrike);

        human.setHp(newHp);
    }
}
