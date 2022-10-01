package oop1;

public class Lemonade extends Drink {
    public boolean aerated;// газированная вода

    public Lemonade(String name, int price, int count, String unit, int volume, boolean aerated) {
        super(name, price, count, unit, volume);
        this.aerated = aerated;
    }

    @Override
    public String info() {
        String supinfo = super.info();
        return supinfo + String.format(" aerated: %b ", this.aerated);
    }
}
