package practice0806;

import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 驗證是否為有效 BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null)
            return true;
        if (node.val <= min || node.val >= max)
            return false;
        return validate(node.left, min, node.val) &&
                validate(node.right, node.val, max);
    }

    // 2. 找出不符合 BST 規則的節點 (中序序列中不是遞增的)
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        inOrderFind(root, new TreeNode[] { null }, result);
        return result;
    }

    private static void inOrderFind(TreeNode node, TreeNode[] prev, List<TreeNode> bad) {
        if (node == null)
            return;
        inOrderFind(node.left, prev, bad);
        if (prev[0] != null && node.val <= prev[0].val) {
            bad.add(prev[0]);
            bad.add(node);
        }
        prev[0] = node;
        inOrderFind(node.right, prev, bad);
    }

    // 3. 修復錯誤的 BST（兩個節點位置交換）
    public static void recoverTree(TreeNode root) {
        TreeNode[] bad = new TreeNode[2];
        TreeNode[] prev = new TreeNode[] { null };
        findTwoSwapped(root, prev, bad);

        if (bad[0] != null && bad[1] != null) {
            int temp = bad[0].val;
            bad[0].val = bad[1].val;
            bad[1].val = temp;
        }
    }

    private static void findTwoSwapped(TreeNode node, TreeNode[] prev, TreeNode[] bad) {
        if (node == null)
            return;
        findTwoSwapped(node.left, prev, bad);
        if (prev[0] != null && node.val < prev[0].val) {
            if (bad[0] == null) {
                bad[0] = prev[0];
                bad[1] = node;
            } else {
                bad[1] = node;
            }
        }
        prev[0] = node;
        findTwoSwapped(node.right, prev, bad);
    }

    // 4. 最少移除幾個節點才能變成 BST（類似 Longest Increasing Subsequence）
    public static int minRemovalsToValidBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        collectInorder(root, inorder);
        int lis = longestIncreasingSubsequence(inorder);
        return inorder.size() - lis;
    }

    private static void collectInorder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        collectInorder(node.left, list);
        list.add(node.val);
        collectInorder(node.right, list);
    }

    private static int longestIncreasingSubsequence(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(dp, num);
            if (idx < 0)
                idx = -idx - 1;
            if (idx == dp.size())
                dp.add(num);
            else
                dp.set(idx, num);
        }
        return dp.size();
    }

    // 測試
    public static void main(String[] args) {
        // 建立一棵錯誤的 BST（2 跟 5 交換了）
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5); // 錯誤
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2); // 錯誤
        root.right.right = new TreeNode(8);

        System.out.println("是否為有效 BST：" + isValidBST(root));

        System.out.println("不合法節點：");
        for (TreeNode node : findInvalidNodes(root)) {
            System.out.print(node.val + " ");
        }

        System.out.println("\n修復 BST...");
        recoverTree(root);
        System.out.println("修復後是否為 BST：" + isValidBST(root));

        System.out.println("最少需移除節點數：" + minRemovalsToValidBST(root));
    }
}
