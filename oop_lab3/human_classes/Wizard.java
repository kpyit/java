package oop_lab3.human_classes;

/**
 * Wizard начальный класс магов
 */
public class Wizard extends Human {

    int range = 200;

    public Wizard(int onrush, int speed,
        int HP, int MP,int armor ,
        int damage, int damageMax, 
        int numberShells,
        Boolean logistics, Boolean magic,    
        String name)
    {
        super(10,5,
        30,0,5,
        -4,-4,
        0,
        false,
        true,
        name);  
        this.className = "Wizard";
    }

    public Wizard(String name) {
        this(10,5,
        30,0,5,
        -4,-4,
        0,
        false,
        true,
        name); 
    }

    public Wizard() {
        this("");
    }

    @Override
    public Human create() {
        return new Wizard();
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
    }

    // Вспышка
    public void blaze(Human human) {
        if (super.mana >= 17) {
            mana -= 17;
            int newHp = ((human.getHp() - super.damage + 45) < 0) ? 0 : (human.getHp() - super.damage + 15);
            human.setHp(newHp);
        }
    }

    // Сжигающая Аура
    public void auraBurn(Human human) {
        if (super.mana >= 15) {
            mana -= 15;
            int newHp = ((human.getHp() - super.damage + 47) < 0) ? 0 : (human.getHp() - super.damage + 15);
            human.setHp(newHp);
        }
    }
}
