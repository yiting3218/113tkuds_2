package practice0812;

import java.util.*;

public class MovingAverageStream {

    public static class MovingAverage {
        private final int k;
        private long sum = 0;
        private int idx = 0;

        private final Deque<int[]> minQ = new ArrayDeque<>();
        private final Deque<int[]> maxQ = new ArrayDeque<>();
        private final Deque<int[]> window = new ArrayDeque<>();

        private final PriorityQueue<Long> lo = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Long> hi = new PriorityQueue<>();
        private final Map<Long, Integer> delayed = new HashMap<>();
        private int loSize = 0, hiSize = 0;

        public MovingAverage(int size) {
            this.k = size;
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
                Integer c = delayed.get(x);
                if (c == null || c == 0)
                    break;
                delayed.put(x, c - 1);
                heap.poll();
            }
        }

        private void addMedian(long x) {
            if (lo.isEmpty() || x <= lo.peek()) {
                lo.offer(x);
                loSize++;
            } else {
                hi.offer(x);
                hiSize++;
            }
            rebalance();
        }

        private void removeMedian(long x) {
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

        private void pushMin(int v, int i) {
            while (!minQ.isEmpty() && minQ.peekLast()[0] > v)
                minQ.pollLast();
            minQ.offerLast(new int[] { v, i });
        }

        private void pushMax(int v, int i) {
            while (!maxQ.isEmpty() && maxQ.peekLast()[0] < v)
                maxQ.pollLast();
            maxQ.offerLast(new int[] { v, i });
        }

        private void evictExpired(int startIdx) {
            while (!minQ.isEmpty() && minQ.peekFirst()[1] < startIdx)
                minQ.pollFirst();
            while (!maxQ.isEmpty() && maxQ.peekFirst()[1] < startIdx)
                maxQ.pollFirst();
        }

        public double next(int val) {
            sum += val;
            window.offerLast(new int[] { val, idx });
            pushMin(val, idx);
            pushMax(val, idx);
            addMedian(val);
            idx++;
            if (window.size() > k) {
                int[] old = window.pollFirst();
                sum -= old[0];
                removeMedian(old[0]);
            }
            int start = Math.max(0, idx - k);
            evictExpired(start);
            int count = Math.min(idx, k);
            return sum * 1.0 / count;
        }

        public double getMedian() {
            if (window.isEmpty())
                return Double.NaN;
            int count = Math.min(idx, k);
            if ((count & 1) == 1)
                return lo.peek();
            return ((double) lo.peek() + (double) hi.peek()) / 2.0;
        }

        public int getMin() {
            if (window.isEmpty())
                throw new NoSuchElementException();
            int start = Math.max(0, idx - k);
            evictExpired(start);
            return minQ.peekFirst()[0];
        }

        public int getMax() {
            if (window.isEmpty())
                throw new NoSuchElementException();
            int start = Math.max(0, idx - k);
            evictExpired(start);
            return maxQ.peekFirst()[0];
        }
    }

    private static String fmt(double x) {
        if (Double.isNaN(x))
            return "NaN";
        double r = Math.rint(x);
        if (Math.abs(x - r) < 1e-9)
            return String.valueOf((long) r);
        return String.format(java.util.Locale.US, "%.2f", x);
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(fmt(ma.next(1)));
        System.out.println(fmt(ma.next(10)));
        System.out.println(fmt(ma.next(3)));
        System.out.println(fmt(ma.next(5)));
        System.out.println(fmt(ma.getMedian()));
        System.out.println(ma.getMin());
        System.out.println(ma.getMax());
    }
}
