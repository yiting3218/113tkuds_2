package midterm;

/*
 * 時間複雜度: O(n)
 * 說明：遞迴遍歷每個節點檢查 BST 和 AVL 條件。每個節點最多被訪問一次，因此時間複雜度是 O(n)，其中 n 是樹的節點數。
 */

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

public class M09_AVLValidate {

    public static boolean isBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.data <= min || node.data >= max) {
            return false;
        }

        return isBST(node.left, min, node.data) && isBST(node.right, node.data, max);
    }

    public static int isAVL(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = isAVL(node.left);
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        int rightHeight = isAVL(node.right);
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return Integer.MIN_VALUE;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static Node buildTree(int[] arr) {
        if (arr.length == 0)
            return null;

        Node root = new Node(arr[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (i < arr.length) {
            Node current = queue.poll();
            if (arr[i] != -1) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        Node root = buildTree(arr);

        if (!isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (isAVL(root) == Integer.MIN_VALUE) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }

        scanner.close();
    }
}
