package home4;

import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayList;

public class home4 {

    public static void main(String[] params) {
        int[] arr = { 4, -22, 12, 3, 18, 27, 0, 5, -2, 4, 8 };
        System.out.println("Исходный массив " + Arrays.toString(arr));

        // промежуточная память
        int[] buff = Arrays.copyOf(arr, arr.length);

        // сортируем массив arr, используя вспомогательный массив buff
        mergesort(arr, buff, 0, arr.length - 1);

        System.out.println("Отсортированный массив " + Arrays.toString(arr) + "\n");

        ArrayList<String> expressions = new ArrayList<String>();
        expressions.add("a+(d*3)");
        expressions.add("[a+(1*3)");
        expressions.add("[6+(3*3)]");
        expressions.add("{a}[+]{(d*3)}");
        expressions.add("<{a}+{(d*3)}>");
        expressions.add("{a+]}{(d*3)}");

        String expression = "";

        for (int i = 0; i < expressions.size(); i++) {
            System.out.print("Пример " + (i + 1) + ": " + expressions.get(i) + " - ");

            expression = expressions.get(i).replaceAll("[^\\[\\]\\{\\}\\(\\)<>]", "");

            if (testExpression(expression))
                System.out.println("истина");
            else
                System.out.println("ложь");
        }
    }
    /*
     * Пример 1: a+(d*3) - истина
     * Пример 2: [a+(1*3) - ложь
     * Пример 3: [6+(3*3)] - истина
     * Пример 4: {a}[+]{(d*3)} - истина
     * Пример 5: <{a}+{(d*3)}> - истина
     * Пример 6: {a+]}{(d*3)} - ложь
     */

    public static class myStack<T> {
        private ArrayList<T> list = new ArrayList<>();

        public void push(T item) {
            list.add(0, item);
        }

        public T pop() {
            return list.remove(0);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public boolean contains(T item) {
            return list.contains(item);
        }
    }

    public static boolean testExpression(String expression) {
        String openHooks = "<{[(";
        String closeHooks = ">}])";
        myStack<Character> stackHooks = new myStack<>();

        for (char ch : expression.toCharArray()) {
            if (openHooks.indexOf(ch) != -1) {
                // System.out.println(" stackHooks.push "+ch);
                stackHooks.push(ch);
            } else if (!stackHooks.isEmpty()) {
                // System.out.println("test ch " + ch);
                if (ch != closeHooks.charAt(openHooks.indexOf(stackHooks.pop())))
                    return false;
            } else {
                return false;
            }
        }
        return stackHooks.isEmpty();// стек должен быть пустым в конце
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // Cлияние подмассивов
    public static void merge(int[] arr, int[] buff, int low, int middle, int high) {
        int k = low;
        int i = low;
        int j = middle + 1;

        // Пока есть элементы в обоих сравниваем и заносим в результат
        while (i <= middle && j <= high) {
            if (arr[i] <= arr[j]) {
                buff[k++] = arr[i++];
            } else {
                buff[k++] = arr[j++];
            }
        }

        while (i <= middle) {//
            buff[k++] = arr[i++];// если в 1 массиве были элементы больше чем во 2 дописываем в конец
        }

        // отсотрированный массив переносим обратно
        System.arraycopy(buff, low, arr, low, high - low + 1);
    }

    public static void mergesort(int[] arr, int[] buff, int low, int high) {
        if (high != low) { // выход из рекурсии если дошли до единичного можно доходить но определенного
                           // размера массива и сортировать другим алгоритмом

            int middle = (low + ((high - low) >> 1));// середина
            // продолжаем делить на подмассивы пока не дойдем до 1
            mergesort(arr, buff, low, middle);
            mergesort(arr, buff, middle + 1, high);
            merge(arr, buff, low, middle, high);
        }
    }
}

/*
 * >>>
Исходный массив [4, -22, 12, 3, 18, 27, 0, 5, -2, 4, 8]
Отсортированный массив [-22, -2, 0, 3, 4, 4, 5, 8, 12, 18, 27]

Пример 1: a+(d*3) - истина
Пример 2: [a+(1*3) - ложь
Пример 3: [6+(3*3)] - истина
Пример 4: {a}[+]{(d*3)} - истина
Пример 5: <{a}+{(d*3)}> - истина
Пример 6: {a+]}{(d*3)} - ложь
 */
