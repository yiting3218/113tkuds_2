package LeetCode;

// 題目：Search Insert Position
// 給定一個升序且不重複的整數陣列 nums 和一個整數 target，回傳 target 的索引；若不存在則回傳應插入的位置。
class lt_35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid; // 找到目標
            } else if (nums[mid] < target) {
                l = mid + 1; // 去右半邊
            } else {
                r = mid - 1; // 去左半邊
            }
        }
        return l; // 若不存在，返回插入位置
    }
}
/*
 * 解題思路：
 * 1. 使用二分搜尋在升序陣列中尋找 target。
 * 2. 若找到則直接返回索引。
 * 3. 若找不到，則返回最終左指標 l，表示 target 應插入的位置。
 * 4. 時間複雜度 O(log n)，空間複雜度 O(1)。
 */
