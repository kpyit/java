package oop_lab4.human_classes;

import static oop_lab4.constants.*;

import java.util.ArrayList;

/**
 * боец начальный класс воинов
 */
public class Fighter extends Human {

    public Fighter(int onrush, int speed,
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

        this.className = "Fighter";
        this.Symbol = 'F';
    }

    public Fighter(String name) {
        this(4, 4,
                10, 0, 5,
                1, 3,
                0,
                false, false,
                name);
    }

    public Fighter() {
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

    /*
     * метод определяющий логику персонажа
     */
    @Override
    public int getNextAction(ArrayList<Human> allHumans, double matrixRange[][]) {
        this.enamies = new ArrayList<>();
        double range2enamy;
        double minDistance = 1000;
        int currentIdEnamy = -1;

        // поиск ближайшего врага
        for (int idHuman = 0; idHuman < allHumans.size(); idHuman++) {
            if ((this.group != allHumans.get(idHuman).group) && (allHumans.get(idHuman).hp > 0)) {
                range2enamy = ((this.id > idHuman) ? matrixRange[idHuman][this.id] : matrixRange[this.id][idHuman]);
                // System.out.printf("get range2enamy %3.1f \n",range2enamy);//get range2enamy
                // 32,1
                // тут должена быть проверка достижимости цели &&
                if ( range2enamy < minDistance) {
                    currentIdEnamy = idHuman;
                    minDistance = range2enamy;
                }
                this.enamies.add(allHumans.get(idHuman));
            }
        }

        // есть вообще есть противники и средства
        if (this.enamies.size() == 0) {
            return NOTHFING;
        }
        this.idHumanAttack = currentIdEnamy;
        // System.out.printf("minDistance: %3.1f range: %3.1f \n",minDistance,
        // this.range);
        if (this.range > minDistance) {
            return ATTACK_ENAMY;
        } else {
            return GO_TO_RANGE;// идем на дистанцию атаки посокльку
        }
    }

    public int damage(Human enamy, double matrixRange[][]) {

        // стандартный урон оружия ближнего боя
        double gauss = rand.nextGaussian();
        double DamageRnd = this.damage + (this.damageMax - this.damage) * (1.0 + gauss) * 0.5;
        int calcDamage = ((int) DamageRnd - enamy.armor > 0) ? ((int) DamageRnd - enamy.armor) : 1;
        // Если добили
        enamy.hp = (calcDamage > enamy.hp) ? 0 : enamy.hp - calcDamage;
        /*
         * System.out.printf("damage %3.1f damageMax %3.1f %n",damage,damageMax);
         * System.out.printf("урон по гауссу:  %3.1f  gauss: %3.1f%n", damageRnd,
         * gauss);
         * System.out.printf("нанесено урона: %d   armor= %d  %n" , calcDamage,
         * enamy.armor);
         */
        return calcDamage;
    }

    // Владение Оружием
    private int weaponMastery(int defWeapon) {
        return defWeapon + 13;
    }

    // Владение Броней
    private int armorMastery(int defArmor) {
        return (int) (defArmor * 1.085) + 4;
    }

    // Мощный Удар
    public void powerStrike(Human human) {
        if (super.mana >= 17) {
            super.mana -= 17;

            // int newHp = ((human.getHp()-super.damage+15) < 0)?
            // 0:(human.getHp()-super.damage+15);
            int newHp = 100;
            human.setHp(newHp);
        }
    }

    // Мощный Выстрел
    public void powerShot(Human human) {
        if (super.mana >= 15) {
            mana -= 15;
            // int newHp = ((human.getHp()-super.damage+15) < 0)?
            // 0:(human.getHp()-super.damage+15);
            int newHp = 100;
            human.setHp(newHp);
        }
    }

}
