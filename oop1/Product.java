package oop1;

public class Product {

    private String name;
    private int price;
    private int count;
    private String unit;

    public Product(String name, int price, int count, String unit) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.unit = unit;
    }
    protected String info()
    {
        return String.format("name: %s, price: %d, count: %d, unit:%s", this.name, this.price, this.count, this.unit);
    }
    @Override
    public String toString() {
        return this.info();
    }

}
