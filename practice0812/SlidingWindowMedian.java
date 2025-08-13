package practice0812;

import java.util.*;

public class SlidingWindowMedian {

    private final PriorityQueue<Long> lo = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Long> hi = new PriorityQueue<>();
    private final Map<Long, Integer> delayed = new HashMap<>();
    private int loSize = 0, hiSize = 0;

    private void add(long x) {
        if (lo.isEmpty() || x <= lo.peek()) {
            lo.offer(x);
            loSize++;
        } else {
            hi.offer(x);
            hiSize++;
        }
        rebalance();
    }

    private void remove(long x) {
        delayed.put(x, delayed.getOrDefault(x, 0) + 1);
        if (!lo.isEmpty() && x <= lo.peek())
            loSize--;
        else
            hiSize--;
        if (!lo.isEmpty() && Objects.equals(lo.peek(), x))
            prune(lo);
        if (!hi.isEmpty() && Objects.equals(hi.peek(), x))
            prune(hi);
        rebalance();
    }

    private void rebalance() {
        if (loSize > hiSize + 1) {
            hi.offer(lo.poll());
            loSize--;
            hiSize++;
            prune(lo);
        } else if (loSize < hiSize) {
            lo.offer(hi.poll());
            hiSize--;
            loSize++;
            prune(hi);
        }
    }

    private void prune(PriorityQueue<Long> heap) {
        while (!heap.isEmpty()) {
            long x = heap.peek();
            Integer cnt = delayed.get(x);
            if (cnt == null || cnt == 0)
                break;
            delayed.put(x, cnt - 1);
            heap.poll();
        }
    }

    private double median(int k) {
        if ((k & 1) == 1)
            return lo.peek();
        return ((double) lo.peek() + (double) hi.peek()) / 2.0;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k == 0 || n == 0)
            return new double[0];
        double[] ans = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= k - 1) {
                ans[i - k + 1] = median(k);
                remove(nums[i - k + 1]);
            }
        }
        return ans;
    }

    private static String format(double[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            double v = arr[i];
            if (Math.abs(v - Math.rint(v)) < 1e-9)
                sb.append((long) Math.rint(v));
            else
                sb.append(String.valueOf(v));
            if (i + 1 < arr.length)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        System.out.println(format(s.medianSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
        System.out.println(format(s.medianSlidingWindow(new int[] { 1, 2, 3, 4 }, 2)));
    }
}
