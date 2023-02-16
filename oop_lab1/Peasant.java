package oop_lab1;
/**
 *  крестьянин
 */
class Peasant extends Human{
    /**
     * 
     */
    public Peasant(){
        super(100,0,0,0);
        super.mana = super.mana/2;
    }
}


