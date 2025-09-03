package LeetCode;

// 題目：Median of Two Sorted Arrays
// 給定兩個已排序陣列 nums1、nums2，回傳合併後的中位數，時間複雜度需為 O(log(m+n))。

public class lt_04_MedianTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1); // 確保 nums1 較短
        }

        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int lo = 0, hi = m;
        while (lo <= hi) {
            int i = (lo + hi) / 2; // nums1 左半長度
            int j = totalLeft - i; // nums2 左半長度

            int leftA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int rightA = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int leftB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int rightB = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (leftA <= rightB && leftB <= rightA) {
                if (((m + n) & 1) == 1) {
                    return Math.max(leftA, leftB); // 總長奇數
                } else {
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0; // 總長偶數
                }
            } else if (leftA > rightB) {
                hi = i - 1; // i 太大
            } else {
                lo = i + 1; // i 太小
            }
        }
        return 0.0; // 不會觸發
    }

    public static void main(String[] args) {
        int[] a1 = { 1, 3 }, b1 = { 2 };
        System.out.println(findMedianSortedArrays(a1, b1)); // 2.0

        int[] a2 = { 1, 2 }, b2 = { 3, 4 };
        System.out.println(findMedianSortedArrays(a2, b2)); // 2.5

        int[] a3 = { 0, 0 }, b3 = { 0, 0 };
        System.out.println(findMedianSortedArrays(a3, b3)); // 0.0
    }
}

/*
 * 解題思路：
 * 1. 題目要求找出兩個排序陣列合併後的中位數，且時間複雜度需 O(log(m+n))。
 * 2. 採用二分搜尋，在較短陣列上切割，計算另一陣列的切割位置，確保左右兩側數量均衡。
 * 3. 當左半最大值 <= 右半最小值時，找到正確切割點，依奇偶數決定中位數公式。
 * 4. 時間複雜度 O(log(min(m,n)))，空間複雜度 O(1)。
 */
