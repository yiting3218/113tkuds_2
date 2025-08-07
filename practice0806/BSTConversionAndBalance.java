package practice0806;

import java.util.*;

public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;

        DoublyListNode(int val) {
            this.val = val;
        }
    }

    static DoublyListNode head = null, tail = null;

    public static void treeToDoublyList(TreeNode root) {
        head = tail = null;
        inOrderToDoublyList(root);
    }

    private static void inOrderToDoublyList(TreeNode node) {
        if (node == null)
            return;
        inOrderToDoublyList(node.left);

        DoublyListNode newNode = new DoublyListNode(node.val);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        inOrderToDoublyList(node.right);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);
        return root;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null)
            return 0;
        int left = checkBalance(node.left);
        int right = checkBalance(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }

    public static int getBalanceFactor(TreeNode node) {
        return height(node.left) - height(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    static int sum = 0;

    public static void reverseInorderSum(TreeNode node) {
        if (node == null)
            return;
        reverseInorderSum(node.right);
        sum += node.val;
        node.val = sum;
        reverseInorderSum(node.left);
    }

    public static void inorderPrint(TreeNode node) {
        if (node == null)
            return;
        inorderPrint(node.left);
        System.out.print(node.val + " ");
        inorderPrint(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        System.out.println("1. 原始 BST 的中序：");
        inorderPrint(root);
        System.out.println();

        System.out.println("2. 將 BST 轉換為排序雙向鏈表：");
        treeToDoublyList(root);
        DoublyListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

        System.out.println("3. 陣列轉換為平衡 BST：");
        int[] sorted = { 1, 2, 3, 4, 5, 6, 7 };
        TreeNode balancedRoot = sortedArrayToBST(sorted);
        inorderPrint(balancedRoot);
        System.out.println();

        System.out.println("4. 是否平衡：" + isBalanced(root));
        System.out.println("5. 根節點平衡因子：" + getBalanceFactor(root));

        System.out.println("6. 將每個節點改為所有大於等於它的總和：");
        sum = 0;
        reverseInorderSum(root);
        inorderPrint(root);
        System.out.println();
    }
}
