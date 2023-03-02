package oop_lab4.human_classes;

import static oop_lab4.constants.*;

import java.util.ArrayList;

/**
 * Wizard начальный класс магов
 */
public class Wizard extends Human {

    public Wizard(int onrush, int speed,
        int HP, int MP,int armor ,
        double damage, double damageMax, 
        int numberShells,
        Boolean logistics, Boolean magic,    
        String name)
    {
        super(onrush, speed,
                HP, MP, armor,
                damage, damageMax,
                numberShells,
                logistics, magic,
                name);

        this.range = 40;
        this.className = "Wizard";
        this.Symbol = 'W';
    }

    public Wizard(String name) {
        this(10,5,
        30,0,5,
        4,4,
        0,
        false,
        true,
        name); 
    }

    public Wizard() {
        this("");
    }

    @Override
    public Human create() {
        return new Wizard();
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
    }

    /*
     * метод определяющий логику персонажа
     */
    @Override
    public int getNextAction(ArrayList<Human> allHumans, double matrixRange[][]) {
        // можно сюда сделать внутренний список врагов
        this.enamies = new ArrayList<>();

        double range2enamy;
        double minDistance = 1000;
        int currentIdEnamy = -1;

        // поиск ближайшего врага
        for (int idHuman = 0; idHuman < allHumans.size(); idHuman++) {
            if ((this.group != allHumans.get(idHuman).group) && (allHumans.get(idHuman).hp > 0)) {
                range2enamy = ((this.id > idHuman) ? matrixRange[idHuman][this.id] : matrixRange[this.id][idHuman]);

                // System.out.printf("get range2enamy %3.1f \n",range2enamy);//get range2enamy 32,1                
                if (range2enamy < minDistance) {
                    currentIdEnamy = idHuman;
                    minDistance =   range2enamy;
                }
                this.enamies.add(allHumans.get(idHuman));
            }
        }

        if (this.enamies.size() == 0)
        {
            return NOTHFING;
        } 
        this.idHumanAttack = currentIdEnamy;

        // System.out.printf("minDistance: %3.1f  range: %3.1f \n",minDistance, this.range);
        if (this.range > minDistance) {
            return ATTACK_ENAMY;
        } else {
            return GO_TO_RANGE;
        }
    }

    @Override
    public int damage(Human enamy, double  matrixRange[][]){ 
         
        enamy.hp = ((int)this.damage > enamy.hp)?0:enamy.hp-(int)this.damage;        
        // System.out.printf("нанесено урона: %d броня игрнорируется %n" , (int)this.damage );
        return (int)this.damage;        
    }

 

    // Вспышка
    public void blaze(Human human) {
        if (super.mana >= 17) {
            mana -= 17;
            // int newHp = ((human.getHp() - super.damage + 45) < 0) ? 0 : (human.getHp() - super.damage + 15);
            int newHp =100;
            human.setHp(newHp);
        }
    }

    // Сжигающая Аура
    public void auraBurn(Human human) {
        if (super.mana >= 15) {
            mana -= 15;
            // int newHp = ((human.getHp() - super.damage + 47) < 0) ? 0 : (human.getHp() - super.damage + 15);
            int newHp = 100;
            human.setHp(newHp);
        }
    }
}
