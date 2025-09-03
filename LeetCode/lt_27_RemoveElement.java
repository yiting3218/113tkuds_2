package LeetCode;

// 題目：Remove Element
// 給定一個整數陣列 nums 和一個整數 val，移除所有該元素的出現。

class lt_27_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int k = 0; // 記錄新的陣列長度
        // 遍歷陣列
        for (int i = 0; i < nums.length; i++) {
            // 當遇到不等於 val 的元素，將其移到前面
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        // 返回新的長度
        return k;
    }
}

/*
 * 解題思路：
 * 1. 題目要求移除所有等於 val 的元素，並返回不包含該元素的長度。
 * 2. 使用指標 k 來標記新的長度，並移動不等於 val 的元素到陣列的前面。
 * 3. 時間複雜度 O(n)，其中 n 是陣列長度。只需一次遍歷即可完成。
 * 4. 空間複雜度 O(1)，只使用了常數空間，修改是在原地完成的。
 */
