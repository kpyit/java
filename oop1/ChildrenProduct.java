package oop1;

public class ChildrenProduct extends Product {
    private int minimumAge; //Минимальный возраст
	private Boolean hypoallergenic; //Гипоаллергенность

        
    public ChildrenProduct(String name, int price, int count, String unit, int minimumAge,Boolean hypoallergenic)
    {
        super(name, price, count, unit);
        this.minimumAge = minimumAge;
        this.hypoallergenic = hypoallergenic;
    }

    @Override
    public String info()
    {
        String supinfo = super.info();
        return supinfo+String.format(" minimumAge: %d, hypoallergenic: %b", this.minimumAge, this.hypoallergenic);
    }

}

 