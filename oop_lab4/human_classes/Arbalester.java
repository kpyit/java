package oop_lab4.human_classes;

import static oop_lab4.constants.*;

import java.util.ArrayList;

/**
 * арбалетчик базовый класс стрелков
 */
public class Arbalester extends Human {

    public Arbalester(int onrush, int speed,
            int HP, int MP, int armor,
            int damage, int damageMax,
            int numberShells,
            Boolean logistics, Boolean magic,
            String name) {
        super(onrush, speed,
                HP, MP, armor,
                damage, damageMax,
                numberShells,
                logistics, magic,
                name);

        this.range = 16;
        this.className = "Arbalester";
        this.Symbol = 'A';
    }

    public Arbalester(String name) {
        this(6, 5,
                12, 0, 3,
                4, 7,
                46,
                false, false,
                name);
    }

    public Arbalester() {
        this("");
    }

    /*
     * метод определяющий логику персонажа
     */
    @Override
    public int getNextAction(ArrayList<Human> allHumans, double matrixRange[][]) {
        this.enamies = new ArrayList<>();

        double range2enamy;
        double minDistance = 1000;
        int currentIdEnamy = -1;

        for (int idHuman = 0; idHuman < allHumans.size(); idHuman++) {
            if ((this.group != allHumans.get(idHuman).group) && (allHumans.get(idHuman).hp > 0)) {
                range2enamy = ((this.id > idHuman) ? matrixRange[idHuman][this.id] : matrixRange[this.id][idHuman]);

                // System.out.printf("get range2enamy %3.1f \n",range2enamy);//get range2enamy
                // 32,1
                if (range2enamy < minDistance) {
                    currentIdEnamy = idHuman;
                    minDistance = range2enamy;
                }
                this.enamies.add(allHumans.get(idHuman));
            }
        }

        // есть вообще есть противники и стрелы
        if (this.enamies.size() == 0 || this.nShells == 0) {
            return NOTHFING;
        }
        this.idHumanAttack = currentIdEnamy;
        // System.out.printf("minDistance: %3.1f range: %3.1f \n",minDistance,
        // this.range);
        if (this.range > minDistance) {
            return ATTACK_ENAMY;
        } else {
            return GO_TO_RANGE;
        }
    }

    @Override
    public int damage(Human enamy, double matrixRange[][]) {

        double gauss = rand.nextGaussian();
        double range2Enamy = (this.id > this.idHumanAttack) ? matrixRange[idHumanAttack][this.id]
                : matrixRange[this.id][idHumanAttack];
        double damageRnd = this.damage + (this.damageMax - this.damage) * (1.0 + gauss) * 0.5;
        double damageByRange = damageRnd * (1 - 0.5 * range2Enamy / this.range);// макс дальность половина урона

        int calcDamage = ((int) damageByRange - enamy.armor > 0) ? ((int) damageByRange - enamy.armor) : 1;

        enamy.hp = (calcDamage > enamy.hp) ? 0 : enamy.hp - calcDamage;

        this.nShells--;

        /*
         * System.out.printf("damage %3.1f damageMax %3.1f %n",damage,damageMax);
         * System.out.printf("урон по гауссу:  %3.1f  gauss: %3.1f%n", damageRnd,
         * gauss);
         * System.out.println("расчитан урон с поправкой на расстояние: " +
         * damageByRange);
         * System.out.printf("нанесено урона: %d   armor= %d  %n" , calcDamage,
         * enamy.armor);
         */
        return calcDamage;
    }

    @Override
    public Human create() {
        return new Arbalester();
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
    }

    /**
     * Увеличивает дальность стрельбы из арбалета на 400
     * 
     * @param defRange
     * @return
     */
    private int increaseRange(int defRange) {
        return defRange + 400;
    }

    /**
     * Владение Арбалетом
     * 
     * @param defDamage
     * @return
     */
    private int crossbowMastery(int defDamage) {
        return defDamage + 15;
    }

    /**
     * @param defArmor
     * @return
     */
    private int lightArmorMastery(int defArmor) {
        return defArmor + 16;
    }
}