// works by removing all the elements from the queue and pushing them to a stack. Then it pops out all the elements from the stack and push them back to the queue. The queue is now reversed.

import java.util.*;

public class ReverseQueue {

    public static Queue<Integer> reverse(Queue<Integer> queue) {
        Queue<Integer> reversed = new Queue<Integer>();
        Stack<Integer> temp = new Stack<Integer>();

        while (queue.getHead() != null) {
            temp.add(queue.getHead().getData());
            queue.delete();
        }

        while (!temp.empty()) {
            reversed.add(temp.pop());
        }

        return reversed;
    }

    public static void main(String[] args) {
        // put code here
        Queue<Integer> queue = new Queue<Integer>();
        Object[] numbers = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (Object num : numbers) {
            queue.add((Integer) num);
        }

        System.out.print("Original Queue: ");
        for (Object num : queue) {
            System.out.print(num + " ");
        }

        System.out.print("\nReversed: ");
        for (Object num : reverse(queue)) {
            System.out.print(num + " ");
        }

    }
}