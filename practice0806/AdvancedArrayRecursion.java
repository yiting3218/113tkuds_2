package practice0806;

import java.util.Arrays;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static int[] mergeRecursive(int[] a, int[] b) {
        return mergeHelper(a, b, 0, 0);
    }

    private static int[] mergeHelper(int[] a, int[] b, int i, int j) {
        if (i >= a.length) {
            return Arrays.copyOfRange(b, j, b.length);
        }
        if (j >= b.length) {
            return Arrays.copyOfRange(a, i, a.length);
        }

        if (a[i] <= b[j]) {
            int[] merged = mergeHelper(a, b, i + 1, j);
            return prepend(a[i], merged);
        } else {
            int[] merged = mergeHelper(a, b, i, j + 1);
            return prepend(b[j], merged);
        }
    }

    private static int[] prepend(int value, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = value;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    public static int kthSmallest(int[] arr, int k) {
        return quickSelect(arr.clone(), 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right)
            return arr[left];

        int pivotIndex = partition(arr, left, right);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    public static boolean hasSubsetSum(int[] arr, int index, int target) {
        if (target == 0)
            return true;
        if (index >= arr.length)
            return false;

        return hasSubsetSum(arr, index + 1, target - arr[index]) || hasSubsetSum(arr, index + 1, target);
    }

    public static void main(String[] args) {
        int[] nums = { 6, 3, 8, 2, 5, 7, 1 };
        System.out.println("=== 快速排序 ===");
        System.out.println("原始：" + Arrays.toString(nums));
        quickSort(nums, 0, nums.length - 1);
        System.out.println("排序後：" + Arrays.toString(nums));

        System.out.println("\n=== 遞迴合併兩個排序陣列 ===");
        int[] sortedA = { 1, 4, 6 };
        int[] sortedB = { 2, 3, 5, 7 };
        int[] merged = mergeRecursive(sortedA, sortedB);
        System.out.println("合併結果：" + Arrays.toString(merged));

        System.out.println("\n=== 第 k 小元素 ===");
        int[] nums2 = { 7, 4, 6, 3, 9, 1 };
        int k = 3;
        System.out.printf("第 %d 小元素為：%d\n", k, kthSmallest(nums2, k));

        System.out.println("\n=== 是否存在子序列和為目標值 ===");
        int[] nums3 = { 3, 4, 5, 2 };
        int target = 9;
        System.out.printf("是否存在總和為 %d 的子集合：%s\n", target, hasSubsetSum(nums3, 0, target));
    }
}
