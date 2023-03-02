package oop_lab4.human_classes;
 
import static oop_lab4.constants.*;
 
import java.util.ArrayList;
import java.util.List;
/**
 * МОНАХ продвинутый класс магов
 */
public class Cleric extends Wizard {
  
    public Cleric(int onrush, int speed,
    int HP, int MP, int armor ,
    double damage, double damageMax, 
    int numberShells,
    Boolean logistics, Boolean magic,    
    String name)
    {
        super(onrush, speed,
        HP, MP, armor,
        damage, damageMax,
        numberShells,
        logistics, magic,
        name);
        this.className = "Cleric";
        this.Symbol = 'С';
    }
        
    public Cleric(String name)
    {
        this(12,5,
        20,0,3,
        3,3,
        2,
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
 
   /*
     * метод определяющий логику персонажа
     */
    public int getNextAction(ArrayList<Human> allHumans, double matrixRange[][]) {
        // можно сюда сделать внутренний список врагов
        ArrayList<Human> heroes = new ArrayList<>();

        double range2hero;
        double minDistance = 1000;
        int currentIdHero = -1;

        // поиск ближайшего врага
        for (int idHuman = 0; idHuman < allHumans.size(); idHuman++) {
            //Все в группе с поврежденным здоровьем и мертвые тоже
            if ((this.group == allHumans.get(idHuman).group) && (allHumans.get(idHuman).hp < allHumans.get(idHuman).maxHp)) {

                range2hero = ((this.id > idHuman) ? matrixRange[idHuman][this.id] : matrixRange[this.id][idHuman]);

                //Если есть мертвецы исцеляем их в 1 очередь
                if(this.nShells !=0 && allHumans.get(idHuman).hp == 0)
                {
                    this.idHumanAttack = idHuman;

                    if (this.range > range2hero) {
                        return HEALING;//в зависимости от здоровья или лечить или воскршать
                    } else {
                        return GO_TO_RANGE;// идем на дистанцию атаки посокльку
                    } 
                } else if(allHumans.get(idHuman).hp != 0)
                {
                    if (range2hero < minDistance)
                    {
                        currentIdHero = idHuman;
                        minDistance = range2hero;
                    }
                    heroes.add(allHumans.get(idHuman));
                }

            }
        }
    
        // есть вообще кого лечить
        if (heroes.size() == 0)
        {
            return NOTHFING;
        }
        // кого лечим
        this.idHumanAttack = currentIdHero;

        if (this.range > minDistance) {
            return HEALING;
        } else {
            return GO_TO_RANGE;// идем на дистанцию лечения
        }
    }


    public int damage(Human hero, double matrixRange[][]) {
        //воскрешает с половиной здоровья
        if(hero.hp == 0 && this.nShells !=0)
        {
            hero.hp = hero.maxHp/2;
            this.nShells--;
            return hero.maxHp/2;    
        }

        hero.hp = ( hero.maxHp < hero.hp+(int)this.damage) ? hero.maxHp :  hero.hp+(int)this.damage;
        /* System.out.printf("исцелено: %d" , (int)this.damage ); */
        return (int)this.damage;
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

