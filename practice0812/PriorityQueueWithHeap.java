package practice0812;

import java.util.*;

public class PriorityQueueWithHeap {
    static final class Task {
        final String name;
        int priority;
        final long ts;

        Task(String name, int priority, long ts) {
            this.name = name;
            this.priority = priority;
            this.ts = ts;
        }

        public String toString() {
            return name;
        }

        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Task))
                return false;
            Task t = (Task) o;
            return Objects.equals(name, t.name);
        }

        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private final Map<String, Task> map = new HashMap<>();
    private final PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
        if (a.priority != b.priority)
            return Integer.compare(b.priority, a.priority);
        int c = Long.compare(a.ts, b.ts);
        if (c != 0)
            return c;
        return a.name.compareTo(b.name);
    });
    private long counter = 0L;

    public void addTask(String name, int priority) {
        Task t = map.get(name);
        if (t != null) {
            changePriority(name, priority);
            return;
        }
        t = new Task(name, priority, counter++);
        map.put(name, t);
        pq.offer(t);
    }

    public String executeNext() {
        Task t = pq.poll();
        if (t == null)
            return null;
        map.remove(t.name);
        return t.name;
    }

    public String peek() {
        Task t = pq.peek();
        return t == null ? null : t.name;
    }

    public boolean changePriority(String name, int newPriority) {
        Task t = map.get(name);
        if (t == null)
            return false;
        pq.remove(t);
        t.priority = newPriority;
        pq.offer(t);
        return true;
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份", 1);
        q.addTask("緊急修復", 5);
        q.addTask("更新", 3);
        List<String> order = new ArrayList<>();
        for (String s = q.executeNext(); s != null; s = q.executeNext())
            order.add(s);
        System.out.println(String.join(" → ", order));
    }
}
