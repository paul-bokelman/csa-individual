
import java.util.ArrayList;

public class InsertionSort {
    public static void insertionSort(ArrayList<Integer> arr) {
        int swaps = 0;
        int comparisons = 0;

        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            int key = arr.get(i);
            int j = i - 1;
            comparisons++;
            while (j >= 0 && arr.get(j) > key) {
                swaps++;
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }

        System.out.println("Swaps: " + swaps);
        System.out.println("Comparisons: " + comparisons);

    }
}