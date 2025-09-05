package LeetCode;

// 題目：Find First and Last Position of Element in Sorted Array
// 給定一個非遞減排序的整數陣列 nums 與整數 target，找出 target 的起始與結束位置；若不存在則回傳 [-1, -1]。
class lt_34_FindFirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[] { first, last };
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int l = 0, r = nums.length - 1, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                res = mid;
                if (isFirst) {
                    r = mid - 1; // 繼續往左找
                } else {
                    l = mid + 1; // 繼續往右找
                }
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }
}
/*
 * 解題思路：
 * 1. 使用二分搜尋兩次，分別找出 target 的最左邊位置與最右邊位置。
 * 2. 若搜尋到目標，持續往左或往右縮小區間，直到無法再更新位置。
 * 3. 若 target 不存在，返回 [-1, -1]。
 * 4. 時間複雜度 O(log n)，空間複雜度 O(1)。
 */
