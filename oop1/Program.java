package oop1;
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
     
    private static void printInfo(Product prod) {
        // LOGGER.log(Level.INFO,prod.getClass().getName());
        // LOGGER.log(Level.INFO,prod.toString());
        System.out.println(prod.getClass().getName());
        System.out.println(prod.toString());
    }

    public static void main(String[] args) {
       
        Product pr1 = new Product("чепупелли", 200, 300000, "штука");
        printInfo(pr1);

        ChildrenProduct childProd1 = new ChildrenProduct("корм", 100, 2, "кг", 3, true);
        printInfo(childProd1);

        Drink drink1 = new Drink("Кола", 30, 1, "л", 1);
        printInfo(drink1);
          
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 

        FoodStuff foodstuff1 = new FoodStuff("Байкал", 30, 1, "л", formatter.format(date));
        printInfo(foodstuff1);
         
        HygieneItem hygieneItem1 = new HygieneItem("прокладка", 30, 1, "шт", 20);
        printInfo(hygieneItem1);

        Lemonade lemonade1 = new Lemonade("колокольчик", 30, 1, "л",1, true);
        printInfo(lemonade1);
        
        Milk milk1 = new Milk("Горбенки", 30, 1, "л",1, 5, 6);
        printInfo(milk1);
                        
        Bread bread1 = new Bread("Черный", 20, 1, "шт", "завтра", "ржаная");
        printInfo(bread1);
        
        Egg egg1 = new Egg("свежие", 20, 1, "шт", "завтра", 10);
        printInfo(egg1);
        
        Mask mask1 = new Mask("синие", 20, 1, "шт", "завтра");
        printInfo(mask1); 

        ToiletPaper toiletPaper1 = new ToiletPaper("зева", 20, 1, "рулон", 4,3);
        printInfo(toiletPaper1);

        Diaper diaper1 = new Diaper("подгузник", 20, 1, "щт", 4,5,0,3,"small");
        printInfo(diaper1);
        
        Pacifier pacifier1 = new Pacifier("соска", 20, 1, "шт",1, true);
        printInfo(pacifier1);

        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(ChildrenProduct.class).toPrintable());
        System.out.println(ClassLayout.parseInstance(childProd1).toPrintable());




    }
}
