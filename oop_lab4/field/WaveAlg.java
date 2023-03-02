package oop_lab4.field;

// import static oop_lab4.constants.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
    
/* хорошее описание логики алгоритма http://www.100byte.ru/100btwrks/wv/wv.html */
public class WaveAlg {

    private int[][] field;// игровое поле
    private int rows;
    private int columns;

    private Point startPoint;
    private Point finishPoint;

    private int[][] path_wieghts;//
    private int[][] calc_path;// расчитанный путь

    private int[][] swap_paths;// след точки для обхода
    private int[] swap_paths_count;// количество точек
    private int swap;
    private int weight;

    int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };// 4 возможных пути есть еще вариант с 8(ходы
                                                                        // по диагонали но логика общая)

    /**
     * конструктор метода поиска пути
     * @param height высота поля
     * @param width  ширина поля
     */
    public WaveAlg(int height, int width){
        this.rows = height;
        this.columns = width;
         //инициализация внутренних переменных
         this.path_wieghts = new int[this.rows][this.columns];//
         this.calc_path = new int[this.rows][this.columns];//

         this.swap_paths = new int[2][this.rows * this.columns / 2];//  макс длинна пол поля
         this.swap_paths_count = new int[] { 0, 0 };// 
    }

    /*
     * Сбрасывает внутренние переменные перед перерасчетом маршрута
     */
    private void clearIntArrays()
    {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j  < this.columns; j ++) {
                this.path_wieghts[i][j] = 0;
                this.calc_path[i][j] = 0;
            }
        }
        for (int i = 0; i < this.rows * this.columns / 2; i++) {
            this.swap_paths[0][i] = 0;
            this.swap_paths[1][i] = 0;
        }
        this.swap_paths_count[0] = 0;
        this.swap_paths_count[1] = 0;

        this.swap = 0;// переключатель массивов обхода
    }

    public ArrayList<Point> path(int[][] field, Point startPoint, Point finishPoint) 
    {
        this.field = field; 

        //Сброс  
        this.clearIntArrays();
  
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        
        //Cтавим метки на точки
        field[startPoint.x][startPoint.y] = 2;
        field[finishPoint.x][finishPoint.y] = 2;

        boolean pathFinded = wave();
        //System.out.printf("path finded? |%b \n", pathFinded); 

        ArrayList<Point> path;

        if (pathFinded) {
            // this.print_weights();
            path = this.createPathPoints();
            //this.addPath2Field();  путь героя к врагу на карту 
            path.forEach(p -> field[p.x][p.y]=3);//путь героя к врагу на карту  
 
        } else {
            path = new ArrayList<Point>();
        }
       
        return path;
    }
 
    //Полностью заполненый путь атаки апонента
    /**
     * волна до конечной точки можно поменять местами чтобы путь был прямой
     */
    private boolean wave() {
        //инцициализация
        this.swap_paths[swap][0] = this.finishPoint.x;//
        this.swap_paths[swap][0 + 1] = this.finishPoint.y;//
        this.swap_paths_count[swap] = 1;        
        this.path_wieghts[this.finishPoint.x][this.finishPoint.y] = 1;// нулевая точка не дает сразу прийти
        this.weight = 1;

        int x;
        int y;
        int reswap = (swap == 0) ? 1 : 0;

        do {
            for (int i = 0; i < this.swap_paths_count[swap]; i++) {
                for (int[] dir : directions)// обходим 4 направления
                {
                    x = this.swap_paths[swap][i * 2] + dir[0];
                    y = this.swap_paths[swap][i * 2 + 1] + dir[1]; 

                    if ((x >= 0 && x < this.rows && y >= 0 && y < this.columns)//если не вышли за границы поля
                            && (this.path_wieghts[x][y] == 0) // если незаполненный вес
                            && (field[x][y] != 1)) // случилось дерево(препятсвие)
                    {
                        this.path_wieghts[x][y] = this.weight + 1;//
                        if (field[x][y] == 2) {
                            return true;// найдена начальная точка точка
                        }
                        // добавляем в массив обхода
                        this.swap_paths[reswap][this.swap_paths_count[reswap] * 2] = x;
                        this.swap_paths[reswap][this.swap_paths_count[reswap] * 2 + 1] = y;
                        this.swap_paths_count[reswap]++;
                    }
                }
            }

            this.weight++;
            this.swap_paths_count[swap] = 0;
            reswap = swap;
            swap = swap == 0 ? 1 : 0;

        } while (this.swap_paths_count[swap] > 0);

        return false;// точки закончились а финиш так и не найден
    } 

    /*
    *  путь движения
    */    
    public ArrayList<Point> createPathPoints()
    {
        int x_current = this.startPoint.x;
        int y_current = this.startPoint.y;
        int x, y;
        weight = path_wieghts[x_current][y_current];

        ArrayList<Point> path = new ArrayList<>();        
        // System.out.printf("fin{%d, %d}; weidht = %d \n", x_current, y_current, weight); 
        do {
            for (int[] dir : directions)// обходим 4 направления
            {
                x = x_current + dir[0];
                y = y_current + dir[1];
                if ((x >= 0 && x < this.rows && y >= 0 && y < this.columns)//гранцы
                        && (this.path_wieghts[x][y] < weight) // первый меньший вес
                        && (this.path_wieghts[x][y] != 0) // незаполненный вес
                        && (field[x][y] != 1)) // не дерево(препятсвие)
                {
                    if (field[x][y] == 2) {
                        return path;// найдена конечная точка не добавляется в путь
                    } else {
                        this.weight--;
                        path.add(new Point(x,y));
                        x_current = x;
                        y_current = y;
                        break;
                    } 
                } 
            }
        } while (true); 
    }//ArrayList<Point> createPathPoints()

     

    /*
    * отрисовка матрицы волны
    */
    private void print_weights() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(String.format("|%2d", path_wieghts[i][j]));
            }
            System.out.println("|");
        }
    }

    /*
    * Нанесение пути на карту чтобы показать путь движения
    */    
    public boolean addPath2Field() {
        int x_current = this.startPoint.x;
        int y_current = this.startPoint.y;

        int x, y;
        weight = path_wieghts[x_current][y_current];

        System.out.printf("fin{%d, %d}; weidht = %d \n", 
                        x_current, y_current, weight);
 
        do {
            for (int[] dir : directions)// обходим 4 направления
            {
                x = x_current + dir[0];
                y = y_current + dir[1];
                // System.out.print(String.format("%d; %d|",x,y));
                if ((x >= 0 && x < this.rows && y >= 0 && y < this.columns)
                        && (this.path_wieghts[x][y] < weight) // первый меньший вес
                        && (this.path_wieghts[x][y] != 0) // незаполненный вес
                        && (field[x][y] != 1)) // не дерево(препятсвие)
                {

                    if (field[x][y] == 2) {
                        return true;// найдена конечная точка
                    } else {
                        this.weight--;
                        field[x][y] = 3;//цвет найденного пути для текущего персонажа
                        x_current = x;
                        y_current = y;
                        break;
                    } 
                } 
            }
        } while (true);
    }//public boolean addPath2Field()

    
    //отрисовка карты и пути с весами 
    public void findedPath() { 

        this.clearIntArrays();

        if (this.wave()) {
            System.out.println("path_founded");
            this.print_weights();
            this.addPath2Field();  
        } else {
            System.out.println("path not founded");
            this.print_weights();
        }

    }

}//static class WaveAlg