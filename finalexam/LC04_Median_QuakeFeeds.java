package finalexam;

// 題目：地震速報雙資料源中位數
// 給定兩個已排序的數列，計算它們聯合集的中位數（不真正合併）。
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] a = new double[n];
        double[] b = new double[m];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextDouble();
        for (int j = 0; j < m; j++)
            b[j] = sc.nextDouble();
        double ans = findMedianSortedArrays(a, b);
        System.out.printf("%.1f\n", ans);
        sc.close();
    }

    public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1); // 保證 nums1 較短
        }
        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int lo = 0, hi = m;
        while (lo <= hi) {
            int i = (lo + hi) / 2;
            int j = totalLeft - i;

            double leftA = (i == 0) ? Double.NEGATIVE_INFINITY : nums1[i - 1];
            double rightA = (i == m) ? Double.POSITIVE_INFINITY : nums1[i];
            double leftB = (j == 0) ? Double.NEGATIVE_INFINITY : nums2[j - 1];
            double rightB = (j == n) ? Double.POSITIVE_INFINITY : nums2[j];

            if (leftA <= rightB && leftB <= rightA) {
                if (((m + n) % 2) == 1) {
                    return Math.max(leftA, leftB); // 奇數長度
                } else {
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0; // 偶數長度
                }
            } else if (leftA > rightB) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("Invalid input");
    }
}
