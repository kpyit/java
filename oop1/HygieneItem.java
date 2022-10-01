package oop1;

public class HygieneItem extends Product {
    public int piecesPackage;//Количество штук в упаковке

      
    public HygieneItem(String name, int price, int count, String unit, int piecesPackage )
    {
        super(name, price, count, unit);
        this.piecesPackage = piecesPackage; 
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" piecesPackage: %d", this.piecesPackage);
        
    }

}
