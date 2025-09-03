package LeetCode;

// 題目：Remove Duplicates from Sorted Array
// 給定一個排序過的整數陣列 nums，移除陣列中重複的元素，使每個元素只出現一次。

class lt_26_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 設定指標 k，從索引 1 開始
        int k = 1;

        // 遍歷整個陣列
        for (int i = 1; i < nums.length; i++) {
            // 當當前元素不等於前一個元素時，將其放入陣列的前面
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }

        // 返回新的陣列長度
        return k;
    }
}

/*
 * 解題思路：
 * 1. 題目要求移除重複的元素，只保留不重複的元素，並且保持原始的順序。
 * 2. 透過雙指標法，將不重複的元素放到前面。
 * 3. 時間複雜度 O(n)，其中 n 是陣列的長度。只需一次遍歷即可完成。
 * 4. 空間複雜度 O(1)，只使用了常數空間，修改是在原地完成的。
 */
