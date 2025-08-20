package midterm;

/*
 * 時間複雜度: O(n log n)
 * 說明：堆積排序的時間複雜度為 O(n log n)，其中 n 是數據的大小。建堆需要 O(n)，每次取出最大/最小元素需要 O(log n)，因此總複雜度是 O(n log n)。
 */

import java.util.*;

class ScoreWithIndex {
    int score;
    int index;

    ScoreWithIndex(int score, int index) {
        this.score = score;
        this.index = index;
    }
}

public class M11_HeapSortWithTie {

    public static class ScoreComparator implements Comparator<ScoreWithIndex> {
        @Override
        public int compare(ScoreWithIndex a, ScoreWithIndex b) {
            if (a.score == b.score) {
                return Integer.compare(a.index, b.index);
            }
            return Integer.compare(a.score, b.score);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        PriorityQueue<ScoreWithIndex> minHeap = new PriorityQueue<>(new ScoreComparator());

        for (int i = 0; i < n; i++) {
            int score = scanner.nextInt();
            minHeap.offer(new ScoreWithIndex(score, i));
        }

        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll().score + " ");
        }

        scanner.close();
    }
}
