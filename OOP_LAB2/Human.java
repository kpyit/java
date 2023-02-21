package oop_lab2;

/**
 * Базовый класс
 */
abstract class Human  implements interfaceGame,humanFactory{
 
    protected String name;//доступны наследникам
    protected int mana;
    protected int maxMana;
    protected int hp;
    protected int maxHp;
    protected int armor;
    protected int damage;
    protected String className = "Human";

    protected Human(int addHP, int addMP,int addArmor ,int addDamage, String chName){
        this.hp = 100+addHP;
        this.mana = 100+addMP;
        this.armor += addArmor;
        this.damage += addDamage;
        
        this.maxHp = this.hp;
        this.maxMana = this.mana;

        name= chName.isEmpty()?Names.getName():chName;
    }
    
    /* (non-Javadoc)
     * @see oop_lab2.interfaceGame#getInfo()
     */
    public String getInfo(){
        return String.format("%20s  %15s - hp: %d  Mana: %5d  armor: %5d damage: %5d",this.name ,this.className , this.hp, this.mana, this.armor, this.damage);
    }

    @Override
    public void step() {
        // пока ничего не сделать
    }


   

    public int getMana(){
        return mana;
    }

    public int getHp(){
        return hp;
    }

    public void setMana(int mana){
        this.mana = mana;
    }

    public void setHp(int hp){
        this.hp = hp;
    }
}