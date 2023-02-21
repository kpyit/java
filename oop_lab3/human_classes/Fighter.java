package oop_lab3.human_classes;
 
/**
 * боец начальный класс воинов
 */
public class Fighter extends Human {

    
    int range = 200;

    public Fighter(int onrush, int speed,
    int HP, int MP,int armor ,
    int damage, int damageMax, 
    int numberShells,
    Boolean logistics, Boolean magic,    
    String name)
    {        
        super(onrush, speed, 
        HP,MP,armor,
        damage,  damageMax,
        numberShells,
        logistics,magic,
        name);
        super.className = "Fighter";
    }
   
    public Fighter(String name)
    {
        super(4, 4,
        10,0,5, 
        1,3,
        0,
        false,false,
        name);        
        super.className = "Fighter";
    }

    public Fighter()
    {
        this("");
    }

    @Override
    public Human create() {
        return new Fighter();
    }


    @Override
    public String getInfo() {
        
        return super.getInfo();
    }
     
    
    @Override
    public void step() {
        // TODO Auto-generated method stub

    }




    // Владение Оружием 
    private int weaponMastery(int defWeapon)
    {
        return defWeapon+13;
    }
    // Владение Броней
    private int armorMastery(int defArmor)
    {
        return (int)(defArmor*1.085)+4; 
    }  

    // Мощный Удар
    public void powerStrike(Human human)
    {
        if(super.mana >= 17)        
        {
            super.mana-=17;  
            
            int newHp = ((human.getHp()-super.damage+15) < 0)? 0:(human.getHp()-super.damage+15);
            human.setHp(newHp);
        }
    } 

    //Мощный Выстрел 
    public void powerShot(Human human)
    {
        if(super.mana >= 15)        
        {
            mana-=15;          
            int newHp = ((human.getHp()-super.damage+15) < 0)? 0:(human.getHp()-super.damage+15);
            human.setHp(newHp);
        }
    }


}

