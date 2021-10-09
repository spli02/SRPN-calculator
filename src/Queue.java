import java.util.ArrayDeque;
import java.util.Deque;

public class Queue {
    private Deque<Long> queue;

    public Queue() {
        queue = new ArrayDeque<>();
    }

    public void getAllQueue() {
        for (int i = 0; i < getSize(); i++) {
            long num = removeFirst();
            System.out.println(num);
            addNew(num);
        }
    }

    public void addNew(long num) {
        queue.add(num);
    }

    public long removeFirst() {
        return queue.removeFirst();
    }

    public int getSize() {
        return queue.size();
    }

    public long getLast() {
        return queue.getLast();
    }

    public long removeLast() {
        return queue.removeLast();
    }
}
