package oop_lab1;
 
/**
 * Wizard начальный класс магов
 */
class Wizard extends Human {

    int range = 200;

    public Wizard(int addHP, int addMP,int addArmor ,int addDamage)
    {
        super(100+addHP,350+addMP,5+addArmor, 10+addDamage); 
    }

    public Wizard()
    {
        this(0,0,0,0); 
    }
 
    // Вспышка
    public void blaze(Human human)
    {
        if(super.mana >= 17)        
        {
            mana-=17;  
            int newHp = ((human.getHp()-super.damage+45) < 0)? 0:(human.getHp()-super.damage+15);
            human.setHp(newHp);
        } 
    } 
 

   //Сжигающая Аура
   public void auraBurn(Human human)
   { 
       if(super.mana >= 15)        
       {
           mana-=15;  
           int newHp = ((human.getHp()-super.damage+47) < 0)? 0:(human.getHp()-super.damage+15);
           human.setHp(newHp);
       }      
   } 
}

