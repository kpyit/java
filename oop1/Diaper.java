package oop1;

public class Diaper extends HygieneItem{
    private int size;//Размер
	private int minimumWeight;//Минимальный вес
	private int maximumWeight;//Максимальный вес
	private String type; //Тип

    public Diaper(String name, int price, int count, String unit, int piecesPackage
    ,int size,int minimumWeight, int maximumWeight,String type)
    {
        super(name, price, count, unit, piecesPackage);

        this.size = size; 
        this.minimumWeight = minimumWeight; 
        this.maximumWeight = maximumWeight; 
        this.type = type; 
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" size: %d minimumWeight: %d maximumWeight: %d type: %s", this.size, this.minimumWeight, this.maximumWeight, this.type); 
    }
}
