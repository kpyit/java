package oop_lab1;

import java.util.List;

/**
 * МОНАХ продвинутый класс магов
 */ 
class Cleric extends Human {
  
    public Cleric(int addHP, int addMP,int addArmor ,int addDamage)
    {
        super(100+addHP,550+addMP,5+addArmor, 10+addDamage); 
    }
    
    public Cleric()
    {
        this(0,0,0,0); 
    } 


    /**
     * Воскрешение
     * 
     * @param human
     */
    public void Resurrection(Human human)
    {
        if(super.mana >= 17 && human.getHp() == 0  )        
        {
            super.mana-=17;
            human.setHp(human.getHp()+108);
            human.hp +=150;
        }
    } 
    /**
     * Боевое Лечение
     * 
     * @param human
     */
    public void battleHeal(Human human)
    {
        if(super.mana >= 17)        
        {
            super.mana-=17;
            //вообще надо вводить макс hp в такой ситуации
            human.setHp(human.getHp()+150);
        }
    } 

    /**
     * Групповое Лечение  
     * 
     * @param humans
     */
    public void groupHeal(List<Human> humans)
    {
        if(super.mana >= 17)        
        {
            super.mana-=27;
            for (Human hum : humans) {
                hum.setHp(hum.getHp()+108);
            }
        }
    }

}

