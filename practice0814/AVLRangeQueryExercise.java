package practice0814;

import java.util.*;

public class AVLRangeQueryExercise {
    static class Node {
        int key, height;
        Node left, right;

        Node(int k) {
            key = k;
            height = 1;
        }

        int balance() {
            int lh = (left == null ? 0 : left.height);
            int rh = (right == null ? 0 : right.height);
            return lh - rh;
        }

        void update() {
            int lh = (left == null ? 0 : left.height);
            int rh = (right == null ? 0 : right.height);
            height = Math.max(lh, rh) + 1;
        }
    }

    private Node root;

    public void insert(int x) {
        root = insert(root, x);
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> res = new ArrayList<>();
        rangeQuery(root, min, max, res);
        return res;
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private Node insert(Node n, int x) {
        if (n == null)
            return new Node(x);
        if (x < n.key)
            n.left = insert(n.left, x);
        else if (x > n.key)
            n.right = insert(n.right, x);
        else
            return n;
        n.update();
        int bf = n.balance();
        if (bf > 1 && x < n.left.key)
            return rotateRight(n);
        if (bf < -1 && x > n.right.key)
            return rotateLeft(n);
        if (bf > 1 && x > n.left.key) {
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (bf < -1 && x < n.right.key) {
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = (x == null ? null : x.right);
        x.right = y;
        y.left = t2;
        y.update();
        x.update();
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node t2 = (y == null ? null : y.left);
        y.left = x;
        x.right = t2;
        x.update();
        y.update();
        return y;
    }

    private void rangeQuery(Node n, int min, int max, List<Integer> res) {
        if (n == null)
            return;
        if (min < n.key)
            rangeQuery(n.left, min, max, res);
        if (min <= n.key && n.key <= max)
            res.add(n.key);
        if (n.key < max)
            rangeQuery(n.right, min, max, res);
    }

    private void printInOrder(Node n) {
        if (n == null)
            return;
        printInOrder(n.left);
        int bf = (n.left == null ? 0 : n.left.height) - (n.right == null ? 0 : n.right.height);
        System.out.print(n.key + "(平衡因子=" + bf + ") ");
        printInOrder(n.right);
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise t = new AVLRangeQueryExercise();
        int[] a = { 30, 20, 40, 10, 25, 35, 50, 5, 22, 37, 60, 27, 33, 45, 55 };
        System.out.println("=== 建立 AVL 樹 ===");
        for (int v : a)
            t.insert(v);
        System.out.print("中序: ");
        t.printInOrder();

        System.out.println("=== 範圍查詢 ===");
        int[][] qs = { { 1, 15 }, { 21, 27 }, { 26, 45 }, { 33, 60 }, { 41, 100 } };
        for (int[] q : qs) {
            List<Integer> ans = t.rangeQuery(q[0], q[1]);
            System.out.println("範圍 [" + q[0] + ", " + q[1] + "] -> " + ans);
        }
    }
}
