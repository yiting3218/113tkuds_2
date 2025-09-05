package LeetCode;

// 題目：Combination Sum II
// 給定含可重複數值的陣列 candidates 與整數 target，找出所有總和等於 target 的「唯一組合」；每個數最多使用一次。
import java.util.*;

class lt_40_CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 先排序，方便剪枝與去重
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) { // 剛好湊到
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > remain)
                break; // 剪枝
            if (i > start && nums[i] == nums[i - 1])
                continue; // 同層去重
            path.add(nums[i]); // 選擇 nums[i]，因為只能用一次所以下一層從 i+1
            dfs(nums, remain - nums[i], i + 1, path, res);
            path.remove(path.size() - 1); // 回溯
        }
    }
}
/*
 * 解題思路：
 * 1. 將陣列排序，方便處理重複元素並做剪枝。
 * 2. 使用回溯法枚舉組合，remain 表示剩餘需要湊的數字。
 * 3. 每個數只能使用一次，因此遞迴時下一層從 i+1 開始。
 * 4. 在同一層中若出現相同數字（i > start 且 nums[i] == nums[i-1]），則跳過避免重複解。
 * 5. 當 remain == 0 時收錄當前路徑；時間複雜度與解的數量相關，空間為遞迴深度 O(n)。
 */
