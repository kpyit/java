package oop_lab1;



// https://repo1.maven.org/maven2/org/openjdk/jol/
//библиотеки цепляются к проекту в java project -> ref libs
//позволяет анализировать структуру обьектов https://www.baeldung.com/java-memory-layout
import org.openjdk.jol.info.ClassLayout;//https://repo1.maven.org/maven2/org/openjdk/jol/jol-core/0.16/
import org.openjdk.jol.vm.VM;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.logging.Level;//https://javarush.ru/groups/posts/2200-logirovanie-razmotatjh-klubok-stektreysa
import java.util.logging.Logger;
 
public class Program {

    static Logger LOGGER;
    static { 
            LOGGER = Logger.getLogger(Program.class.getName());
    } 

    private static void printInfo(Human man) {
        // LOGGER.log(Level.INFO,man.getClass().getName());
        // LOGGER.log(Level.INFO,man.toString());
        System.out.println(man.getClass().getName());
        System.out.println(man.toString());
    }

    public static void main(String[] args)
    {

        Peasant man1 = new Peasant();

        Fighter hero1 = new Fighter(0,0,0,0); 
                
        Warlord warlord1 = new Warlord(); 

        Rogue rogue1 = new Rogue(); 

        Hawkeye hawkeye1 = new Hawkeye(); 

        Arbalester arbalester1 = new Arbalester(); 

        Warlock warlock1 = new Warlock();

        Wizard wizard1 = new Wizard();

        Cleric Cleric1  = new Cleric();
 
        hawkeye1.doubleShot(arbalester1);

        System.out.println(warlord1.getInfo());
        rogue1.criticalDamage(warlord1);
        System.out.println(warlord1.getInfo());


        printInfo(rogue1);

        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(Arbalester.class).toPrintable());
        System.out.println(ClassLayout.parseInstance(arbalester1).toPrintable());


    }
}
/* >>>

hp 600  Mana: 300  armor: 111
hp 559  Mana: 300  armor: 111
 

oop_lab1.Rogue
oop_lab1.Rogue@3941a79c      
   
oop_lab1.Arbalester object internals:
OFF  SZ               TYPE DESCRIPTION               VALUE
  0   8                    (object header: mark)     N/A
  8   4                    (object header: class)    N/A
 12   4                int Human.mana                N/A
 16   4                int Human.hp                  N/A
 20   4                int Human.armor               N/A
 24   4                int Human.damage              N/A
 28   4   java.lang.String Human.name                N/A
 32   4                int Fighter.range             N/A
 36   4                    (object alignment gap)
Instance size: 40 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

oop_lab1.Arbalester object internals:
OFF  SZ               TYPE DESCRIPTION               VALUE
  0   8                    (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
  8   4                    (object header: class)    0x01000400
 12   4                int Human.mana                300
 16   4                int Human.hp                  362
 20   4                int Human.armor               98
 24   4                int Human.damage              121
 28   4   java.lang.String Human.name                null
 32   4                int Fighter.range             800
 36   4                    (object alignment gap)
Instance size: 40 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 */