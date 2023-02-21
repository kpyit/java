package oop_lab3.human_classes;
import java.util.List;

/**
 * МОНАХ продвинутый класс магов
 */
public class Cleric extends Human {
  
    public Cleric(int onrush, int speed,
    int HP, int MP,int armor ,
    int damage, int damageMax, 
    int numberShells,
    Boolean logistics, Boolean magic,    
    String name)
    {
        super(12,5,
        30,0,7,
        -4,-4,
        0,
        false,
        true,
        name); 
        super.className = "Cleric";
    }
        
    public Cleric(String name)
    {
        this(12,5,
        30,0,7,
        -4,-4,
        0,
        false,
        true,
        name);         
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

