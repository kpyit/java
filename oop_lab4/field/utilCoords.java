package oop_lab4.field;

import oop_lab4.human_classes.Human;

import static oop_lab4.constants.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
 
/**
 * Служебный класс для работы с координатами  
 */
public class utilCoords {

    
    WaveAlg findPath; 
    Random rand = new Random();

    utilCoords(int height,int width)
    {
        this.findPath = new WaveAlg(height,width);
    }


    //Параметризованная функция но не работает с примитивными типама
    protected <T> void setMatrixValue(T[][] array, T value)
    {
        int height = array.length;
        int width = array[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
            {
                array[i][j] = value;
            }
        }

    }
    
    protected void setMatrixValue(int[][] array, int value){

        int height = array.length;
        int width = array[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
            {
                array[i][j] = value;
            }
        }
    }

    protected void setMatrixRandBool(int[][] array, double forestDense){

        int height = array.length;
        int width = array[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // array[i][j] = (int) (rand.nextDouble() * forestDense);//меньше 0,5 то 0
                // array[i][j] = (int) (rand.nextDouble());//меньше 0,5 то 0
                array[i][j] = (int) (rand.nextInt(11)/10);//меньше 0,5 то 0
            }
        } 

    }

    /**
     * расчет матрицы растояний  
      */
    public void ranges_calc_full(double[][]mRange, ArrayList<Human> actQueue)
    {// треугольная чтобы получить растояние надо взять меньший id и посмотреть растояние до большего id
        int size = mRange.length;

        for (int i = 0; i < size; i++){
            for (int j = i; j < size; j++){                
                mRange[i][j] = actQueue.get(i).pos.distance(actQueue.get(j).pos) ;//треугольная
            }
        }
    }
 
    /*
     * Перерасчет растояний для текущего игрока сделавшего ход
     */
    public void ranges_recalc_hero(int id, double[][]mRange, ArrayList<Human> actQueue)
    {
        Point PosCurrentHero =  actQueue.get(id).pos;
        int idMax = actQueue.size();
        //для перерасчета будет использоваться вся строка от id+1 
        for (int j = id+1; j < idMax; j++){
            //растояние до самого себя не пересчитввается
            mRange[j][id] = PosCurrentHero.distance(actQueue.get(j).pos) ;
        }
        // и весь столбец id до id строки- для id меньше текущего 
        for (int j = 0; j < id; j++) {                
            mRange[id][j] = PosCurrentHero.distance(actQueue.get(j).pos) ;
        }
    }
   
    /*  
     * рачет шагов перенести в утилиты по координатам!
    */
    public ArrayList<Point> steps2Range(int[][] field, Point startPoint, Point finishPoint, double range, int speed)
    {
        ArrayList<Point> fullPath2enamy = this.findPath.path(field, startPoint, finishPoint); 
        // System.out.printf("fullPath2enamy.size: %d \n", fullPath2enamy.size()); 

        //если пути нет такое было. игрок был заблокирован своими и деревом
        if(fullPath2enamy.isEmpty())
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
}
