package LeetCode;

// 題目：Next Permutation
// 給定一個整數陣列 nums，找到其字典序中的下一個排列；若已是最後一個排列則轉為最小升序。
class lt_31_NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 從右往左找到第一個遞增的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n - 1;
            // 從右往左找到第一個比 nums[i] 大的元素
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // 將 i 之後的區間反轉
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
/*
 * 解題思路：
 * 1. 從右往左找到第一個遞增位置 i。
 * 2. 從右往左找到第一個比 nums[i] 大的位置 j 並交換。
 * 3. 將 i 之後的序列反轉，得到最小的下一個排列。
 */
