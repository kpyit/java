package oop_lab2;

/**
 * разбойник
 */
class Rogue extends Human {

    public Rogue(String name) {
        super(100, 150, 3, 25,name);
        super.mana = daggerMastery(super.mana);
        super.className = "Rogue";
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
        int defstrike = super.damage;
        if (Math.random() < 0.1)
            defstrike += 16;

        int newHp = ((human.getHp() - defstrike) < 0) ? 0 : (human.getHp() - defstrike);

        human.setHp(newHp);
    }
}
