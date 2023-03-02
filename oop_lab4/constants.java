package oop_lab4;

public final class constants {

    private constants() {
        // restrict instantiation
    }

    //Допустимые вариации действий перснажа на поле боя
    public static final int NOTHFING = 1;//идти в точку
    public static final int GO_TO = 2;//идти в точку
    public static final int GO_TO_RANGE = 3;//идти к точке на дистанцию атаки
    public static final int ATTACK_ENAMY = 4;//атаковать
    public static final int HEALING = 5;//лечить
  

    //псевдографика
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
 

    public static final String FIELD = "\u2591";
    public static final String FIELD1 = "\u2592";
    public static final String FIELD2 = "\u2593";


    public static final String SQUARE_START = ANSI_WHITE + FIELD + ANSI_RESET;
    public static final String SQUARE_TREE = ANSI_GREEN + ANSI_BLUE_BACKGROUND + FIELD2 + ANSI_RESET;
    public static final String SQUARE_FIELD = ANSI_GREEN + ANSI_YELLOW_BACKGROUND + FIELD1 + ANSI_RESET;
    public static final String SQUARE_PATH = ANSI_RED + FIELD2 + ANSI_RESET;

    //цветные метки для поля
    public static final String[] SQUARES = { SQUARE_FIELD, SQUARE_TREE, SQUARE_START, SQUARE_PATH };

    //Позция группы отностительно длинны карты
    public static final double[] POS_SHIFT = {0.6,0.1,0.9,0.7,0.4,0.5};

    //Цвета групп
    public static final String[] GROUP_COLORS = {ANSI_RED_BACKGROUND,ANSI_WHITE_BACKGROUND,ANSI_PURPLE_BACKGROUND,ANSI_CYAN_BACKGROUND};
}
