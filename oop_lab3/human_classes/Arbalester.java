package oop_lab3.human_classes;

/**
 * арбалетчик
 */
public class Arbalester extends Fighter {

    public Arbalester(String name) {
        super(6, 4,
        10, 0, 3,
        2, 3,
        16,
        false, false,
        name);  
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