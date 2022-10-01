package oop1;

public class Milk extends Drink {
    public int fatPercentage; // Процент жирности
    public int expirationDate;// Срок годности

    public Milk(String name, int price, int count, String unit, int volume, int fatPercentage, int expirationDate) {
        super(name, price, count, unit, volume);
        this.fatPercentage = fatPercentage;
        this.expirationDate = expirationDate;
    }

    @Override
    public String info() {
        String supinfo = super.info();
        return supinfo
                + String.format(" fatPercentage: %d, expirationDate: %d ", this.fatPercentage, this.expirationDate);
    }

}
