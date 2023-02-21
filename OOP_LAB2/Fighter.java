package oop_lab2;
 
/**
 * боец начальный класс воинов
 */
class Fighter extends Human {

    
    int range = 200;

    public Fighter(int addHP, int addMP,int addArmor ,int addDamage, String name)
    {        
        super(200+addHP,50+addMP,5+addArmor, 10+addDamage,name);
        super.armor += armorMastery(super.armor);
        super.damage += weaponMastery(super.damage);
        super.className = "Fighter";
    }
   
    public Fighter(String name)
    {
        super(200,50,5, 10,name);
        super.armor += armorMastery(super.armor);
        super.damage += weaponMastery(super.damage);
        super.className = "Fighter";
    }

    public Fighter()
    {
        this("");
    }

    @Override
    public Human create() {
        // TODO Auto-generated method stub
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

