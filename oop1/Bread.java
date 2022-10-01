package oop1;

public class Bread  extends FoodStuff{
    private String flourType;

    public Bread(String name, int price, int count, String unit, String expirationDate, String flourType )
    {
        super(name, price, count,unit, expirationDate);
        this.flourType = flourType; 
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" flourType: %s", this.flourType);
        
    }
}
