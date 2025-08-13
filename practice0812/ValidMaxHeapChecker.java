package practice0812;

import java.util.Arrays;

public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < n && arr[i] < arr[left])
                return false;
            if (right < n && arr[i] < arr[right])
                return false;
        }
        return true;
    }

    public static int findFirstViolationIndex(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < n && arr[i] < arr[left])
                return left;
            if (right < n && arr[i] < arr[right])
                return right;
        }
        return -1;
    }

    private static void printCheck(int[] arr) {
        boolean ok = isValidMaxHeap(arr);
        if (ok) {
            System.out.println(Arrays.toString(arr) + " -> true");
        } else {
            int v = findFirstViolationIndex(arr);
            int parent = (v - 1) / 2;
            System.out.println(Arrays.toString(arr) + " -> false (索引" + v + "的" + arr[v] + "大於父節點" + arr[parent] + ")");
        }
    }

    public static void main(String[] args) {
        printCheck(new int[] { 100, 90, 80, 70, 60, 75, 65 });
        printCheck(new int[] { 100, 90, 80, 95, 60, 75, 65 });
        printCheck(new int[] { 50 });
        printCheck(new int[] {});
    }
}
