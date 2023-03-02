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
public class drawField extends utilCoords  {

    int heightField;
    int lenghtField;
    int field_raw[][];// просто рандомно заполненный массив как шаблон для row
 
    int field_prebuffer1[][];// поле с текущим окружением но без действий текущего игрока

    int field_buffer1[][];// поле с текущим окружением  
    int field_buffer2[][];// поле с текущим дейтсвием игрока с отрисовкой пользователю с шагом задержки
   
     
    int nextIdGroup = 0;
    int nextId2Step = 0;
    int nextIdMax = 0; 

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
    protected drawField(int height, int width){

        super(height, width);

        this.heightField = height;
        this.lenghtField = width;

        this.field_raw = new int[height][width];// карта местности [rows][columns]
        // Заполняем карту лесом
        this.setMatrixRandBool( field_raw, 0.3);
 
    }  
    /* 
     * 
    */
    protected void printFieldPlay(int[][] field) 
    {

        for (int i = 0; i < this.heightField; i++) {
            for (int j = 0; j < this.lenghtField ; j++) {
                System.out.print(SQUARES[field[i][j]]);
            }
            System.out.println();
        }
    }
      
    /** 
     * печать матрицы позиций существ
     */     
    protected void print_pos_heroes(int[][] pos_heroes) {
        for (int i = 0; i < heightField; i++) {
            for (int j = 0; j < lenghtField; j++) {
                System.out.print(String.format("|%2d", pos_heroes[i][j]));
            }
            System.out.println("|");
        }
    }
    /**
     * печать матрицы растояний между существами
     */
    protected void print_ranges(double[][] matrixRange)
    {
        for (int i = 0; i < nextIdMax; i++) {
            for (int j = 0; j < nextIdMax; j++) {
                System.out.print(String.format("|%4.1f",  matrixRange[i][j]));
            }
            System.out.println("|");
        }
    } 

    /**
     * Поле с группами
     */
    protected void printFieldVGroups(int[][] field, ArrayList<Human> actQueue, int[][] pos_heroes)
    {
        String buffer = "";//Нужно спроектировать буфера
        // field_buffer1 = field_raw.clone();//Поле которое будем наполнять игроками
        for (int i = 0; i < this.heightField; i++) {
            for (int j = 0; j < this.lenghtField ; j++){

                if (pos_heroes[i][j]>-1)//если в клетке существо
                { 
                    if(actQueue.get(pos_heroes[i][j]).hp == 0)//дохлый
                    {
                        buffer += ANSI_RED_BACKGROUND;
                    } else 
                        buffer += GROUP_COLORS[actQueue.get(pos_heroes[i][j]).group];
                    
                    buffer += actQueue.get(pos_heroes[i][j]).Symbol;
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
    
    protected void printFieldVGroupsEvent(int[][] field, ArrayList<Human> actQueue, int[][] pos_heroes, int selectedId, String color)
    {
        String buffer = "";//Нужно спроектировать буфера
        for (int i = 0; i < this.heightField; i++) {
            for (int j = 0; j < this.lenghtField ; j++){
                if (pos_heroes[i][j]>-1)//если в клетке существо
                { 
                    if(selectedId == pos_heroes[i][j])
                        buffer += color;
                    else if(actQueue.get(pos_heroes[i][j]).hp == 0)//дохлый
                    {
                        buffer += ANSI_RED_BACKGROUND;
                    } else 
                        buffer += GROUP_COLORS[actQueue.get(pos_heroes[i][j]).group];
                    
                    buffer += actQueue.get(pos_heroes[i][j]).Symbol;
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
    protected static void sleep()
    {
        try {//страшный способ спать
            // for (int i=0; i<timeToWait ; i++)
                Thread.sleep(200);
        } catch (InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }  
    }

    protected static void sleep100()
    {
        try {//
                Thread.sleep(200);
        } catch (InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }  
    }
     
}//public class battlefield 