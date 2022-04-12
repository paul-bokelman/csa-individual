// uses queue class and merges two queues together in a sorted result

public class QueueMerge {

    public static Queue<Integer> merge(Queue<Integer> one, Queue<Integer> two) {
        Queue<Integer> mergedQueue = new Queue<Integer>();

        while (one.getHead() != null && two.getHead() != null) {
            Integer a = one.getHead().getData();

            Integer b = two.getHead().getData();
            if (a < b) {
                mergedQueue.add(a);
                one.delete();
            } else {
                mergedQueue.add(b);
                two.delete();
            }
        }

        while (one.getHead() != null && two.getHead() == null) {
            Integer a = one.getHead().getData();
            mergedQueue.add(a);
            one.delete();
        }

        while (one.getHead() == null && two.getHead() != null) {
            Integer b = two.getHead().getData();
            mergedQueue.add(b);
            two.delete();
        }

        return mergedQueue;
    }

    public static void main(String[] args) {
        Object[] numbers1 = new Integer[] { 1, 4, 5, 8 };
        Queue<Integer> q1 = new Queue<Integer>();
        System.out.println("queue 1:");
        for (Object num : numbers1) {
            q1.add((Integer) num);
            System.out.print(num + " ");
        }

        Object[] numbers2 = new Integer[] { 2, 3, 6, 7 };
        Queue<Integer> q2 = new Queue<Integer>();
        System.out.println("\n\nqueue 2:");
        for (Object num : numbers2) {
            q2.add((Integer) num);
            System.out.print(num + " ");
        }

        Queue<Integer> abc = merge(q1, q2);
        System.out.println("\n\nmerged queue:");
        for (Object num : abc) {
            System.out.print(num + " ");
        }
    }
}