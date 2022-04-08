import java.util.Iterator;

class Queue<T> implements Iterable<T> {
    LinkedList<T> head, tail;

    public void add(T data) {
        LinkedList<T> tail = new LinkedList<>(data, null);
        if (head == null)
            this.head = this.tail = tail;
        else {
            this.tail.setNextNode(tail);
            this.tail = tail;
        }
    }

    public void delete() {
        head = head.getNext();
    }

    public LinkedList<T> getHead() {
        return this.head;
    }

    public LinkedList<T> getTail() {
        return this.tail;
    }

    /**
     * Returns the iterator object.
     *
     * @return this, instance of object
     */
    public Iterator<T> iterator() {
        return new QueueIterator<>(this);
    }
}

class QueueIterator<T> implements Iterator<T> {
    LinkedList<T> current;

    public QueueIterator(Queue<T> q) {
        current = q.getHead();
    }

    public boolean hasNext() {
        return current != null;
    }

    public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
    }
}

class QueueManager<T> {
    private final String name;
    private int count = 0;
    public final Queue<T> queue = new Queue<>();

    public QueueManager(String name) {
        this.name = name;
    }

    public QueueManager(String name, T[]... seriesOfObjects) {
        this.name = name;
        this.addList(seriesOfObjects);
    }

    public void addList(T[]... seriesOfObjects) {
        for (T[] objects : seriesOfObjects)
            for (T data : objects) {
                this.queue.add(data);
                this.count++;
            }
    }

    public void add(T data) {
        this.queue.add(data);
        this.count++;
    }

    public void delete() {
        this.queue.delete();
        this.count--;
    }

    public void printQueue() {
        System.out.println(this.name + " count: " + count);
        System.out.print(this.name + " data: ");
        for (T data : queue)
            System.out.print(data + " ");
        System.out.println();
    }
}

class QueueTester {
    public static void main(String[] args) {
        Object[] words = new String[] { "seven", "slimy", "snakes", "sallying", "slowly", "slithered", "southward" };

        QueueManager qWords = new QueueManager("Words");
        for (Object word : words) {
            qWords.add(word);
            qWords.printQueue();
        }
        for (Object word : words) {
            qWords.delete();
            qWords.printQueue();
        }

        Object[] numbers = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        QueueManager qNums = new QueueManager("Integers", numbers);
        qNums.printQueue();
    }
}