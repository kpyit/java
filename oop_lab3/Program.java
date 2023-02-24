package oop_lab3;

import oop_lab3.human_classes.*;

import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

import java.lang.Class;

/*  
Убедиться что у вас все поля описанны. Создать в основной программе два списка.
Положить в них по 10 случайных персонажей.
В первом только крестьянин, разбойник, снайпер или колдун.
Во втором крестьянин, копейщик, арбалетчик, монах.
Вывести в консоль информацию обо всех персонажах не зависимо от списка, 
очередь должна определяться скоростью каждого персонажа.
  

имя         Крестьянин  Разбойник	Снайпер	    Колдун  копейщик    арбалетчик	монах
атака		1           8           12          17      4           6           12
защита		1           3           10          12      5           3           7
выстрелы	0           0           32          0       0           16          0
урон		1           2-4         8-10        -5      1-3         2-3         -4
здоровье	1           10          15          30      10          10          30
скорость	3           6           9           9       4           4           5
доставка	1           0           0           0       0           0           0
магия		0           0           0           1       0           0           1
  
 
координаты X и Y


1 42 00 - компаратор на лету
*/
public class Program { 
 

    public static void main(String[] args) {
          
        ArrayList<Human> party1 = new ArrayList<>();
        ArrayList<Human> party2 = new ArrayList<>();

        // В первом только крестьянин, разбойник, снайпер или колдун.
        RandomFactoryHuman factoryType1 = new RandomFactoryHuman(new humanFactory[]{
            new Peasant(),
            new Rogue(),
            new Hawkeye(),            
            new Warlock(),
            });

        // Во втором крестьянин, копейщик, арбалетчик, монах.
        RandomFactoryHuman factoryType2 = new RandomFactoryHuman(new humanFactory[]{
            new Peasant(),
            new Warlord(),
            new Arbalester(),
            new Cleric() 
            });


        for(int i=0; i<10; i++)
        {
            
            party1.add(factoryType1.create());
            party2.add(factoryType2.create());
        } 
 


        party1.sort(null);
        party2.sort(null);

        
        System.out.println();       
        for(int i=0; i<10; i++)
            System.out.println(party1.get(i).getInfo());            
        
        System.out.println(); 

        for(int i=0; i<10; i++)
            System.out.println(party2.get(i).getInfo());            
        
 
    }
}
/*
 * >>>  
    Tyrion Lannister          Hawkeye -  onrush: 12  hp: 15  armor: 10  speed:  9  damage:    8 - 10
         Sansa Stark          Hawkeye -  onrush: 12  hp: 15  armor: 10  speed:  9  damage:    8 - 10
       Septa Mordane          Warlock -  onrush: 17  hp: 30  armor: 12  speed:  9  damage:   -5 - -5
       Petyr Baelish          Hawkeye -  onrush: 12  hp: 15  armor: 10  speed:  9  damage:    8 - 10
         Syrio Forel          Hawkeye -  onrush: 12  hp: 15  armor: 10  speed:  9  damage:    8 - 10
               Qotho          Warlock -  onrush: 17  hp: 30  armor: 12  speed:  9  damage:   -5 - -5
      Sandor Clegane          Warlock -  onrush: 17  hp: 30  armor: 12  speed:  9  damage:   -5 - -5
         Syrio Forel            Rogue -  onrush:  8  hp: 10  armor:  3  speed:  6  damage:    2 -  4
          Spice King          Peasant -  onrush:  1  hp:  1  armor:  1  speed:  3  damage:    1 -  1
               Qotho          Peasant -  onrush:  1  hp:  1  armor:  1  speed:  3  damage:    1 -  1

                 Pyp           Cleric -  onrush: 12  hp: 30  armor:  7  speed:  5  damage:   -4 - -4
      Sandor Clegane           Cleric -  onrush: 12  hp: 30  armor:  7  speed:  5  damage:   -4 - -4
     Renly Baratheon           Cleric -  onrush: 12  hp: 30  armor:  7  speed:  5  damage:   -4 - -4
      Sandor Clegane       Arbalester -  onrush:  6  hp: 10  armor:  3  speed:  4  damage:    2 -  3
       Septa Mordane          Warlord -  onrush:  4  hp: 10  armor:  5  speed:  4  damage:    1 -  3
       Septa Mordane          Warlord -  onrush:  4  hp: 10  armor:  5  speed:  4  damage:    1 -  3
          Spice King          Warlord -  onrush:  4  hp: 10  armor:  5  speed:  4  damage:    1 -  3
      Sandor Clegane          Warlord -  onrush:  4  hp: 10  armor:  5  speed:  4  damage:    1 -  3
       Septa Mordane          Warlord -  onrush:  4  hp: 10  armor:  5  speed:  4  damage:    1 -  3
        Rickon Stark          Peasant -  onrush:  1  hp:  1  armor:  1  speed:  3  damage:    1 -  1
 */









 