package oop_lab1;
 
/**
 * 
 */
class Warlord extends Fighter {

    public Warlord()
    {
        super(300, 150, 15, 15);
        super.armor += heavyArmorMastery(super.armor);
    }

    // Сокрушительная Мощь Ур.1 Power Crush
    // 60 урона  18 MP    
    // Сокрушительная Мощь
    public void powerCrush(Human human)
    {
        if(super.mana >= 18)        
        {
            mana-=18;  
            int newHp = ((human.getHp()-super.damage+15) < 0)? 0:(human.getHp()-super.damage+15);
            human.setHp(newHp);
        }  
    } 
    
    // Владение Тяжелой Броней
    private int heavyArmorMastery(int defWeapon)
    {
        return defWeapon+21;
    } 
}

