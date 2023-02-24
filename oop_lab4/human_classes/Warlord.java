package oop_lab4.human_classes;
/**
 * Копейщик
 */
public class Warlord extends Fighter {
  
    public Warlord(String name) 
    {
        super(4,4,
        10,0,5,
        1,3,
        0,
        false,
        false,
        name); 

        // super.armor += heavyArmorMastery(super.armor);
        this.className = "Warlord";
    }

    public Warlord() {
        this("");
    }

    @Override
    public Human create() {
        return new Warlord();
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
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

