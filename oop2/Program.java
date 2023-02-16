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
/*
 * >>>
 oop1.Product
name: чепупелли, price: 200, count: 300000, unit:штука
oop1.ChildrenProduct
name: корм, price: 100, count: 2, unit:кг minimumAge: 3, hypoallergenic: true
oop1.Drink
name: Кола, price: 30, count: 1, unit:л volume: 1
oop1.FoodStuff
name: Байкал, price: 30, count: 1, unit:л expirationDate: 01-10-2022 
oop1.HygieneItem
name: прокладка, price: 30, count: 1, unit:шт piecesPackage: 20
oop1.Lemonade
name: колокольчик, price: 30, count: 1, unit:л volume: 1  aerated: true
oop1.Milk
name: Горбенки, price: 30, count: 1, unit:л volume: 1  fatPercentage: 5, expirationDate: 6
oop1.Bread
name: Черный, price: 20, count: 1, unit:шт expirationDate: завтра  flourType: ржаная
oop1.Egg
name: свежие, price: 20, count: 1, unit:шт expirationDate: завтра  amountInPackage: 10
oop1.Mask
name: синие, price: 20, count: 1, unit:шт expirationDate: завтра
oop1.ToiletPaper
name: зева, price: 20, count: 1, unit:рулон piecesPackage: 4 numberLayers: 3
oop1.Diaper
name: подгузник, price: 20, count: 1, unit:щт piecesPackage: 4 size: 5 minimumWeight: 0 maximumWeight: 3 type: small
oop1.Pacifier
name: соска, price: 20, count: 1, unit:шт minimumAge: 1, hypoallergenic: true
# WARNING: Unable to get Instrumentation. Dynamic Attach failed. You may add this JAR as -javaagent manually, or supply -Djdk.attach.allowAttachSelf
# WARNING: Unable to attach Serviceability Agent. sun.jvm.hotspot.memory.Universe.getNarrowOopBase()
# Running 64-bit HotSpot VM.
# Using compressed oop with 3-bit shift.
# Using compressed klass with 3-bit shift.
# WARNING | Compressed references base/shifts are guessed by the experiment!
# WARNING | Therefore, computed addresses are just guesses, and ARE NOT RELIABLE.
# WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
# Objects are 8 bytes aligned.
# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]

oop1.ChildrenProduct object internals:
OFF  SZ                TYPE DESCRIPTION                      VALUE
  0   8                     (object header: mark)            N/A
  8   4                     (object header: class)           N/A
 12   4                 int Product.price                    N/A
 16   4                 int Product.count                    N/A
 20   4    java.lang.String Product.name                     N/A
 24   4    java.lang.String Product.unit                     N/A
 28   4                 int ChildrenProduct.minimumAge       N/A
 32   4   java.lang.Boolean ChildrenProduct.hypoallergenic   N/A
 36   4                     (object alignment gap)
Instance size: 40 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

oop1.ChildrenProduct object internals:
OFF  SZ                TYPE DESCRIPTION                      VALUE
  0   8                     (object header: mark)            0x0000000000000001 (non-biasable; age: 0)
  8   4                     (object header: class)           0x01000bf0
 12   4                 int Product.price                    100
 16   4                 int Product.count                    2
 20   4    java.lang.String Product.name                     (object)
 24   4    java.lang.String Product.unit                     (object)
 28   4                 int ChildrenProduct.minimumAge       3
 32   4   java.lang.Boolean ChildrenProduct.hypoallergenic   true
 36   4                     (object alignment gap)
Instance size: 40 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

 
 */