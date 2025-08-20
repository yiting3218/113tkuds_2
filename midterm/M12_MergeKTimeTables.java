package midterm;

/*
 * 時間複雜度: O(n log K)
 * 說明：由於每次從 K 條列表中選取最小值並插入 Min-Heap，最壞情況下需要 O(log K) 的時間，遍歷每個時間點共需要 O(n) 的時間，其中 n 是所有列表元素的總和。
 */

import java.util.*;

class TimeEntry {
    int time;
    int listIndex;
    int indexInList;

    TimeEntry(int time, int listIndex, int indexInList) {
        this.time = time;
        this.listIndex = listIndex;
        this.indexInList = indexInList;
    }
}

public class M12_MergeKTimeTables {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int K = scanner.nextInt();

        List<List<Integer>> timeTables = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = scanner.nextInt();
            List<Integer> timeTable = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                timeTable.add(scanner.nextInt());
            }
            timeTables.add(timeTable);
        }

        List<Integer> mergedList = new ArrayList<>();

        PriorityQueue<TimeEntry> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        for (int i = 0; i < K; i++) {
            if (!timeTables.get(i).isEmpty()) {
                minHeap.offer(new TimeEntry(timeTables.get(i).get(0), i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            TimeEntry entry = minHeap.poll();
            mergedList.add(entry.time);

            if (entry.indexInList + 1 < timeTables.get(entry.listIndex).size()) {
                minHeap.offer(new TimeEntry(timeTables.get(entry.listIndex).get(entry.indexInList + 1),
                        entry.listIndex, entry.indexInList + 1));
            }
        }

        for (int time : mergedList) {
            System.out.print(time + " ");
        }

        scanner.close();
    }
}
