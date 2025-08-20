
package midterm;

/*
 * 時間複雜度: O(n)
 * 說明：使用自底向上的方式進行堆積化，每次對非葉子節點執行 heapifyDown 操作，最壞情況需要 O(log n) 時間，但由於我們從底部開始執行，總體時間複雜度是 O(n)。
 */

import java.util.Scanner;

public class M01_BuildHeap {
    public static void heapifyDown(int[] heap, int n, int index, boolean isMaxHeap) {
        int largestOrSmallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < n) {
            if (isMaxHeap ? heap[left] > heap[largestOrSmallest] : heap[left] < heap[largestOrSmallest]) {
                largestOrSmallest = left;
            }
        }
        if (right < n) {
            if (isMaxHeap ? heap[right] > heap[largestOrSmallest] : heap[right] < heap[largestOrSmallest]) {
                largestOrSmallest = right;
            }
        }

        if (largestOrSmallest != index) {
            int temp = heap[index];
            heap[index] = heap[largestOrSmallest];
            heap[largestOrSmallest] = temp;

            heapifyDown(heap, n, largestOrSmallest, isMaxHeap);
        }
    }

    public static void buildHeap(int[] heap, int n, boolean isMaxHeap) {
        int start = (n / 2) - 1;
        for (int i = start; i >= 0; i--) {
            heapifyDown(heap, n, i, isMaxHeap);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = scanner.next();
        int n = scanner.nextInt();
        int[] heap = new int[n];

        for (int i = 0; i < n; i++) {
            heap[i] = scanner.nextInt();
        }

        boolean isMaxHeap = type.equals("max");

        buildHeap(heap, n, isMaxHeap);

        for (int i = 0; i < n; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();

        scanner.close();
    }
}
