package oop_lab2;


/**
 * Warlock (Колдун) продвинутый класс магов
 */
class Warlock extends Human { 
        
    public Warlock(int addHP, int addMP, int addArmor, int addDamage, String name) {
        super(100 + addHP, 450 + addMP, 5 + addArmor, 10 + addDamage, name);
        this.className = "Warlock";
    } 
     
    public Warlock(String name) 
    {
        this(0,0,0,0,name);
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
