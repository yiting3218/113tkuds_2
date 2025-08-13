package practice0812;

import java.util.*;

public class MergeKSortedArrays {
    static final class Node {
        final int val;
        final int ai;
        final int ei;

        Node(int val, int ai, int ei) {
            this.val = val;
            this.ai = ai;
            this.ei = ei;
        }
    }

    public static int[] merge(int[][] arrays) {
        if (arrays == null || arrays.length == 0)
            return new int[0];
        int total = 0;
        for (int[] a : arrays)
            if (a != null)
                total += a.length;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length > 0) {
                pq.offer(new Node(arrays[i][0], i, 0));
            }
        }
        int[] res = new int[total];
        int idx = 0;
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            res[idx++] = n.val;
            int ni = n.ei + 1;
            if (ni < arrays[n.ai].length) {
                pq.offer(new Node(arrays[n.ai][ni], n.ai, ni));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] a1 = { { 1, 4, 5 }, { 1, 3, 4 }, { 2, 6 } };
        int[][] a2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] a3 = { { 1 }, { 0 } };
        System.out.println(Arrays.toString(merge(a1)));
        System.out.println(Arrays.toString(merge(a2)));
        System.out.println(Arrays.toString(merge(a3)));
    }
}
