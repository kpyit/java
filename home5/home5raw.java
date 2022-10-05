package home5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.awt.Point;
import java.util.Random;

/* http://www.100byte.ru/100btwrks/wv/wv.html 
    Волновой алгоритм применяется при трассеровке плат и ище много где
*/
public class home5raw {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private static final String FIELD = "\u2591";
    private static final String FIELD1 = "\u2592";
    private static final String FIELD2 = "\u2593";
    private static final String FIELD3 = "\u2588";

    private static String SQUARE_START = ANSI_WHITE + FIELD + ANSI_RESET;
    private static String SQUARE_TREE = ANSI_GREEN + ANSI_BLUE_BACKGROUND + FIELD2 + ANSI_RESET;
    private static String SQUARE_FIELD = ANSI_GREEN + ANSI_YELLOW_BACKGROUND + FIELD1 + ANSI_RESET;
    private static String SQUARE_PATH = ANSI_RED + FIELD2 + ANSI_RESET;

    public static void main(String[] params) {

        int rows = 8;
        int columns = 30;

        int[][] obstacle_weights = new int[rows][columns];// вес препятствия и заодно карта местности

        Random rand = new Random();

        // заполняем карту
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                obstacle_weights[i][j] = (int) (rand.nextInt(7) / 6);
            }
        }

        // стартовая и конечная точка
        Point startPoint = new Point((int) rand.nextInt(rows), (int) (rand.nextInt(columns) / 2));
        Point finishPoint = new Point((int) rand.nextInt(rows), (int) (rand.nextInt(columns) / 2 + columns / 2 - 1));

        obstacle_weights[startPoint.x][startPoint.y] = 2;
        obstacle_weights[finishPoint.x][finishPoint.y] = 2;


        WaveAlgoritm test = new WaveAlgoritm(obstacle_weights, startPoint, finishPoint);

        test.find();
        /*
         * System.out.print(ANSI_WHITE + FIELD + ANSI_RESET);//СТАРТ ФИНИШ
         * System.out.print(ANSI_RED + FIELD + ANSI_RESET);//ОПТИМАЛЬНЫЙ ПУТЬ
         * System.out.print(ANSI_GREEN + FIELD + ANSI_RESET);
         * System.out.print(ANSI_GREEN +ANSI_YELLOW_BACKGROUND + FIELD + ANSI_RESET);
         * System.out.print(ANSI_GREEN +ANSI_BLUE_BACKGROUND + FIELD + ANSI_RESET);
         * System.out.print(ANSI_GREEN +ANSI_BLUE_BACKGROUND + FIELD1 + ANSI_RESET);
         * System.out.print(ANSI_GREEN +ANSI_BLUE_BACKGROUND + FIELD2 +
         * ANSI_RESET);//ДЕРЕВЬЯ
         * System.out.print(ANSI_GREEN +ANSI_BLUE_BACKGROUND + FIELD3 + ANSI_RESET);
         * System.out.print(ANSI_GREEN +ANSI_YELLOW_BACKGROUND + FIELD + ANSI_RESET);
         * System.out.print(ANSI_GREEN +ANSI_YELLOW_BACKGROUND + FIELD1 +
         * ANSI_RESET);//ПОЛЕ
         * System.out.print(ANSI_GREEN +ANSI_YELLOW_BACKGROUND + FIELD2 + ANSI_RESET);
         * System.out.print(ANSI_GREEN +ANSI_YELLOW_BACKGROUND + FIELD3 + ANSI_RESET);
         */
    }

    static class WaveAlgoritm {
        private int[][] fieldPlay;// игровое поле
        private int rows;
        private int columns;

        private Point startPoint;
        private Point finishPoint;

        private int[][] path_wieghts;//
        private int[][] calc_path;// расчитанный путь

        private int[][] swap_paths;// точки для обхода
        private int[] swap_paths_count;// количество точек
        private int swap;
        private int weight;

        String[] SQUARES = new String[] { SQUARE_FIELD, SQUARE_TREE, SQUARE_START, SQUARE_PATH };

        int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };// 4 возможных пути

        public WaveAlgoritm(int[][] fieldPlay, Point startPoint, Point finishPoint) {
            this.fieldPlay = fieldPlay;
            this.rows = fieldPlay.length;
            this.columns = fieldPlay[0].length;

            this.path_wieghts = new int[this.rows][this.columns];//
            this.calc_path = new int[this.rows][this.columns];//

            this.swap = 0;// переключатель массивов обхода
            this.swap_paths = new int[2][this.rows * this.columns / 2];//
            this.swap_paths_count = new int[] { 0, 0 };//

            this.startPoint = startPoint;
            this.finishPoint = finishPoint;
        }

        /*
         * логика поиска
         * выбираем тип распостранения волны в 4 стороны или в 8(+4 диагональные)
         * от стартовой позиции
         * фнукция посмотреть соседей(пускай пока 4) если сосед в сетке массива у него
         * еще нет веса(тоесть до него еще никто не добрался) и он не конечный
         * ставим вес от текущей точки и добавляем в массив координат следующего прохода
         * так 4 точки
         * Если в массиве остались точки идем по ним дальше закочился счетчик
         * переключаемся на 2 массив в котором обходим новые точки а заполняем
         * предыдущий массив
         * вес старта 1 чтобы проверку проходил и обратно можно было путь найти до 1 а
         * не до любой 0 точки
         */
        private boolean wave() {
            int x;
            int y;
            int reswap = swap == 0 ? 1 : 0;
            do {
                for (int i = 0; i < this.swap_paths_count[swap]; i++) {
                    for (int[] dir : directions)// обходим 4 направления
                    {
                        x = this.swap_paths[swap][i * 2] + dir[0];
                        y = this.swap_paths[swap][i * 2 + 1] + dir[1];

                        // System.out.println(String.format("%d; %d",x,y));

                        if ((x >= 0 && x < this.rows && y >= 0 && y < this.columns)
                                && (this.path_wieghts[x][y] == 0) // незаполненный вес
                                && (fieldPlay[x][y] != 1)) // не дерево(препятсвие)
                        {
                            this.path_wieghts[x][y] = this.weight + 1;// наращиваем вес
                            if (fieldPlay[x][y] == 2) {
                                return true;// найдена конечная точка
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

        public void find() {
            this.swap_paths[swap][0] = startPoint.x;//
            this.swap_paths[swap][0 + 1] = startPoint.y;//
            this.swap_paths_count[swap] = 1;
            this.path_wieghts[startPoint.x][startPoint.y] = 1;// нулевая точка
            this.weight = 1;

            printFieldPlay();

            if (this.wave()) {
                System.out.println("path_founded");
                this.print_weights();

                this.drawPath();

                printFieldPlay();

            } else {
                System.out.println("path not founded");
                this.print_weights();
            }

        }

        private void print_weights() {

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(String.format("|%2d", path_wieghts[i][j]));
                }
                System.out.println("|");
            }
        }

        private void printFieldPlay() {

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(SQUARES[fieldPlay[i][j]]);
                }
                System.out.println();
            }
        }

        /*
         * из найденной конечной точки
         * берем вес
         * сравниваем с соседями если меньше
         * смотрим не 1 ли вес значит дошли и
         * ставим красный в точку и
         * найденную координату ставим как текущую
         * переходим на пункт берем вес
         */
        public boolean drawPath() {
            int x_current = this.finishPoint.x;
            int y_current = this.finishPoint.y;
            
            int x;
            int y;
            weight = path_wieghts[x_current][y_current];

            System.out.println(String.format("f %d; %d; w = %d |",x_current,y_current,weight));

            do {
                for (int[] dir : directions)// обходим 4 направления
                {
                    x = x_current + dir[0];
                    y = y_current + dir[1];
                    //System.out.print(String.format("%d; %d|",x,y));
                    if ((x >= 0 && x < this.rows && y >= 0 && y < this.columns)
                            && (this.path_wieghts[x][y] < weight) // первый меньший вес
                            && (this.path_wieghts[x][y] != 0) // незаполненный вес
                            && (fieldPlay[x][y] != 1)) // не дерево(препятсвие)
                    {

                        if (fieldPlay[x][y] == 2) {
                            return true;// найдена конечная точка
                        } else {
                            this.weight--;
                            fieldPlay[x][y] = 3;
                            x_current = x;
                            y_current = y;
                            break;
                        }

                    }

                }
            } while (true);
        }
    }
}