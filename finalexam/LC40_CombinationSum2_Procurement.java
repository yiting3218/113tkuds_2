package finalexam;

// 題目：防災物資組合總和
// 給定可能含重複的候選價格與預算 target，列出所有總和等於 target 的唯一升序組合；每個元素最多使用一次。
import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] cand = new int[n];
        for (int i = 0; i < n; i++)
            cand[i] = sc.nextInt();
        List<List<Integer>> res = combinationSum2(cand, target);
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0)
                    System.out.print(" ");
                System.out.print(comb.get(i));
            }
            System.out.println();
        }
        sc.close();
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 排序以便剪枝與同層去重
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(int[] a, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            if (a[i] > remain)
                break; // 剪枝
            if (i > start && a[i] == a[i - 1])
                continue; // 同層去重，避免重複組合
            path.add(a[i]);
            dfs(a, remain - a[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
