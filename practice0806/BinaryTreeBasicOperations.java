package practice0806;

import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int data;
        TreeNode left, right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 計算所有節點值總和
     */
    public static int sum(TreeNode root) {
        if (root == null)
            return 0;
        return root.data + sum(root.left) + sum(root.right);
    }

    /**
     * 計算節點數
     */
    public static int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 計算平均值
     */
    public static double average(TreeNode root) {
        int total = sum(root);
        int count = countNodes(root);
        return count == 0 ? 0 : (double) total / count;
    }

    /**
     * 找出最大值
     */
    public static int findMax(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int max = root.data;
        max = Math.max(max, findMax(root.left));
        max = Math.max(max, findMax(root.right));
        return max;
    }

    /**
     * 找出最小值
     */
    public static int findMin(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int min = root.data;
        min = Math.min(min, findMin(root.left));
        min = Math.min(min, findMin(root.right));
        return min;
    }

    /**
     * 計算樹的寬度（最大層節點數）
     */
    public static int getMaxWidth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null)
                    queue.offer(curr.left);
                if (curr.right != null)
                    queue.offer(curr.right);
            }
        }

        return maxWidth;
    }

    /**
     * 判斷是否為完全二元樹（Complete Binary Tree）
     */
    public static boolean isComplete(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                if (mustBeLeaf)
                    return false;
                queue.offer(node.left);
            } else {
                mustBeLeaf = true;
            }

            if (node.right != null) {
                if (mustBeLeaf)
                    return false;
                queue.offer(node.right);
            } else {
                mustBeLeaf = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 建立測試樹
        // 10
        // / \
        // 5 15
        // / \ \
        // 3 7 20

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        System.out.println("=== 二元樹基本操作練習 ===");
        System.out.println("節點總和: " + sum(root));
        System.out.println("平均值: " + average(root));
        System.out.println("最大值: " + findMax(root));
        System.out.println("最小值: " + findMin(root));
        System.out.println("樹的寬度（最大層節點數）: " + getMaxWidth(root));
        System.out.println("是否為完全二元樹: " + isComplete(root));
    }
}
