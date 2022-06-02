import java.util.Arrays;
import java.util.Date;

public class Name {
    private static final int SIZE = 200000;

    public static void main(String[] args) {
        int[] mass = new int[SIZE];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = (int) (Math.random() * SIZE);
        }
        //System.out.println(Arrays.toString(mass));

        standardArraysSort(mass);
        comboSort(mass);
        quickSort(mass);
        countingSort(mass);
        mergeSort(mass);
        mergeSortNR(mass);
        insertSort(mass);
        shuttleSort(mass);
        selectionSort(mass);
        shellaSort(mass);
        bubbleSort(mass);

        //  System.out.println(Arrays.toString(mergeSort(mass)));
//        System.out.println(Arrays.toString(insertSort(mass)));
    }

    //сортировка пузырьковая
    public static int[] standardArraysSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();
       Arrays.sort(mass);
        Date end = new Date();
        System.out.println("Arrays.sort() time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    //сортировка пузырьковая
    public static int[] bubbleSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < mass.length; i++) {
                if (mass[i] < mass[i - 1]) {
                    swap(mass, i, i - 1);
                    needIteration = true;
                }
            }
        }
        Date end = new Date();
        System.out.println("BUBBLE sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    //сортировка выбором
    public static int[] selectionSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();
        for (int before = 0; before < mass.length; before++) {
            int minIndex = before;
            for (int next = before; next < mass.length; next++) {
                if (mass[next] < mass[minIndex]) {
                    minIndex = next;
                }
            }
            swap(mass, before, minIndex);
        }
        Date end = new Date();
        System.out.println("SELECTION sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    //сортировка вставками
    public static int[] insertSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();
        for (int index = 0; index < mass.length; index++) {

            int tempValue = mass[index]; // Значение элемента
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = index - 1;
            //поочередно передвигаем на место tempValue цифры, больше чем tempValue
            //если нашли меньшее - ставим после него tempValue (на место числа, переставленного на предыдущем шаге)
            for (; i >= 0; i--) {
                if (tempValue < mass[i]) {
                    mass[i + 1] = mass[i];
                } else {
                    break;
                }
            }
            mass[i + 1] = tempValue;

        }
        Date end = new Date();
        System.out.println("INSERT sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    //сортировка Челночная
    public static int[] shuttleSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();

        for (int i = 1; i < mass.length; i++) {
            if (mass[i] < mass[i - 1]) {
                swap(mass, i, i - 1);
                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if (mass[z] < mass[z - 1]) {
                        swap(mass, z, z - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        Date end = new Date();
        System.out.println("SHUTTLE sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    //сортировка Шелла
    public static int[] shellaSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();

        int gap = mass.length / 2;
// Пока разница между элементами есть
        while (gap >= 1) {
            for (int right = 0; right < mass.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (mass[c] > mass[c + gap]) {
                        swap(mass, c, c + gap);
                    }
                }
            }
            // Пересчитываем разрыв
            gap = gap / 2;
        }
        Date end = new Date();
        System.out.println("SHELLA sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    //сортировка Слиянием
    public static int[] mergeSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();

        merSort(mass, 0, mass.length - 1);
        Date end = new Date();
        System.out.println("MERGE sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    private static void merSort(int[] numbers, int from, int to) {
        if (from >= to) {
            return;
        }
        int mid = (to + from) / 2;
        merSort(numbers, from, mid);
        merSort(numbers, mid + 1, to);
        merge(numbers, from, mid, to);
    }

    private static void merge(int[] array, int from, int mid, int to) {
        int[] tmp = new int[to - from + 1];
        int i = from, j = mid + 1, k = 0;
        while (i <= mid && j <= to) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= to) {
            tmp[k++] = array[j++];
        }
        // Копируем данные в tmp в массив
        System.arraycopy(tmp, 0, array, from, to - from + 1);
    }


    public static int[] mergeSortNR(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();

        sortMergeIteration(mass);
        Date end = new Date();
        System.out.println("MERGE(non recursive) sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }
    // сортировка слиянием, нерекурсивная реализация (итерация)
    private static void sortMergeIteration(int[] nums) {
        int len = 1; // Длина исходного массива сортировки
        while (len < nums.length) {
            for (int i = 0; i < nums.length; i += len * 2) {
                sortMergeIterationHelper(nums, i, len);
            }
            len *= 2; // Длина массива будет сортироваться каждый раз * 2
        }
    }

    /**
     * Вспомогательная функция
     *
     * @param nums оригинальный массив
     * @парам старт с начальной позиции
     * @param len Длина объединенного массива
     */
    private static void sortMergeIterationHelper(int[] nums, int start, int len) {
        int[] tem = new int[len * 2];
        int i = start;
        int j = start + len;
        int k = 0;
        while (i < start + len && (j < start + len + len && j < nums.length)) {
            tem[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        while (i <start + len && i <nums.length) {// Примечание: здесь я также могу превысить длину
            tem[k++] = nums[i++];
        }
        while (j < start + len + len && j < nums.length) {
            tem[k++] = nums[j++];
        }
        int right = start + len + len;
        int index = 0;
        while (start < nums.length && start < right) {
            nums[start++] = tem[index++];
        }
    }

    //сортировка Counting sort
    public static int[] countingSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();

       radixSort(mass, mass.length);
        Date end = new Date();
        System.out.println("COUNTING sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    private static void radixSort(int arr[], int n)
    {
        int m = getMax(arr, n);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }
    private static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }
    private static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }


    //сортировка QuickSort
    public static int[] quickSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();

        qqSort(mass, 0, mass.length - 1);
        Date end = new Date();
        System.out.println("QUICKSORT sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }

    private static void qqSort(int[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = source[(leftMarker + rightMarker) / 2];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source[leftMarker] < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source[rightMarker] > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            qqSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            qqSort(source, leftBorder, rightMarker);
        }
    }

    private static void swap(int[] mass, int id1, int id2) {
        int temp = mass[id1];
        mass[id1] = mass[id2];
        mass[id2] = temp;
    }

    //сортировка Расческой (CombSort)
    public static int[] comboSort(int[] oldMass) {
        int[] mass = Arrays.copyOf(oldMass, SIZE);
        Date start = new Date();

        int gap = mass.length;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1)
                gap = (int) (gap / 1.247330950103979);

            int i = 0;
            swapped = false;
            while (i + gap < mass.length) {
                if (mass[i] > (mass[i + gap])) {
                    int t = mass[i];
                    mass[i] = mass[i + gap];
                    mass[i + gap] = t;
                    swapped = true;
                }
                i++;
            }
        }
        Date end = new Date();
        System.out.println("COMBOsort sort time: " + (end.getTime() - start.getTime() + " ms"));
        return mass;
    }
}
