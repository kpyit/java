package oop_lab4.human_classes;

import static oop_lab4.constants.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import oop_lab4.Names;
import oop_lab4.interfaceGame;
import oop_lab4.humanFactory;

/**
 * Базовый класс * не работает абстрация в подмодуле внешними интерфесами мое
 * // class Human implements interfaceGame,humanFactory{
 */

public class Human implements interfaceGame, humanFactory, Comparable<Human> {

    public String name; 

    public Point pos;
    public int id;
    public int group;
    public int idHumanAttack;

    public double range = 1.6;

    public char Symbol = 'H';
    public String className = "Human";
    public ArrayList<Human> enamies = new ArrayList<>();
    public int hp;
    public int speed;

    static Random rand = new Random();

    protected int onrush;
    protected int mana;
    protected int maxMana;
    
    protected int maxHp;

    protected int armor;
    protected double damage;
    protected double damageMax;
    

    protected boolean fLogistics;
    protected int nShells;
    protected boolean fMagic;

    protected int lvl;/* для растчета автоуровня */

    
    
    protected Human(int onrush, int setSpeed, int HP, int MP, int armor,
    double damage,
    double damageMaximum,
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
     * @see oop_lab4.interfaceGame#getInfo()
     */
    public String getInfo() {
        return String.format("%20s  %15s -  onrush: %2d  hp: %2d  armor: %2d  speed: %2d  damage: %2.1f - %2.1f", 
            this.name, this.className,this.onrush, this.hp, this.armor,  this.speed, this.damage, this.damageMax);
    }

    /*
     * метод определяющий логику персонажа
     */
    public int getNextAction(ArrayList<Human> allHumans, double matrixRange[][]) {
        // можно сюда сделать внутренний список врагов
        this.enamies = new ArrayList<>();

        double range2enamy;
        double minDistance = 1000;
        int currentIdEnamy = -1;

        // поиск ближайшего врага
        for (int idHuman = 0; idHuman < allHumans.size(); idHuman++) {
            if ((this.group != allHumans.get(idHuman).group) && (allHumans.get(idHuman).hp > 0)) {
                range2enamy = ((this.id > idHuman) ? matrixRange[idHuman][this.id] : matrixRange[this.id][idHuman]);

                // System.out.printf("get range2enamy %3.1f \n",range2enamy);//get range2enamy
                // 32,1

                if (range2enamy < minDistance) {
                    currentIdEnamy = idHuman;
                    minDistance = range2enamy;
                }
                this.enamies.add(allHumans.get(idHuman));
            }
        }

        // есть вообще есть противники и средства
        if (this.enamies.size() == 0) 
        {
            return NOTHFING; 
        } 
        // ВЫБРАННЫЙ ДЛЯ АТАКИ ПЕРСОНАЖ
        this.idHumanAttack = currentIdEnamy;

        // System.out.printf("minDistance: %3.1f range: %3.1f \n",minDistance,
        // this.range);

        if (this.range > minDistance) {
            return ATTACK_ENAMY;
        } else {
            return GO_TO_RANGE;// идем на дистанцию атаки посокльку
        }
        /* 
         * return GO_TO;//просто ходим ищем врагов которых пока не видно
         */
    }

    public int damage(Human enamy, double matrixRange[][]) { 
        // стандартный урон оружия ближнего боя
        double gauss = rand.nextGaussian();
        double DamageRnd = this.damage + (this.damageMax - this.damage) * (1.0 + gauss) * 0.5;
        int calcDamage = ((int) DamageRnd - enamy.armor > 0) ? ((int) DamageRnd - enamy.armor) : 1;
        // Если добиваем
        enamy.hp = (calcDamage > enamy.hp) ? 0 : enamy.hp - calcDamage;
        /*
        * System.out.printf("damage %3.1f damageMax %3.1f %n",damage,damageMax);
        * System.out.printf("урон по гауссу:  %3.1f  gauss: %3.1f%n", damageRnd,
        * gauss);
        * System.out.printf("нанесено урона: %d   armor= %d  %n" , calcDamage,
        * enamy.armor);
        */

        // рачет дальнобойного оружия
        // double gauss = rand.nextGaussian();
        // double range2Enamy = (this.id > this.idHumanAttack) ? matrixRange[idHumanAttack][this.id]
        //         : matrixRange[this.id][idHumanAttack];
        // double damageRnd = this.damage + (this.damageMax - this.damage) * (1.0 + gauss) * 0.5;
        // double damageByRange = damageRnd * (1 - 0.5 * range2Enamy / this.range);// макс дальность половина урона

        // int calcDamage = ((int) damageByRange - enamy.armor > 0) ? ((int) damageByRange - enamy.armor) : 1;
        // // Если добили
        // enamy.hp = (calcDamage > enamy.hp) ? 0 : enamy.hp - calcDamage; 
        /*
         * System.out.printf("damage %3.1f damageMax %3.1f %n",damage,damageMax);
         * System.out.printf("урон по гауссу:  %3.1f  gauss: %3.1f%n", damageRnd, gauss);
         * System.out.println("расчитан урон с поправкой на расстояние: " + damageByRange);
         * System.out.printf("нанесено урона: %d   armor= %d  %n" , calcDamage, enamy.armor);
         */
        return calcDamage;
    }

    /*
     * @see oop_lab4.interfaceGame#step()
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