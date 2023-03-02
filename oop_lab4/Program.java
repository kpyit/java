package oop_lab4;

import oop_lab4.field.battlefield;
import oop_lab4.human_classes.*;


import java.awt.Point;

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
  
/************************************************************* 
*/


public class Program { 
 

    public static void main(String[] args) {
          
        ArrayList<Human> party1 = new ArrayList<>();
        ArrayList<Human> party2 = new ArrayList<>();

        // В первом только крестьянин, разбойник, снайпер или колдун.
        RandomFactoryHuman factoryType1 = new RandomFactoryHuman(new humanFactory[]{
            // new Peasant(),
            new Rogue(),
            new Rogue(),
            new Hawkeye(),            
            new Warlord(),
            new Warlord(),
            new Arbalester(),
            new Cleric(),
            new Warlock()

            });

        // Во втором крестьянин, копейщик, арбалетчик, монах.
        RandomFactoryHuman factoryType2 = new RandomFactoryHuman(new humanFactory[]{
            // new Peasant(),
            new Warlord(),
            new Warlord(),
            new Arbalester(),
            new Arbalester(),
            new Cleric(),
            new Rogue(),
            });
 
        for(int i=0; i<9; i++)
            party1.add(factoryType1.create());
            
        
        for(int i=0; i<9; i++)
            party2.add(factoryType2.create()); 
     

        int rows = 12;
        int columns = 40;        
        battlefield field = new battlefield(12,40);

        // field.printFieldPlay();
        // добавляем группу игроков наших и врагов по умолчанию 1 группа наша

        field.addGroup(party1);
        field.addGroup(party2); 

        field.initPositionsGroup();

        //field.print_ranges();//расстояния между игроками
        //field.print_pos_heroes();//позиции hero

        //field.printFieldVGroups();
         
        // 1 шаг героя
        // field.stepNextHero(0);

        int i=0;
        int winGroup;
        int counter = 500;
        do
        {
            // System.out.printf("ROUND %d %n",i++); 

            // for(int j=0; j<party2.size(); j++)
            //      System.out.println(party2.get(j).getInfo());

            winGroup = field.round();
            counter--;
        } while(winGroup == -1 && counter != 0);

        System.out.printf("ГРУППА %d ВЫЙГРАЛА %n",winGroup);
        
        /* проблемы пытается идти к длижайшему но пути нет и стоит 
         * 
         * арбалетчики почему то не идут вперед после 1 врага или еще чего
         */


    }
}
/*
 * >>>  
▒▒▒▒▓▓▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▓▒▒▒▒▒H▒▒▒▒▒▒▒▓▓▒▒
▓▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒HHH▒▒▒▓▒▒▒▒▒▒
▒▒▒▒▒▒▒▓▒▓▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▓▒▒H▒▒▒▒▒▒▒▒▒▒
▒▒▒▒▒▒▒▓▒▒▒▒▒▒▓▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▓▒▒▒▒
▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
▓▒▒▒▓▓▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▓▒▒
▒▒▒▒▒▒▓▒▒▒▒▒▒▓▒▓▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
▒▒▒▒▒▒▓▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▓▒▒▓▒▒▒▒
▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒
 */









 