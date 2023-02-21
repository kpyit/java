package oop_lab2;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

import java.lang.Class;
import java.lang.reflect.*;


// Доработать дерево классов так, чтобы экземпляры классов создавались конструктором 
// с именем в параметре а остальные параметры инициализировали сами в зависимости от персонажа. 
// Добавить файл интерфейса в котором описать два метода, void step(); и String getInfo(); +
// Добавить реализацию интерфейса в ваш коренной абстрактный класс.+
//  Переопределить getInfo так, чтобы он возвращал строки:"Я крестьянин", 
// "Я снайпер" и так далее в зависимости от типа персонажа. +
// В основном классе создать список и добавить в него 10 случаных персонажей.
// Пройти по списку и вывести строку возвращаемую getInfo.
  

public class Program { 
    public static void main(String[] args) {

        Random rand = new Random();

        ArrayList<Human> listHumans = new ArrayList<>();

        Fighter fighter1 = new Fighter();        
        Cleric cleric1 = new Cleric();        

        System.out.println(fighter1.getInfo());
        System.out.println(cleric1.getInfo());
 

        RandomFactoryHuman factory = new RandomFactoryHuman(new humanFactory[]{
            new Fighter(),
            new Cleric(),
            new Cleric(),//клериков вечно не хватало
            new Arbalester(),
            new Hawkeye(),
            new Peasant(),
            new Warlord(),
            new Warlock(),
            new Wizard(),
            new Rogue()
            });

        for(int i=0; i<10; i++)
        {
            listHumans.add(factory.create());
            System.out.println(listHumans.get(i).getInfo());            
        }
 

        List<Class<? extends Human>> list1 = new ArrayList<Class<? extends Human>>();
        list1.add(Fighter.class);
        list1.add(Cleric.class); 
         
       
 
    }
}
/*
 * >>> 
     Qhorin Halfhand          Fighter - hp: 300  Mana:   150  armor:    14 damage:    33
   Rhaegar Targaryen           Cleric - hp: 200  Mana:   650  armor:     5 damage:    10
         Sansa Stark          Warlock - hp: 200  Mana:   550  armor:     5 damage:    10
    Tommen Baratheon          Warlord - hp: 300  Mana:   150  armor:    49 damage:    33
   Rhaegar Targaryen          Hawkeye - hp: 400  Mana:   310  armor:    20 damage:    53
         Syrio Forel          Peasant - hp: 200  Mana:    50  armor:     0 damage:     0
   Rhaegar Targaryen          Fighter - hp: 300  Mana:   150  armor:    14 damage:    33
       Samwell Tarly           Wizard - hp: 200  Mana:   450  armor:     5 damage:    10
    Tommen Baratheon           Cleric - hp: 200  Mana:   650  armor:     5 damage:    10
     Qhorin Halfhand          Peasant - hp: 200  Mana:    50  armor:     0 damage:     0
      Sandor Clegane           Cleric - hp: 200  Mana:   650  armor:     5 damage:    10
                 Pyp            Rogue - hp: 200  Mana:   260  armor:     3 damage:    25
 */












    /*
            Class[] cArg = new Class[2];
            cArg[0] = Double.class;
            cArg[1] = Long.class;
            Constructor ct = c.getDeclaredConstructor(cArg);
            System.out.println("Constructor = " + ct.toString()); 
    */ 


  /*  рефлексия не пошла 
        Class[] parameterType = null;
        List<Human> classes = Arrays.asList(Fighter.class, Cleric.class); 
        for (Human clazz : classes) {            
            Constructor ct = clazz.getDeclaredConstructor(parameterType);
            Object instance = clazz.getDeclaredConstructor(parameterType).newInstance() ;
        }    
        for (Class<? extends Human> hum : list1) {  
            
        //  можем извелкать конструкторы!
        System.out.println(Fighter.class.getConstructors()[0]);//public oop_lab2.Fighter()
        System.out.println(Fighter.class.getConstructors()[1]);//public oop_lab2.Fighter(java.lang.String)
        System.out.println(Fighter.class.getConstructors()[2]);//public oop_lab2.Fighter(int,int,int,int,java.lang.String)

        // Constructor constructor = clazz.getConstructors()[0];
        Constructor constructor = Fighter.class.getConstructors()[0];
        constructor.setAccessible(true);
        final Fighter instance = (Fighter)constructor.newInstance(null);

        Class[] cArg = new Class[1];
        cArg[0] = null;
        Object instance0 = list1.get(0).getDeclaredConstructor(cArg).newInstance(); 
        */