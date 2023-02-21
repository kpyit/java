package oop_lab2;

/**
 * арбалетчик
 */
class Arbalester extends Fighter {

    public Arbalester(String name) {
        super(100, 150, 10, 5,name); 
        super.damage += crossbowMastery(super.damage);
        super.range += increaseRange(super.range);
        super.armor += lightArmorMastery(super.armor);
        this.className = "Arbalester";
    }

    public Arbalester()
    {
        this("");
    }

    @Override
    public Human create() {
        return new Arbalester();
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