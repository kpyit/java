package oop_lab3.human_classes;


/**
 * Warlock (Колдун) продвинутый класс магов
 */
public class Warlock extends Human { 
        
    public Warlock(int onrush, int speed,
    int HP, int MP,int armor ,
    int damage, int damageMax, 
    int numberShells,
    Boolean logistics, Boolean magic,    
    String name){
        super(17,9,
        30,0,12,
        -5,-5,
        0,
        false,
        true,
        name);
        this.className = "Warlock";
    } 
     
    public Warlock(String name) 
    {
        this(17,9,
        30,0,12,
        -5,-5,
        0,
        false,
        true,
        name);
    } 

    public Warlock() {
        this("");
    }

    @Override
    public Human create() {
        return new Warlock();
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
    }

    /**
     * Призвать Кая по идее в группу должен добавлятся кот
     */
    public void summonKaiTheCat() {
        if (super.mana >= 10) {
            super.mana -= 10;
        }
    }

    /**
     * Излечить Слугу кот тоже человек
     * 
     * @param cat
     */
    public void groupHeal(Human cat) {
        if (super.mana >= 10) {
            super.mana -= 10;
            cat.setHp(cat.getHp() + 100);
        }
    }

}
