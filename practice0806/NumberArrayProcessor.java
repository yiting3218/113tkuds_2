package practice0806;

import java.util.*;

public class NumberArrayProcessor {

    public static void main(String[] args) {
        int[] array1 = { 5, 2, 8, 2, 9, 5, 1, 8 };
        int[] array2 = { 1, 3, 5, 7 };
        int[] array3 = { 2, 4, 6, 8 };

        System.out.println("=== 原始陣列1 ===");
        System.out.println(Arrays.toString(array1));

        // 移除重複元素
        int[] noDuplicates = removeDuplicates(array1);
        System.out.println("\n移除重複元素後：");
        System.out.println(Arrays.toString(noDuplicates));

        // 合併兩個已排序陣列
        int[] mergedSorted = mergeSortedArrays(array2, array3);
        System.out.println("\n合併已排序陣列：");
        System.out.println("陣列2：" + Arrays.toString(array2));
        System.out.println("陣列3：" + Arrays.toString(array3));
        System.out.println("合併結果：" + Arrays.toString(mergedSorted));

        // 找出出現頻率最高的元素
        int mode = findMostFrequentElement(array1);
        System.out.println("\n出現頻率最高的元素：" + mode);

        // 分割陣列
        System.out.println("\n將陣列平均分割為兩部分：");
        int[][] split = splitArray(array1);
        System.out.println("前半部分：" + Arrays.toString(split[0]));
        System.out.println("後半部分：" + Arrays.toString(split[1]));
    }

    // 1. 移除重複元素（保留順序）
    public static int[] removeDuplicates(int[] array) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 2. 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) {
            result[k++] = a[i++];
        }

        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }

    // 3. 找出出現頻率最高的元素
    public static int findMostFrequentElement(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        int mode = array[0];

        for (int num : array) {
            int count = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, count);

            if (count > maxFreq) {
                maxFreq = count;
                mode = num;
            }
        }

        return mode;
    }

    // 4. 將陣列平均分割為兩個子陣列
    public static int[][] splitArray(int[] array) {
        int mid = array.length / 2;
        int[][] result = new int[2][];

        result[0] = Arrays.copyOfRange(array, 0, mid);
        result[1] = Arrays.copyOfRange(array, mid, array.length);

        return result;
    }
}
