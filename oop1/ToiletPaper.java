package oop1;

public class ToiletPaper extends HygieneItem{
    private int numberLayers;

    public ToiletPaper(String name, int price, int count, String unit, int piecesPackage,int numberLayers )
    {
        super(name, price, count, unit, piecesPackage);
        this.numberLayers = numberLayers; 
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" numberLayers: %d", this.numberLayers); 
    }
}
