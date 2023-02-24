package oop_lab4;

import oop_lab4.human_classes.*;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

import java.util.ArrayList;

import java.lang.Class;


/*  

    Создать класс с описанием координат, x и y.
    Добавить в абстрактный класс порле с координатами и пробросить его инициализацию до конструкторов персонажей. Farmer farmer = new Farmer(getName(), x, y);
    Реализовать метод step() лучников.
    
    3.1 Если жизнь равна нулю или стрел нет, завершить оьработку.
    
    3.2 Поиск среди противников наиболее приближённого. 
    
    3.3 Нанести среднее повреждение найденному противнику.
    
    3.4 Найти среди своих крестьянина.
    
    3.5 Если найден завершить метод иначе уменьшить запас стрел на одну.


Предлагаю изьять координаты в общий массив для построения матрицы дальности
и доступности по уже готовому алгоритму

Пункты 1, 2 и 3.2 рещены на семинаре ими можно воспользоваться!


имя         Крестьянин	Разбойник	Снайпер	    Колдун	копейщик	арбалетчик	монах
атака		1			8			12			17		4			6			12
защита		1			3			10			12		5			3			7
выстрелы	0			0			32			0		0			16			0
урон		1			2-4			8-10		-5		1-3			2-3			-4
здоровье	1			10			15			30		10			10			30
скорость	3			6			9			9		4			4			5
доставка	1			0			0			0		0			0			0
магия		0			0			0			1		0			0			1
  
 
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
              Qyburn          Peasant -  onrush:    1 hp:    1 armor:    1 speed:    3 damage:    1 -  1
       Samwell Tarly            Rogue -  onrush:    8 hp:   10 armor:    3 speed:    6 damage:    2 -  4
   Rhaegar Targaryen            Rogue -  onrush:    8 hp:   10 armor:    3 speed:    6 damage:    2 -  4
               Qotho            Rogue -  onrush:    8 hp:   10 armor:    3 speed:    6 damage:    2 -  4
       Petyr Baelish            Rogue -  onrush:    8 hp:   10 armor:    3 speed:    6 damage:    2 -  4
    Tommen Baratheon            Rogue -  onrush:    8 hp:   10 armor:    3 speed:    6 damage:    2 -  4
               Qotho          Warlock -  onrush:   17 hp:   30 armor:   12 speed:    9 damage:   -5 - -5
    Tommen Baratheon          Warlock -  onrush:   17 hp:   30 armor:   12 speed:    9 damage:   -5 - -5
   Rhaegar Targaryen          Warlock -  onrush:   17 hp:   30 armor:   12 speed:    9 damage:   -5 - -5
                 Pyp          Warlock -  onrush:   17 hp:   30 armor:   12 speed:    9 damage:   -5 - -5

         Syrio Forel          Peasant -  onrush:    1 hp:    1 armor:    1 speed:    3 damage:    1 -  1
     Renly Baratheon          Warlord -  onrush:    4 hp:   10 armor:    5 speed:    4 damage:    1 -  3
     Renly Baratheon          Warlord -  onrush:    4 hp:   10 armor:    5 speed:    4 damage:    1 -  3
       Theon Greyjoy       Arbalester -  onrush:    6 hp:   10 armor:    3 speed:    4 damage:    2 -  3
               Qotho       Arbalester -  onrush:    6 hp:   10 armor:    3 speed:    4 damage:    2 -  3
         Sansa Stark       Arbalester -  onrush:    6 hp:   10 armor:    3 speed:    4 damage:    2 -  3
      Sandor Clegane           Cleric -  onrush:   12 hp:   30 armor:    7 speed:    5 damage:   -4 - -4
               Qotho           Cleric -  onrush:   12 hp:   30 armor:    7 speed:    5 damage:   -4 - -4
       Rodrik Cassel           Cleric -  onrush:   12 hp:   30 armor:    7 speed:    5 damage:   -4 - -4
   Rhaegar Targaryen           Cleric -  onrush:   12 hp:   30 armor:    7 speed:    5 damage:   -4 - -4
 */









 