import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Sorts {
    private final ArrayList<Integer> data = new ArrayList<>();
    private final Duration timeElapsed;

    public Sorts(int size, String sortType) {

        Instant start = Instant.now();
        for (int i = 0; i < size; i++) {
            data.add((int) (Math.random() * (size + 1)));
        }

        switch (sortType) {
            case "1":
                BubbleSort.bubbleSort(data);
                break;
            case "2":
                InsertionSort.insertionSort(data);
                break;
            case "3":
                MergeSort.mergeSort(data);
                break;
            case "4":
                SelectionSort.selectionSort(data);
                break;
            default:
                System.out.println("Invalid sort type");
                break;
        }

        Instant end = Instant.now(); // time capture -- end
        this.timeElapsed = Duration.between(start, end);
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public int getTimeElapsed() {
        return timeElapsed.getNano();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sortOption;
        int sum = 0, time = 0;
        int TIMES = 12;
        int SIZE = 5000;

        while (true) {
            System.out.println(
                    "Calculate Complexity for BubbleSort (1), Insertion Sort (2), MergeSort (3) or SelectionSort (4)?");
            sortOption = scanner.next();
            if (sortOption.equals("1") || sortOption.equals("2") || sortOption.equals("3") || sortOption.equals("4"))
                break;
        }

        for (int i = 0; i < TIMES; i++) {
            Sorts s = new Sorts(SIZE, sortOption);
            for (int j = 0; j < s.getData().size(); j++) {
                sum += s.getData().get(j);
            }
            System.out.println("Average random: " + sum / ((i + 1) * SIZE));
            System.out.println("Nanoseconds: " + s.getTimeElapsed());
            time += s.getTimeElapsed();
        }
        System.out.println("Average random: " + sum / (TIMES * SIZE));
        System.out.println("Total Nanoseconds: " + time);
        System.out.println("Total Seconds: " + time / 1000000000.0);
    }

}