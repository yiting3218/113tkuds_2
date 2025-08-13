package practice0812;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicMinHeapPractice {
    private final List<Integer> heap = new ArrayList<>();

    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (heap.isEmpty())
            throw new NoSuchElementException("Heap is empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public int getMin() {
        if (heap.isEmpty())
            throw new NoSuchElementException("Heap is empty");
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i) < heap.get(parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int left = leftChild(i);
            int right = rightChild(i);
            int smallest = i;
            if (left < heap.size() && heap.get(left) < heap.get(smallest))
                smallest = left;
            if (right < heap.size() && heap.get(right) < heap.get(smallest))
                smallest = right;
            if (smallest == i)
                break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    public static void main(String[] args) {
        BasicMinHeapPractice h = new BasicMinHeapPractice();
        int[] input = { 15, 10, 20, 8, 25, 5 };
        int[] expected = { 5, 8, 10, 15, 20, 25 };
        for (int x : input)
            h.insert(x);
        System.out.println("Heap size after inserts: " + h.size());
        System.out.println("Current min (peek): " + h.getMin());
        System.out.println("Extract sequence:");
        boolean ok = true;
        for (int e : expected) {
            int got = h.extractMin();
            System.out.print(got + (e == expected[expected.length - 1] ? "\n" : " "));
            if (got != e)
                ok = false;
        }
        System.out.println(ok ? "Result: OK (matches expected order)" : "Result: MISMATCH");
        System.out.println("Heap empty: " + h.isEmpty());
    }
}
