package LeetCode;

import java.util.HashMap;

// 題目：Two Sum
// 給定一個整數陣列 nums 和一個目標值 target，請回傳兩個索引，使得 nums[i] + nums[j] == target。

public class lt_01_TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        // 使用 HashMap 來儲存「數值 -> 索引」
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍歷整個陣列
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 計算出需要的另一個數字

            // 如果 map 中已經有 complement，表示找到一組解答
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            // 否則就把當前數字與索引存進 map
            map.put(nums[i], i);
        }

        // 題目保證一定有解，所以不會走到這裡
        throw new IllegalArgumentException("No solution found");
    }

    public static void main(String[] args) {
        // 測試案例 1
        int[] nums1 = { 2, 7, 11, 15 };
        int target1 = 9;
        int[] ans1 = twoSum(nums1, target1);
        System.out.println("[" + ans1[0] + "," + ans1[1] + "]");

        // 測試案例 2
        int[] nums2 = { 3, 2, 4 };
        int target2 = 6;
        int[] ans2 = twoSum(nums2, target2);
        System.out.println("[" + ans2[0] + "," + ans2[1] + "]");

        // 測試案例 3
        int[] nums3 = { 3, 3 };
        int target3 = 6;
        int[] ans3 = twoSum(nums3, target3);
        System.out.println("[" + ans3[0] + "," + ans3[1] + "]");
    }
}

/*
 * 解題思路：
 * 1. 題目要求找到兩個數字相加等於 target。
 * 2. 使用 HashMap 儲存「數值 → 索引」，查找是否有另一個數值能與當前數字配對。
 * 3. 時間複雜度 O(n)，只需一次迴圈即可完成。
 */
