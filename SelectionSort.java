import java.util.ArrayList;

public class SelectionSort {
    public static void selectionSort(ArrayList<Integer> arr) {
        int swaps = 0;
        int comparisons = 0;

        for (int i = 0; i < arr.size(); i++) {
            int min = i;
            for (int j = i + 1; j < arr.size(); j++) {
                comparisons++;
                if (arr.get(j) < arr.get(min)) {
                    swaps++;
                    min = j;
                }
            }
            int temp = arr.get(i);
            arr.set(i, arr.get(min));
            arr.set(min, temp);
        }

        System.out.println("Swaps: " + swaps);
        System.out.println("Comparisons: " + comparisons);
    }
}
