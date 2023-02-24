package oop_lab4.human_classes;

/**
 * крестьянин
 */
public class Peasant extends Human {
    /**
     * 
     */
    public Peasant(String name) {
        super(1,3,
         1, 0, 1, 
        1,1,
        0,
        true, false,
         name);
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
