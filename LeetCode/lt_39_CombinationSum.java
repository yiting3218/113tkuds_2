package LeetCode;

// 題目：Combination Sum
// 給定不重複的整數陣列 candidates 與整數 target，找出所有總和等於 target 的「組合」；每個數可被重複選取。
import java.util.*;

class lt_39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 先排序以利剪枝
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
            path.add(nums[i]); // 選 nums[i]，可重複使用所以下一層仍從 i 開始
            dfs(nums, remain - nums[i], i, path, res);
            path.remove(path.size() - 1); // 回溯
        }
    }
}
/*
 * 解題思路：
 * 1. 回溯列舉組合，使用 remain 表示尚需湊的值。
 * 2. 陣列先排序，當 nums[i] > remain 時可直接剪枝跳出。
 * 3. 允許重複選取 → 遞迴時下一層仍從 i 開始；避免排列重複 → 迴圈從 start 向右。
 * 4. 當 remain 為 0 時收錄當前路徑；小於 0 或越界則回溯。
 * 5. 時間複雜度約 O(解的總數 × 平均長度)，空間複雜度 O(目標深度)。
 */