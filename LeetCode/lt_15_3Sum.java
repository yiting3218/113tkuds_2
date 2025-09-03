package LeetCode;

// 題目：3Sum
// 給定一個整數陣列 nums，回傳所有的三元組 [nums[i], nums[j], nums[k]]，使得 i != j, i != k, j != k，且 nums[i] + nums[j] + nums[k] == 0。

import java.util.*;

public class lt_15_3Sum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序，便於雙指標

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳過重複的元素
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right]; // 計算三數之和
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 跳過重複元素
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[] { -1, 0, 1, 2, -1, -4 })); // [[-1, -1, 2], [-1, 0, 1]]
        System.out.println(threeSum(new int[] { 0, 1, 1 })); // []
        System.out.println(threeSum(new int[] { 0, 0, 0 })); // [[0, 0, 0]]
    }
}

/*
 * 解題思路：
 * 1. 題目要求找出三個數字之和為 0 的所有三元組。
 * 2. 首先對陣列進行排序，這樣便於使用雙指標方法。
 * 3. 使用第一個指標固定，每次根據剩餘部分使用兩個指標（left 和 right）來搜尋匹配的結果。
 * 4. 避免重複的三元組，通過跳過相同的元素來實現。
 * 5. 時間複雜度 O(n^2)，其中 n 是陣列的長度。排序 O(nlogn)，雙指標 O(n)。
 * 6. 空間複雜度 O(1)，不計結果列表的空間。
 */
