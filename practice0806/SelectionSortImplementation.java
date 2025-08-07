package practice0806;

import java.util.Arrays;

public class SelectionSortImplementation {

    /**
     * 選擇排序法
     * 時間複雜度：O(n²)
     * 空間複雜度：O(1)
     * 穩定性：不穩定排序
     */
    public static void selectionSort(int[] array) {
        int n = array.length;
        int comparisons = 0;
        int swaps = 0;

        System.out.println("=== 選擇排序過程 ===");

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // 記錄目前最小值的索引

            System.out.printf("\n第 %d 輪：\n", i + 1);

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                System.out.printf("比較 array[%d]=%d 和 array[%d]=%d\n",
                        j, array[j], minIndex, array[minIndex]);

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                // 執行交換
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                swaps++;
                System.out.printf("→ 交換 array[%d]=%d 和 array[%d]=%d\n",
                        i, array[minIndex], minIndex, array[i]);
            } else {
                System.out.println("→ 無需交換，最小值已在正確位置");
            }

            System.out.println("目前陣列狀態：" + Arrays.toString(array));
        }

        System.out.printf("\n排序完成！總比較次數：%d，總交換次數：%d\n", comparisons, swaps);
    }

    public static void main(String[] args) {
        int[] numbers = { 64, 25, 12, 22, 11 };

        System.out.println("原始陣列：" + Arrays.toString(numbers));
        selectionSort(numbers);
        System.out.println("排序結果：" + Arrays.toString(numbers));
    }
}
