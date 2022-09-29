package home3;

public class home3 {

    public static void main(String[] params)
    { 
        System.out.println("порядок перестановки блинов для ханойской башни для 4 блинов");
        tower_of_hanoi(4,1,2);
        System.out.println("");

        int heap[] = { 45, 1, 15, 21, 4, 42, 6, 12 };
        int n = heap.length;

        System.out.print ("изначальная куча ");
        for (int i = 0; i < n; ++i)
            System.out.print(heap[i] + " ");
        System.out.println();


        HeapSort HS = new HeapSort();
        HS.sort(heap);

        System.out.println("отсотртированная куча ");
        for (int i = 0; i < n; ++i)
            System.out.print(heap[i] + " ");
        System.out.println(); 
       
    }
 
    
    //
    public static void tower_of_hanoi(int n, int i, int j) {
        // условие завершения рекурсии
        if (n == 1) {
            System.out.printf("переместить диск %d c %d на %d \n", n, i, j);
        } else {
            int temp = 6 - i - j;
            // перенос с начальной на временную пирамиду на 1 блин меньше чем в предыдущем
            // случае
            tower_of_hanoi(n - 1, i, temp);
            // перемещаем самый большой диск из оставшихся на целевую башню
            System.out.printf("переместить диск %d c %d на %d \n", n, i, j);
            // с временной на целевую, временная будет меняться то 1 то 3 пирамида по
            // формуле 6-i-j
            tower_of_hanoi(n - 1, temp, j);
        }
    }

    // пирамидальная сортировка
    // https://neerc.ifmo.ru/wiki/index.php?title=Сортировка_кучей
    // https://habr.com/ru/company/otus/blog/460087/
    static class HeapSort {

        public void sort(int heap[]) {
            int n = heap.length;

            // сдигаем корень и проверяем листья на условие
            for (int i = n / 2 - 1; i >= 0; i--)
                shiftHeap(heap, n, i);

            // просеиваием минимальный элемент
            for (int i = n - 1; i >= 0; i--) {
                // Перемещаем текущий корень в конец
                int temp = heap[0];
                heap[0] = heap[i];
                heap[i] = temp;

                System.out.print("shiftHeap in ");
                for (int j = 0; j < i; ++j)
                    System.out.print(heap[j] + " ");
                System.out.println();

                // рекурсия на уменьшенной куче
                shiftHeap(heap, i, 0);
            }
        }

        // обработка поддерева
        void shiftHeap(int heap[], int n, int i) {
            int root = i; // Инициализируем наибольший элемент как корень
            int left = 2 * i + 1; // левый = 2*i + 1
            int right = 2 * i + 2; // правый = 2*i + 2

            // Если левый дочерний элемент меньше корня и не вышли за границу
            if ((left < n) && (heap[left] < heap[root]))
                root = left;

            // Если значение в правол узле меньше корня и левого
            if ((right < n) && (heap[right] < heap[root]))
                root = right;

            // Если в листьях значение меньше свопаем с корнем пока не найдем минимальное
            if (root != i) {

                System.out.print("swap i=" + i + " " + heap[i] + " <> " + heap[root] + "; => ");

                int swap = heap[i];
                heap[i] = heap[root];
                heap[root] = swap;

                System.out.print("shiftHeap req ");
                for (int j = 0; j < n; ++j)
                    System.out.print(heap[j] + " ");
                System.out.println();

                // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
                shiftHeap(heap, n, root);
            }
        }

    }

}

/*
 * >>>
ряд треугольных чисел от 1 до 10  
1 3 6 10 15 21 28 36 45 55 
сумма треугльных чисел до 10 включительно = 220

порядок перестановки блинов для ханойской башни для 4 блинов
переместить диск 1 c 1 на 3 
переместить диск 2 c 1 на 2
переместить диск 1 c 3 на 2
переместить диск 3 c 1 на 3
переместить диск 1 c 2 на 1
переместить диск 2 c 2 на 3
переместить диск 1 c 1 на 3
переместить диск 4 c 1 на 2
переместить диск 1 c 3 на 2 
переместить диск 2 c 3 на 1
переместить диск 1 c 2 на 1
переместить диск 3 c 3 на 2
переместить диск 1 c 1 на 3
переместить диск 2 c 1 на 2
переместить диск 1 c 3 на 2

изначальная куча 45 1 15 21 4 42 6 12 
swap i=3 21 <> 12; => shiftHeap req 45 1 15 12 4 42 6 21
swap i=2 15 <> 6; => shiftHeap req 45 1 6 12 4 42 15 21 
swap i=0 45 <> 1; => shiftHeap req 1 45 6 12 4 42 15 21
swap i=1 45 <> 4; => shiftHeap req 1 4 6 12 45 42 15 21
shiftHeap in 21 4 6 12 45 42 15
swap i=0 21 <> 4; => shiftHeap req 4 21 6 12 45 42 15
swap i=1 21 <> 12; => shiftHeap req 4 12 6 21 45 42 15
shiftHeap in 15 12 6 21 45 42 
swap i=0 15 <> 6; => shiftHeap req 6 12 15 21 45 42
shiftHeap in 42 12 15 21 45
swap i=0 42 <> 12; => shiftHeap req 12 42 15 21 45
swap i=1 42 <> 21; => shiftHeap req 12 21 15 42 45
shiftHeap in 45 21 15 42
swap i=0 45 <> 15; => shiftHeap req 15 21 45 42
shiftHeap in 42 21 45
swap i=0 42 <> 21; => shiftHeap req 21 42 45 
shiftHeap in 45 42
swap i=0 45 <> 42; => shiftHeap req 42 45
shiftHeap in 45
shiftHeap in
отсотртированная куча
45 42 21 15 12 6 4 1
 */