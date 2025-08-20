package midterm;

/*
 * 時間複雜度: O(n log K)
 * 說明：維護大小為 K 的 Min-Heap，每次插入或調整堆積需要 O(log K) 的時間，總共需要處理 n 個元素，因此總時間複雜度是 O(n log K)。
 */

import java.util.*;

public class M03_TopKConvenience {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();

        PriorityQueue<Map.Entry<Integer, String>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getKey().equals(b.getKey()) ? a.getValue().compareTo(b.getValue())
                        : a.getKey() - b.getKey());

        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int qty = scanner.nextInt();
            Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<>(qty, name);

            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<Map.Entry<Integer, String>> resultList = new ArrayList<>(minHeap);
        resultList.sort((a, b) -> b.getKey() - a.getKey());

        for (Map.Entry<Integer, String> entry : resultList) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }

        scanner.close();
    }
}
