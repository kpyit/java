package oop_lab4.field;

import static oop_lab4.constants.*;
import oop_lab4.field.utilCoords;
import oop_lab4.human_classes.Human;
import oop_lab4.field.WaveAlg;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/*
 *  *  КЛАСС ПОЛЕ СРАЖЕНИЯ
 */
public class battlefield extends utilCoords {

    
    int pos_heroes[][];//id hero
    //матрица расстояний между каждым из игроков 
    double matrixRange[][];

    int heightField;
    int lenghtField;
    int field_raw[][];// просто рандомно заполненный массив как шаблон для row



    int field_prebuffer1[][];// поле с текущим окружением но без действий текущего игрока

    int field_buffer1[][];// поле с текущим окружением  
    int field_buffer2[][];// поле с текущим дейтсвием игрока с отрисовкой пользователю с шагом задержки
 
    Random rand = new Random();

    WaveAlg findPath;
    


    int nextIdGroup = 0;
    int nextId2Step = 0;
    int nextIdMax = 0;
    ArrayList<Human> action_queue = new ArrayList<>();//очередь игроков для действий
  

    //очередь в игре может сортироваться по размному поэтому вынес компаратор сюда
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

        this.heightField = height;
        this.lenghtField = width;

        this.field_raw = new int[height][width];// карта местности [rows][columns]
        this.pos_heroes = new int[height][width];//id hero
        
        this.findPath = new WaveAlg(height,width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
            {
                this.pos_heroes[i][j] = -1;
            }
        }

