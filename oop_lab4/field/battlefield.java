package oop_lab4.field;

import static oop_lab4.constants.*; 
import oop_lab4.human_classes.Human; 


import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Collections;
import java.util.Comparator; 


/*
 *  *  КЛАСС ПОЛЕ СРАЖЕНИЯ
 */
public class battlefield extends drawField {

    //матрица позиций героев  id в позиции
    int[][] pos_heroes;
    //матрица расстояний между каждым из игроков 
    double matrixRange[][];
 
    int field_prebuffer1[][];// поле с текущим окружением но без действий текущего игрока

    int field_buffer1[][];// поле с текущим окружением  
    int field_buffer2[][];// поле с текущим дейтсвием игрока с отрисовкой пользователю с шагом задержки
  
    int nextIdGroup = 0;
    int nextId2Step = 0;
    int nextIdMax = 0;

    ArrayList<Human> action_queue = new ArrayList<>();//очередь игроков 
  
    //очередь в игре сортировка
    Comparator<Human> sort4action = new Comparator<Human>() {
        public int compare(Human h1, Human h2) {
            if(h1.getMana() < h2.getMana())
                return 1;
            else if(h1.getMana() > h2.getMana())
                return -1;
            else return 0;
        };        
    };


    /**
     * конструктор отвечающий за создание боевого поля 
     * @param height высота поля
     * @param width ширина поля     * 
    */
    public battlefield(int height, int width){
        super(height, width);

        this.pos_heroes = new int[height][width];//id hero
       
        this.setMatrixValue( pos_heroes, -1); 
    } 
 

    /* добавляет новую группу персонажей в очередь действий */
    public int addGroup(ArrayList<Human> newGroup)
    {
        this.nextIdGroup++;
        //устанавливаем группу пользователю
        newGroup.forEach(n -> n.group = nextIdGroup);
        
        this.action_queue.addAll(newGroup);

        Collections.shuffle(this.action_queue);

        this.action_queue.sort(sort4action);//сортировка по скорости

        this.nextIdMax = action_queue.size();

        //каждое существо хранит свой id списка
        for (int i = 0; i < this.action_queue.size(); i++)
            this.action_queue.get(i).id = i;
        

        //треугольная      
        this.matrixRange = new double[nextIdMax][nextIdMax];

        return this.nextIdGroup; //
    }



    public void initPositionsGroup()
    { 
        int maxGroup = this.nextIdGroup;

        ArrayList<Point> generatedPos = new ArrayList<>();

        Point newPos;

        for(int idGroup = 1; idGroup <= maxGroup; idGroup++){
            for(int id  = 0; id  < nextIdMax; id++)
            {            
                if(this.action_queue.get(id).group == idGroup){
                    do { 
                        newPos = new Point(this.rand.nextInt( 2, this.heightField-2),
                                            this.rand.nextInt((int)(this.lenghtField*POS_SHIFT[idGroup]),(int)(this.lenghtField*POS_SHIFT[idGroup]+2)));
                    }
                    while(generatedPos.contains(newPos));//уже сгерерированная точка можно еще проверку на дерево добавить
                    generatedPos.add(newPos);

                    pos_heroes[newPos.x][newPos.y] = id;

                    this.action_queue.get(id).pos = newPos;
                }
            }
        } 
        this.ranges_calc_full(this.matrixRange, this.action_queue);        
    }
 

