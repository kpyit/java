package oop_lab1;

/**
 * Warlock (Колдун) продвинутый класс магов
 */
class Warlock extends Human {

    public Warlock(int addHP, int addMP, int addArmor, int addDamage) {
        super(100 + addHP, 450 + addMP, 5 + addArmor, 10 + addDamage);
    }
 
    public Warlock()
    {
        this(0,0,0,0);
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
     * @param cat
     */
    public void groupHeal(Human cat) {
        if (super.mana >= 10) {
            super.mana -= 10;
            cat.setHp(cat.getHp() + 100);
        }
    }

}
