package oop_lab2;
import java.util.List;

/**
 * МОНАХ продвинутый класс магов
 */ 
class Cleric extends Human {
  
    public Cleric(int addHP, int addMP,int addArmor ,int addDamage, String name)
    {
        super(100+addHP,550+addMP,5+addArmor, 10+addDamage, name); 
        super.className = "Cleric";
    }
        
    public Cleric(String name)
    {
        this(0,0,0,0,name);
    } 

    public Cleric()
    {
        this("");         
    } 

    
    @Override
    public Human create() {
        // TODO Auto-generated method stub
        return new Cleric();
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