        // Заполняем карту лесом
        for (int i = 0; i < this.heightField; i++) {
            for (int j = 0; j < this.lenghtField; j++) {
                field_raw[i][j] = (int) (rand.nextInt(11) / 10);
            }
        } 
    } 

    public void printFieldPlay() {

        for (int i = 0; i < this.heightField; i++) {
            for (int j = 0; j < this.lenghtField ; j++) {
                System.out.print(SQUARES[field_raw[i][j]]);
            }
            System.out.println();
        }
    }

    /* добавляет новую группу персонажей в очередь действий */
    public int addGroup(ArrayList<Human> newGroup)
    {
        this.nextIdGroup++;
        //устанавливаем группу пользователю
        newGroup.forEach((n) -> n.group = nextIdGroup);
        this.action_queue.addAll(newGroup);

        Collections.shuffle(this.action_queue);

        this.action_queue.sort(sort4action);//сейчас сортировка по скорости

        this.nextIdMax = action_queue.size();


        for (int i = 0; i < this.action_queue.size(); i++)//каждое существо хранит свой id
            this.action_queue.get(i).id = i;
        



        this.matrixRange = new double[nextIdMax][nextIdMax];//треугольная

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
  
        this.ranges_calc_full();        
    }

    /**
     * расчет матрицы растояний  
      */
    public void ranges_calc_full()
    {// треугольная чтобы получить растояние надо взять меньший id и посмотреть растояние до большего id
        for (int i = 0; i < nextIdMax; i++){
            for (int j = i; j < nextIdMax; j++){                
                this.matrixRange[i][j] = this.action_queue.get(i).pos.distance(this.action_queue.get(j).pos) ;//треугольная
            }
        }
    }

    /*
     * Перерасчет растояний для текущего игрока сделавшего ход
     */
    public void ranges_recalc_hero(int id)
    {
        Point PosCurrentHero =  this.action_queue.get(id).pos;
        //для перерасчета будет использоваться вся строка от id+1 
        for (int j = id+1; j < nextIdMax; j++){
            //растояние до самого себя не пересчитввается
            this.matrixRange[j][id] = PosCurrentHero.distance(this.action_queue.get(j).pos) ;
        }
        // и весь столбец id до id строки- для id меньше текущего 
        for (int j = 0; j < id; j++) {                
            this.matrixRange[id][j] = PosCurrentHero.distance(this.action_queue.get(j).pos) ;
        }
    }

    /** 
     * печать матрицы позиций существ
     */     
    public void print_pos_heroes() {
        for (int i = 0; i < heightField; i++) {
            for (int j = 0; j < lenghtField; j++) {
                System.out.print(String.format("|%2d", this.pos_heroes[i][j]));
            }
            System.out.println("|");
        }
    }
    /**
     * печать матрицы растояний между существами
     */
    public void print_ranges()
    {
        for (int i = 0; i < nextIdMax; i++) {
            for (int j = 0; j < nextIdMax; j++) {
                System.out.print(String.format("|%4.1f", this.matrixRange[i][j]));
            }
            System.out.println("|");
        }
    } 

    /**
     * Поле с группами
     */
    public void printFieldVGroups(int[][] field)
    {
        String buffer = "";//Нужно спроектировать буфера
        // field_buffer1 = field_raw.clone();//Поле которое будем наполнять игроками
        for (int i = 0; i < this.heightField; i++) {
            for (int j = 0; j < this.lenghtField ; j++){

                if (pos_heroes[i][j]>-1)//если в клетке существо
                { 
                    if(this.action_queue.get(pos_heroes[i][j]).hp == 0)//дохлый
                    {
                        buffer += ANSI_RED_BACKGROUND;
                    } else 
                        buffer += GROUP_COLORS[this.action_queue.get(pos_heroes[i][j]).group];
                    
                    buffer += this.action_queue.get(pos_heroes[i][j]).Symbol;

                    // buffer += GROUP_COLORS[this.action_queue.get(pos_heroes[i][j]).group]+this.action_queue.get(pos_heroes[i][j]).Symbol;
                } else {
                    buffer += SQUARES[field[i][j]];
                }
            }
            buffer +="\n";                      
        } 
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
        System.out.print(buffer);  
    }
    
    public void printFieldVGroupsEvent(int[][] field,int selectedId, String color)
    {
        String buffer = "";//Нужно спроектировать буфера
        // field_buffer1 = field_raw.clone();//Поле которое будем наполнять игроками
        for (int i = 0; i < this.heightField; i++) {
            for (int j = 0; j < this.lenghtField ; j++){
                if (pos_heroes[i][j]>-1)//если в клетке существо
                { 
                    if(selectedId == pos_heroes[i][j])
                        buffer += color;
                    else if(this.action_queue.get(pos_heroes[i][j]).hp == 0)//дохлый
                    {
                        buffer += ANSI_RED_BACKGROUND;
                    } else 
                        buffer += GROUP_COLORS[this.action_queue.get(pos_heroes[i][j]).group];
                    
                    buffer += this.action_queue.get(pos_heroes[i][j]).Symbol;
                } else {
                    buffer += SQUARES[field[i][j]];
                }
            }
            buffer +="\n";                      
        } 
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
        System.out.print(buffer);  
    }

    /*
     * 
     */
    private static void sleep()
    {
        try {//страшный способ спать
            // for (int i=0; i<timeToWait ; i++)
                Thread.sleep(200);
        } catch (InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }  
    }

    private static void sleep100()
    {
        try {//
                Thread.sleep(200);
        } catch (InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }  
    }
    
    /*
     * 
     */
    public void stepNextHero(int id)
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
                this.field_buffer2  = Arrays.stream(field_buffer1).map(int[]::clone).toArray(int[][]::new);//Поле для поиска пути        
                pathHero = this.steps2Range(this.field_buffer1, actionHero.pos, this.action_queue.get(actionHero.idHumanAttack).pos,actionHero.range, actionHero.speed);
                
                
                //случай если к ближайшему врагу пути не найдено
                int counter_enamies = 0;
                while(pathHero.isEmpty() && counter_enamies < actionHero.enamies.size())
                {
                    this.field_buffer2  = Arrays.stream(field_buffer1).map(int[]::clone).toArray(int[][]::new);//Поле для поиска пути        
                    pathHero = this.steps2Range(this.field_buffer2, actionHero.pos, actionHero.enamies.get(counter_enamies).pos,actionHero.range, actionHero.speed);
                    
                    actionHero.idHumanAttack = actionHero.enamies.get(counter_enamies).id;

                    counter_enamies++;
                }//отсюда с путем до друго бойца или опять ничего
 

                /* анимация событий хода игрока */    
                for (Point point : pathHero){  
                    pos_heroes[actionHero.pos.x][actionHero.pos.y] = -1;
                    actionHero.pos = point;
                    pos_heroes[actionHero.pos.x][actionHero.pos.y] = actionHero.id; 

                    this.printFieldVGroups(field_raw);  
                    this.sleep100();
                }
                
                //TODO: ПЕРЕКЛЮЧИТЬ НА РАСЧЕТ ДЛЯ 1 ГЕРОЯ
                this.ranges_calc_full();
                break;

            case ATTACK_ENAMY:
                // System.out.printf("ATTACK_HUMAH %s \n", this.action_queue.get(id).className) ;
                //отрисовать тоже самое поле только 1 герою фон поставить красным которого атаковали
                printFieldVGroupsEvent(field_raw,this.action_queue.get(id).idHumanAttack, ANSI_RED_BACKGROUND);
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
                printFieldVGroupsEvent(field_raw,this.action_queue.get(id).idHumanAttack, ANSI_GREEN_BACKGROUND);
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
 
    /* TODO
     * рачет шагов перенести в утилиты по координатам!
    */
    public ArrayList<Point> steps2Range(int[][] field, Point startPoint, Point finishPoint, double range, int speed)
    {
        ArrayList<Point> fullPath2enamy = this.findPath.path(field, startPoint, finishPoint);

        // System.out.printf("fullPath2enamy.size: %d \n", fullPath2enamy.size()); 

        //если пути нет такое было игрок был заблокирован своими и деревом
        if(fullPath2enamy.size() == 0)
            return new ArrayList<Point>(); 
 
        int points = speed;
        int countStep = 0;
        
        //Пока можем шагать или не достигли дистанции атаки
        while(countStep < fullPath2enamy.size() && 
            points >= 0 &&
            range < (fullPath2enamy.get(countStep).distance(finishPoint)+1.0)){

            points--;
            countStep++;
        }
        
        return new ArrayList<Point>(fullPath2enamy.subList(0, countStep));
    }


 
}//public class battlefield 