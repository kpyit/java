package home5;

import java.awt.Point;
import java.util.Random;

/* хорошее описание логики алгоритма http://www.100byte.ru/100btwrks/wv/wv.html  */
public class home5 {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String FIELD = "\u2591";
    private static final String FIELD1 = "\u2592";
    private static final String FIELD2 = "\u2593";

    private static String SQUARE_START = ANSI_WHITE + FIELD + ANSI_RESET;
    private static String SQUARE_TREE = ANSI_GREEN + ANSI_BLUE_BACKGROUND + FIELD2 + ANSI_RESET;
    private static String SQUARE_FIELD = ANSI_GREEN + ANSI_YELLOW_BACKGROUND + FIELD1 + ANSI_RESET;
    private static String SQUARE_PATH = ANSI_RED + FIELD2 + ANSI_RESET;

    public static void main(String[] params) {

        int rows = 8;
        int columns = 30;
        int[][] obstacleWeights = new int[rows][columns];// карта местности

        Random rand = new Random();

        // заполняем карту
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                obstacleWeights[i][j] = (int) (rand.nextInt(7) / 6);
            }
        }

        // стартовая и конечная точка
        Point startPoint = new Point((int) rand.nextInt(rows), (int) (rand.nextInt(columns) / 2));
        Point finishPoint = new Point((int) rand.nextInt(rows), (int) (rand.nextInt(columns) / 2 + columns / 2 - 1));

        obstacleWeights[startPoint.x][startPoint.y] = 2;
        obstacleWeights[finishPoint.x][finishPoint.y] = 2;

        WaveAlgoritm test = new WaveAlgoritm(obstacleWeights, startPoint, finishPoint);
        test.findPath();

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

        int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };// 4 возможных пути есть еще вариант с 8(ходы
                                                                          // по диагонали но логика общая)

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

                        if ((x >= 0 && x < this.rows && y >= 0 && y < this.columns)
                                && (this.path_wieghts[x][y] == 0) // незаполненный вес
                                && (fieldPlay[x][y] != 1)) // дерево(препятсвие)
                        {
                            this.path_wieghts[x][y] = this.weight + 1;//
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

        public void findPath() {
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

        public boolean drawPath() {
            int x_current = this.finishPoint.x;
            int y_current = this.finishPoint.y;

            int x;
            int y;
            weight = path_wieghts[x_current][y_current];

            System.out.println(String.format("f %d; %d; w = %d |", x_current, y_current, weight));

            do {
                for (int[] dir : directions)// обходим 4 направления
                {
                    x = x_current + dir[0];
                    y = y_current + dir[1];
                    // System.out.print(String.format("%d; %d|",x,y));
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