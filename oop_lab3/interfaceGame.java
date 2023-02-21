package oop_lab3;

/*
 * ккие методы мы обязаны реализовать
*/
public interface interfaceGame {
    /**
     * описывает поведение персонажа каждый ход
     * видимо прийдется передавать сцену
     */
    void step();
    /**
     * @return
     */
    String getInfo();//тела быть не может
}
