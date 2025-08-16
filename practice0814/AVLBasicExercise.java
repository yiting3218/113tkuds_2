package practice0814;

public class AVLBasicExercise {
    static class Node {
        int key, height;
        Node left, right;

        Node(int k) {
            key = k;
            height = 1;
        }

        int balance() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            return lh - rh;
        }

        void update() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            height = Math.max(lh, rh) + 1;
        }
    }

    private Node root;

    public void insert(int x) {
        root = insert(root, x);
    }

    public boolean search(int x) {
        return search(root, x);
    }

    public int height() {
        return height(root);
    }

    public boolean isValidAVL() {
        return checkAVL(root) != -1;
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
        Node t2 = (x == null) ? null : x.right;
        x.right = y;
        y.left = t2;
        y.update();
        x.update();
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node t2 = (y == null) ? null : y.left;
        y.left = x;
        x.right = t2;
        x.update();
        y.update();
        return y;
    }

    private boolean search(Node n, int x) {
        if (n == null)
            return false;
        if (x == n.key)
            return true;
        return x < n.key ? search(n.left, x) : search(n.right, x);
    }

    private int checkAVL(Node n) {
        if (n == null)
            return 0;
        int lh = checkAVL(n.left);
        if (lh == -1)
            return -1;
        int rh = checkAVL(n.right);
        if (rh == -1)
            return -1;
        if (Math.abs(lh - rh) > 1)
            return -1;
        return Math.max(lh, rh) + 1;
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
        AVLBasicExercise t = new AVLBasicExercise();
        int[] a = { 10, 20, 30, 40, 50, 25 };
        System.out.println("=== AVL 樹基本操作測試 ===");
        for (int v : a) {
            System.out.println("插入: " + v);
            t.insert(v);
            System.out.print("中序: ");
            t.printInOrder();
            System.out.println("高度: " + t.height());
            System.out.println("有效AVL: " + t.isValidAVL());
            System.out.println();
        }
        System.out.println("搜尋 25: " + t.search(25));
        System.out.println("搜尋 35: " + t.search(35));
        System.out.println("最終高度: " + t.height());
        System.out.println("最終有效AVL: " + t.isValidAVL());
    }
}
