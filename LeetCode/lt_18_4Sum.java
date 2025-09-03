package LeetCode;

// 題目：4Sum
// 給定一個整數陣列 nums，返回所有的唯一四元組 [nums[a], nums[b], nums[c], nums[d]]，使得 nums[a] + nums[b] + nums[c] + nums[d] == target。

import java.util.*;

public class lt_18_4Sum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return result;

        Arrays.sort(nums); // 排序方便後續使用雙指標方法

        for (int i = 0; i < nums.length - 3; i++) {
            // 跳過重複的元素，避免重複的四元組
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                // 跳過重複的元素，避免重複的四元組
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                int left = j + 1, right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 跳過重複的元素，避免重複的四元組
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0)); // [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0,
                                                                          // 1]]
        System.out.println(fourSum(new int[] { 2, 2, 2, 2, 2 }, 8)); // [[2, 2, 2, 2]]
        System.out.println(fourSum(new int[] { -1, 0, 1, 2, -1, -4 }, 0)); // [[-1, -1, 2, 2], [-1, 0, 0, 1]]
    }
}

/*
 * 解題思路：
 * 1. 題目要求找出和為 target 的所有唯一四元組。
 * 2. 首先對陣列進行排序，這樣便於使用雙指標法來縮小範圍，並避免重複組合。
 * 3. 使用兩個固定的指標 i 和 j，剩餘部分使用兩個指標 left 和 right 來進行搜尋。
 * 4. 使用雙指標法，若和為 target，則加入結果，並跳過重複元素以防止重複四元組。
 * 5. 時間複雜度 O(n^3)，因為有兩層迴圈和雙指標法。
 * 6. 空間複雜度 O(1)，不計結果列表的空間。
 */
