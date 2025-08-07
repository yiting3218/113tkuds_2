package practice0806;

import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        int size; // 節點總數 (包含自己)
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    // 更新節點大小
    private static int getSize(TreeNode node) {
        return (node == null) ? 0 : node.size;
    }

    private static void updateSize(TreeNode node) {
        if (node != null)
            node.size = 1 + getSize(node.left) + getSize(node.right);
    }

    // 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);
        updateSize(root);
        return root;
    }

    // 刪除節點（標準BST刪除）
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null)
            return null;
        if (val < root.val)
            root.left = delete(root.left, val);
        else if (val > root.val)
            root.right = delete(root.right, val);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            TreeNode min = findMin(root.right);
            root.val = min.val;
            root.right = delete(root.right, min.val);
        }
        updateSize(root);
        return root;
    }

    private static TreeNode findMin(TreeNode root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    // 第 k 小元素（中序走訪第 k 次）
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null)
            throw new IllegalArgumentException("Tree is empty");
        int leftSize = getSize(root.left);
        if (k == leftSize + 1)
            return root.val;
        else if (k <= leftSize)
            return kthSmallest(root.left, k);
        else
            return kthSmallest(root.right, k - leftSize - 1);
    }

    // 第 k 大元素（轉成第 size-k+1 小）
    public static int kthLargest(TreeNode root, int k) {
        int total = getSize(root);
        return kthSmallest(root, total - k + 1);
    }

    // 查找第 k 小 ~ 第 j 小元素
    public static void printKthRange(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inOrderRange(root, result, k, j, new int[] { 0 });
        System.out.println("第 " + k + " 小到第 " + j + " 小的元素: " + result);
    }

    private static void inOrderRange(TreeNode node, List<Integer> list, int k, int j, int[] count) {
        if (node == null || count[0] >= j)
            return;
        inOrderRange(node.left, list, k, j, count);
        count[0]++;
        if (count[0] >= k && count[0] <= j)
            list.add(node.val);
        inOrderRange(node.right, list, k, j, count);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = { 20, 10, 5, 15, 30, 25, 35 };

        for (int v : values) {
            root = insert(root, v);
        }

        System.out.println("第 3 小元素: " + kthSmallest(root, 3)); // 應為 15
        System.out.println("第 2 大元素: " + kthLargest(root, 2)); // 應為 30

        printKthRange(root, 2, 5); // 印出第 2~5 小

        System.out.println("刪除節點 10 後：");
        root = delete(root, 10);
        System.out.println("新的第 3 小元素: " + kthSmallest(root, 3)); // 應該變成 20
    }
}