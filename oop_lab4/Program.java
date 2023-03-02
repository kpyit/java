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

        // for(int j=0; j<party2.size(); j++)
        //   System.out.println(party2.get(j).getInfo());
         
        // 1 шаг героя
        // field.stepNextHero(0);

        int i=0;
        int winGroup;
        int counter = 500;
        do
        {
            System.out.printf("ROUND %d %n",i++); 



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
/* замес
 * >>>  
▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▓▒▒▒▓▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒
▒▒▒▒▒▒▒▒▒▒▒▓▒▓▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒A▒▒▒▒▒▒▒
▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒D▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▓▒▒
▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▓▒▒D▒▒▒▒▒▒▒▒▒▒▒A▓▓▒▒▒
▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒D▒▒RR▒▒▒▒▒▒▒▒▒▒▒▒С▓▒▒
▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒D▒▒R▒▓▒▒▒▒▒▓▒▒▒▒▒▒▒▒
▒▓▓▒С▒▓▒▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒R▒▒▒▒▒▒▒A▒▒▒▒▒▒▒
▒▒▒▒K▒▒▒▒▒▓▒▒▓▒▒▒▒D▒▒▒▓▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▓
▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒A▒▒▓▒▒▒▒▒
▒▒▒▒▒▓▓▒▒▒▒▒A▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▓▒▒▒▒▒▒▒
▒▒▒▒▓▓▒▒▒▒▒▒▒▒▓▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒A▒▓▒▒▒▒▒
▒▒▒▒▓▓▒▒▒▓▓▒▒▓▒▒▒▓▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▓▒
 */









 