package practice0812;

import java.util.*;

public class KthSmallestElement {

    public static int kthSmallestMaxHeap(int[] arr, int k) {
        if (arr == null || k < 1 || k > arr.length)
            throw new IllegalArgumentException();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : arr) {
            maxHeap.offer(x);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        return maxHeap.peek();
    }

    public static int kthSmallestMinHeap(int[] arr, int k) {
        if (arr == null || k < 1 || k > arr.length)
            throw new IllegalArgumentException();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int x : arr)
            minHeap.offer(x);
        for (int i = 1; i < k; i++)
            minHeap.poll();
        return minHeap.peek();
    }

    private static void runCase(int[] arr, int k) {
        int[] a1 = arr.clone();
        long t1 = System.nanoTime();
        int r1 = kthSmallestMaxHeap(a1, k);
        long d1 = System.nanoTime() - t1;

        int[] a2 = arr.clone();
        long t2 = System.nanoTime();
        int r2 = kthSmallestMinHeap(a2, k);
        long d2 = System.nanoTime() - t2;

        System.out.println("陣列: " + Arrays.toString(arr) + ", K=" + k);
        System.out.println("MaxHeap(大小K) -> " + r1 + " , time(ns)=" + d1);
        System.out.println("MinHeap(提取K次) -> " + r2 + " , time(ns)=" + d2);
        System.out.println();
    }

    public static void main(String[] args) {
        runCase(new int[] { 7, 10, 4, 3, 20, 15 }, 3);
        runCase(new int[] { 1 }, 1);
        runCase(new int[] { 3, 1, 4, 1, 5, 9, 2, 6 }, 4);
    }
}
