package oop1;

public class Drink extends Product {
    private int volume;//Объём

    
    public Drink(String name, int price, int count, String unit, int volume )
    {
        super(name, price, count, unit);
        this.volume = volume;
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" volume: %d ", this.volume  );
    }
}


