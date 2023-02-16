package oop2;

public interface Animal
{
    float animalGrowth=0;    //Рост животного
    float animalWeight=0;    //Вес животного
    String animalEyeColor = new String();  //Цвет глаз животного

    void makeSound();
    void print();
}
// public abstract class animal {
//     public abstract void m1();
//     public abstract void m2();
// }