    /*
     * 
     */
    private void stepNextHero(int id)
    { 
        if(this.action_queue.get(id).getHp() == 0)
            return;
        //.clone(); ссылается на теже ячейки памяти!
        this.field_buffer1 = Arrays.stream(field_raw).map(int[]::clone).toArray(int[][]::new);//Поле для поиска пути        
        //в свиче не дает выполнить!
        this.action_queue.forEach(human -> field_buffer1[human.pos.x][human.pos.y]=1);//для растчета герои препятствие

        ArrayList<Point> pathHero; 
        int damage;
        Human hero;
        Human partyH;
        Human enamy;
         
        switch (this.action_queue.get(id).getNextAction(this.action_queue,this.matrixRange)) 
        {
            case NOTHFING:           
                // System.out.printf("NOTHFING %s %n", this.action_queue.get(id).className) ;
                break;
            case GO_TO:            
                // System.out.printf("GO_TO %s %n", this.action_queue.get(id).className) ;
                break;
            case GO_TO_RANGE:  
                // System.out.printf("GO_TO_RANGE %s %n", this.action_queue.get(id).className);

                Human actionHero = this.action_queue.get(id);                 
                // this.field_buffer2  = Arrays.stream(field_buffer1).map(int[]::clone).toArray(int[][]::new);//Поле для поиска пути        
                pathHero = this.steps2Range(this.field_buffer1, actionHero.pos, this.action_queue.get(actionHero.idHumanAttack).pos,actionHero.range, actionHero.speed);
                 
                //случай если к ближайшему врагу пути не найдено
                int counter_enamies = 0;
                while(pathHero.isEmpty() && counter_enamies < actionHero.enamies.size())
                {
                    // this.field_buffer2  = Arrays.stream(field_buffer1).map(int[]::clone).toArray(int[][]::new);//Поле для поиска пути        
                    pathHero = this.steps2Range(this.field_buffer1, actionHero.pos, actionHero.enamies.get(counter_enamies).pos,actionHero.range, actionHero.speed);
                    
                    actionHero.idHumanAttack = actionHero.enamies.get(counter_enamies).id;

                    counter_enamies++;
                }//отсюда с путем до друго бойца или опять ничего
 

                /* анимация событий хода игрока */    
                for (Point point : pathHero){  
                    pos_heroes[actionHero.pos.x][actionHero.pos.y] = -1;
                    actionHero.pos = point;
                    pos_heroes[actionHero.pos.x][actionHero.pos.y] = actionHero.id; 

                    this.printFieldVGroups(field_raw, this.action_queue, this.pos_heroes);  
                    this.sleep100();
                } 
                //TODO: ПЕРЕКЛЮЧИТЬ НА РАСЧЕТ ДЛЯ 1 ГЕРОЯ
                this.ranges_calc_full(this.matrixRange, this.action_queue);        
                break;

            case ATTACK_ENAMY:
                // System.out.printf("ATTACK_HUMAH %s \n", this.action_queue.get(id).className) ;
                //отрисовать тоже самое поле только 1 герою фон поставить красным которого атаковали
                printFieldVGroupsEvent(field_raw, this.action_queue, this.pos_heroes, this.action_queue.get(id).idHumanAttack, ANSI_RED_BACKGROUND);
                this.sleep();

                //Нанести урон и в консоль вывести сюда
                enamy = this.action_queue.get(this.action_queue.get(id).idHumanAttack);
                hero = this.action_queue.get(id);

                damage = this.action_queue.get(id).damage(enamy, this.matrixRange);                
                // System.out.printf("Hero %s %s  damage %d to hero %s %s \n",  hero.name,hero.className,damage,enamy.name,enamy.className);
 
                break;
            case HEALING:
                // System.out.printf("HEALING %s \n", this.action_queue.get(id).className) ;

                //  фон  зеленым которого лечим
                printFieldVGroupsEvent(field_raw, this.action_queue, this.pos_heroes, this.action_queue.get(id).idHumanAttack, ANSI_GREEN_BACKGROUND);
                this.sleep();

                //Нанести урон и в консоль вывести сюда
                partyH = this.action_queue.get(this.action_queue.get(id).idHumanAttack);
                hero = this.action_queue.get(id);

                damage = this.action_queue.get(id).damage(partyH, this.matrixRange);                
                // System.out.printf("Hero %s %s  вылечил %d -> hero %s %s \n",hero.name,hero.className,damage, partyH.name,partyH.className);

                break;                
            default:
                // System.out.printf("default %s \n", this.action_queue.get(id).className) ;
            break;
        }; 
    }//public void stepNextHero(int id)
    
    /**
     * 
     * @return  выйгравшую группу
     */
    public int round()
    {
        this.action_queue.forEach(h->this.stepNextHero(h.id));

        //некая группа выйграла
        int count_live=0;
        int live_group=0;
        int count_live_group=0;

        for(int g =0 ; g < nextIdMax; g++)
        {
            for(int i = 0; i < this.action_queue.size(); i++)
            {
                if(this.action_queue.get(i).group == g && this.action_queue.get(i).hp > 0)
                {
                    count_live++;
                }
            }
            if(count_live > 0)
            {
                live_group = g;
                count_live_group++;
                count_live = 0;
            }
        }//тут можно взять статистику в каждом ходу

        if(count_live_group>1)
            return -1;
        else 
            return live_group;//группа выживших в сражении
    } 
}//public class battlefield 