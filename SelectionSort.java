import java.util.ArrayList;
// finds the smallest element in the array then swaps it with the first element, this process is repeated until the array is sorted from the smallest to the largest element.

// An important note is the temporary variable temp. This variable is used to store the value of the element that is being swapped. This slows down the algorithm, but it is necessary because the algorithm does not know the size of the array.

public class SelectionSort {
    public static void selectionSort(ArrayList<Integer> arr) {
        int swaps = 0;
        int comparisons = 0;

        for (int i = 0; i < arr.size(); i++) {
            int min = i;
            for (int j = i + 1; j < arr.size(); j++) {

                if (arr.get(j) < arr.get(min)) {
                    comparisons++;
                    min = j;
                }
            }
            swaps++;
            int temp = arr.get(i);
            arr.set(i, arr.get(min));
            arr.set(min, temp);
        }

        System.out.println("Swaps: " + swaps);
        System.out.println("Comparisons: " + comparisons);
    }
}
