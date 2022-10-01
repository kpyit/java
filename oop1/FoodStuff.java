package oop1;
 
public class FoodStuff extends Product{
    private String expirationDate ;//Срок годности
    
    public FoodStuff(String name, int price, int count, String unit, String expirationDate)
    {
        super(name, price, count, unit); 
        this.expirationDate = expirationDate; 
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" expirationDate: %s ", this.expirationDate );
    }

}