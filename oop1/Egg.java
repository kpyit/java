package oop1;

public class Egg extends FoodStuff{
    private int amountInPackage;

    public Egg(String name, int price, int count, String unit, String expirationDate, int amountInPackage )
    {
        super(name, price, count,unit, expirationDate);
        this.amountInPackage = amountInPackage; 
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" amountInPackage: %d", this.amountInPackage);
        
    }
}
