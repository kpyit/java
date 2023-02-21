package oop_lab2;
/**
 * крестьянин
 */
class Peasant extends Human {
    /**
     * 
     */
    public Peasant(String name) {
        super(100, 0, 0, 0, name);
        super.mana = super.mana / 2;
        this.className = "Peasant";
    }

    public Peasant() {
        this("");
    }

    @Override
    public Human create() {
        return new Peasant();
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
    }

}
