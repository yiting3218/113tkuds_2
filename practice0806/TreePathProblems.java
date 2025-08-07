package practice0806;

import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 找出從根節點到所有葉節點的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPath(root, new ArrayList<>(), result);
        return result;
    }

    private static void dfsPath(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null)
            return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            dfsPath(node.left, path, result);
            dfsPath(node.right, path, result);
        }
        path.remove(path.size() - 1); // backtrack
    }

    // 2. 判斷是否存在和為目標值的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出最大根到葉的總和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null)
            return 0;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 4. 任意兩節點間的最大路徑和（樹的直徑）
    static int maxPathSum = Integer.MIN_VALUE;

    public static int maxTreePathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        dfsMaxSum(root);
        return maxPathSum;
    }

    private static int dfsMaxSum(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(0, dfsMaxSum(node.left));
        int right = Math.max(0, dfsMaxSum(node.right));
        maxPathSum = Math.max(maxPathSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    // 測試
    public static void main(String[] args) {
        // 建立測試樹：
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("所有根到葉路徑：");
        for (List<Integer> path : allRootToLeafPaths(root)) {
            System.out.println(path);
        }

        int target = 22;
        System.out.println("是否存在和為 " + target + " 的路徑：" + hasPathSum(root, target));

        System.out.println("最大根到葉路徑和：" + maxRootToLeafSum(root));

        System.out.println("樹中最大路徑總和：" + maxTreePathSum(root));
    }
}