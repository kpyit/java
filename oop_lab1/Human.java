package oop_lab1;

/**
 * Базовый класс
 */
abstract class Human{
 

    protected String name;//доступны наследникам
    protected int mana;
    protected int hp;
    protected int armor;
    protected int damage;

    protected Human(int addHP, int addMP,int addArmor ,int addDamage){
        this.hp = 100+addHP;
        this.mana = 100+addMP;
        this.armor += addArmor;
        this.damage += addDamage; 
    }

    public String getInfo() {
        return String.format("hp %d  Mana: %d  armor: %d",this.hp, this.mana, this.armor);
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

