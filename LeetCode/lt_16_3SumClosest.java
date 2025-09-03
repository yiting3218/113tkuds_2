package LeetCode;

// 題目：3Sum Closest
// 給定一個整數陣列 nums 和一個整數 target，找出三個整數，使得它們的和最接近 target。

import java.util.Arrays;

public class lt_16_3SumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 排序以便使用雙指標法

        int closestSum = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重複計算相同的元素
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 更新最接近的和
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                // 根據和與 target 的大小調整指標
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // 找到精確匹配的和
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[] { -1, 2, 1, -4 }, 1)); // 2
        System.out.println(threeSumClosest(new int[] { 0, 0, 0 }, 1)); // 0
        System.out.println(threeSumClosest(new int[] { -1, 0, 1, 1 }, 0)); // 1
    }
}

/*
 * 解題思路：
 * 1. 題目要求找出三個數字，使其和最接近 target。
 * 2. 首先對陣列進行排序，這樣便於使用雙指標方法來遍歷陣列。
 * 3. 使用三個指標，其中一個固定，另外兩個指標在剩餘部分進行查找，計算每次的和，並記錄與 target 最接近的結果。
 * 4. 時間複雜度 O(n^2)，因為每次遍歷一次並用雙指標查找，空間複雜度 O(1)。
 */
