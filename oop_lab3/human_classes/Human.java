package oop_lab3.human_classes;

import oop_lab3.Names;
import oop_lab3.interfaceGame;
import oop_lab3.humanFactory;

/**
 * Базовый класс
 * не работает абстрация в подмодуле внешними интерфесами
 * // abstract class Human implements interfaceGame,humanFactory{
 */

public class Human implements interfaceGame, humanFactory, Comparable<Human> {

    protected String name;// доступны наследникам

    protected int X;
    protected int Y;

    protected int onrush;

    protected int mana;
    protected int maxMana;
    protected int hp;
    protected int maxHp;

    protected int armor;
    protected int damage;
    protected int damageMax;
    protected int speed;

    protected boolean fLogistics;
    protected int nShells;
    protected boolean fMagic;

    protected int lvl;/* для растчета автоуровня */

    protected String className = "Human";

    protected Human(int onrush, int setSpeed, int HP, int MP, int armor,
            int damage,
            int damageMaximum,
            int numberShells,
            Boolean logistics,
            Boolean magic,
            String chName) {
        
        this.onrush = onrush;
        this.hp = HP;
        this.mana = MP;
        this.armor = armor;
        this.damage = damage;
        this.damageMax = damageMaximum;

        this.maxHp = this.hp;
        this.maxMana = this.mana;

        this.speed = setSpeed;

        this.fLogistics = logistics;
        this.nShells = numberShells;
        this.fMagic = magic;

        name = chName.isEmpty() ? Names.getName() : chName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see oop_lab2.interfaceGame#getInfo()
     */
    public String getInfo() {
        return String.format("%20s  %15s -  onrush: %4d hp: %4d armor: %4d speed: %4d damage: %4d - %2d", 
        this.name, this.className,this.onrush, this.hp, this.armor,  this.speed, this.damage, this.damageMax);
    }

    /*
     * (non-Javadoc)
     * 
     * @see oop_lab3.interfaceGame#step()
     */
    @Override
    public void step() {
        // пока ничего не сделать
    }

    @Override
    public Human create() {
        return new Human(1, 3,
                0, 0, 0,
                0, 0,
                0,
                false,
                false,
                "");
    }

    /*
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Human o) {
        /*
         * if (this.speed > o.getSpeed())
         * return 1;
         * else if (this.speed < o.getSpeed())
         * return -1;
         * else
         * return 0;
         */
        // или
        return Integer.compare(this.onrush, o.getOnrush());// хреновый ход вызвать еще 1 обьект

    }
    public int getOnrush() {
        return onrush;
    }
    public int getSpeed() {
        return speed;
    }

    public int getMana() {
        return mana;
    }

    public int getHp() {
        return hp;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}