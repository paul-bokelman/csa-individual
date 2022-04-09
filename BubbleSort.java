import java.util.ArrayList;

public class BubbleSort{
    
    public static void bubbleSort(ArrayList<Integer> arr) {
        int swaps = 0;
        int comparisons = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size() - 1; j++) {
                comparisons++;
                if (arr.get(j) > arr.get(j + 1)) {
                    swaps++;
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
        System.out.println("Swaps: " + swaps);
        System.out.println("Comparisons: " + comparisons);
    }
}