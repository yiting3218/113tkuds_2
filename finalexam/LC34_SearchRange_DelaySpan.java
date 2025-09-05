package finalexam;

// 題目：延誤等級首末定位
// 在已排序（非遞減）的整數序列中，找出某值 target 的首、末索引；若不存在輸出 -1 -1。
import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        int first = lowerBound(nums, target);
        int last = upperBound(nums, target) - 1;

        if (first == n || first < 0 || nums.length == 0 || nums[first] != target) {
            System.out.println("-1 -1");
        } else {
            System.out.println(first + " " + Math.max(first, last));
        }
        sc.close();
    }

    // 回傳第一個 >= target 的索引（若都小於 target，回 n）
    private static int lowerBound(int[] a, int target) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + ((r - l) >>> 1);
            if (a[m] >= target)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    // 回傳第一個 > target 的索引（若都 <= target，回 n）
    private static int upperBound(int[] a, int target) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + ((r - l) >>> 1);
            if (a[m] > target)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }
}
