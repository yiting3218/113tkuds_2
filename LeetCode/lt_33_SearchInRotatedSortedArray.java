package LeetCode;

// 題目：Search in Rotated Sorted Array
// 給定一個可能經過旋轉的升序（且元素不重複）陣列 nums 與整數 target，請在 O(log n) 時間內回傳 target 的索引；若不存在回傳 -1。
class lt_33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;

            // 判斷哪一半有序
            if (nums[l] <= nums[mid]) { // 左半有序
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1; // 目標在左半
                } else {
                    l = mid + 1; // 目標在右半
                }
            } else { // 右半有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1; // 目標在右半
                } else {
                    r = mid - 1; // 目標在左半
                }
            }
        }
        return -1;
    }
}
/*
 * 解題思路：
 * 1. 以二分搜尋，每次用 nums[l]、nums[mid] 判斷左半或右半是否有序。
 * 2. 若左半有序且 target 在 [nums[l], nums[mid]) 內，縮小至左半，否則去右半。
 * 3. 若右半有序且 target 在 (nums[mid], nums[r]] 內，縮小至右半，否則去左半。
 * 4. 命中回傳索引，搜尋結束仍未命中則回傳 -1。
 * 5. 時間複雜度 O(log n)，空間複雜度 O(1)。
 */
