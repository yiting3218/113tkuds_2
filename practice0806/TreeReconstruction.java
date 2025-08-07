package practice0806;

import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            this.val = v;
        }
    }

    // 1. 前序 + 中序 重建
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int preL, int preR, int[] in, int inL, int inR,
            Map<Integer, Integer> inMap) {
        if (preL > preR)
            return null;
        TreeNode root = new TreeNode(pre[preL]);
        int rootIndex = inMap.get(pre[preL]);
        int leftSize = rootIndex - inL;
        root.left = buildPreIn(pre, preL + 1, preL + leftSize, in, inL, rootIndex - 1, inMap);
        root.right = buildPreIn(pre, preL + leftSize + 1, preR, in, rootIndex + 1, inR, inMap);
        return root;
    }

    // 2. 後序 + 中序 重建
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int postL, int postR, int[] in, int inL, int inR,
            Map<Integer, Integer> inMap) {
        if (postL > postR)
            return null;
        TreeNode root = new TreeNode(post[postR]);
        int rootIndex = inMap.get(post[postR]);
        int leftSize = rootIndex - inL;
        root.left = buildPostIn(post, postL, postL + leftSize - 1, in, inL, rootIndex - 1, inMap);
        root.right = buildPostIn(post, postL + leftSize, postR - 1, in, rootIndex + 1, inR, inMap);
        return root;
    }

    // 3. 層序重建完全二元樹（完全填滿）
    public static TreeNode buildTreeFromLevelOrder(int[] levelOrder) {
        if (levelOrder.length == 0)
            return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();
            if (i < levelOrder.length) {
                current.left = new TreeNode(levelOrder[i++]);
                queue.offer(current.left);
            }
            if (i < levelOrder.length) {
                current.right = new TreeNode(levelOrder[i++]);
                queue.offer(current.right);
            }
        }
        return root;
    }

    // 印出中序驗證
    public static void printInorder(TreeNode node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    // 主程式測試
    public static void main(String[] args) {
        // 測試前序 + 中序重建
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        TreeNode root1 = buildTreePreIn(preorder, inorder);
        System.out.print("前序+中序重建的中序輸出：");
        printInorder(root1);
        System.out.println();

        // 測試後序 + 中序重建
        int[] postorder = { 9, 15, 7, 20, 3 };
        TreeNode root2 = buildTreePostIn(postorder, inorder);
        System.out.print("後序+中序重建的中序輸出：");
        printInorder(root2);
        System.out.println();

        // 測試層序重建
        int[] levelOrder = { 1, 2, 3, 4, 5, 6, 7 };
        TreeNode root3 = buildTreeFromLevelOrder(levelOrder);
        System.out.print("層序重建的中序輸出：");
        printInorder(root3);
        System.out.println();
    }
}