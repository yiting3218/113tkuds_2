package finalexam;

// 題目：防災物資組合總和
// 給定候選價格與預算 target，列出所有總和等於 target 的升序組合；同一價格可重複使用。
import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] cand = new int[n];
        for (int i = 0; i < n; i++)
            cand[i] = sc.nextInt();
        List<List<Integer>> res = combinationSum(cand, target);
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

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 先排序以利剪枝與輸出遞增
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(int[] a, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) { // 剛好湊到
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            if (a[i] > remain)
                break; // 剪枝
            path.add(a[i]); // 選 a[i]
            dfs(a, remain - a[i], i, path, res); // I 版：可重複使用，下一層仍從 i
            path.remove(path.size() - 1); // 回溯
        }
    }
}
