package practice0814;

import java.util.*;

public class PersistentAVLExercise {
    static final class Node {
        final int key;
        final Node left, right;
        final int height;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            int lh = (left == null ? 0 : left.height);
            int rh = (right == null ? 0 : right.height);
            this.height = Math.max(lh, rh) + 1;
        }

        int balance() {
            int lh = (left == null ? 0 : left.height);
            int rh = (right == null ? 0 : right.height);
            return lh - rh;
        }
    }

    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null);
    }

    public int insert(int ver, int x) {
        Node root = getRoot(ver);
        Node nr = insertNode(root, x);
        versions.add(nr);
        return versions.size() - 1;
    }

    public boolean search(int ver, int x) {
        Node n = getRoot(ver);
        while (n != null) {
            if (x == n.key)
                return true;
            n = (x < n.key) ? n.left : n.right;
        }
        return false;
    }

    public int height(int ver) {
        Node r = getRoot(ver);
        return r == null ? 0 : r.height;
    }

    public boolean isValidAVL(int ver) {
        return checkAVL(getRoot(ver)) != -1;
    }

    public int versionsCount() {
        return versions.size();
    }

    public void printInOrder(int ver) {
        printInOrder(getRoot(ver));
        System.out.println();
    }

    public void printInOrderWithBF(int ver) {
        printInOrderWithBF(getRoot(ver));
        System.out.println();
    }

    private Node getRoot(int ver) {
        if (ver < 0 || ver >= versions.size())
            return null;
        return versions.get(ver);
    }

    private Node insertNode(Node n, int x) {
        if (n == null)
            return new Node(x, null, null);
        if (x < n.key) {
            Node nl = insertNode(n.left, x);
            Node cur = new Node(n.key, nl, n.right);
            return rebalance(cur, x);
        } else if (x > n.key) {
            Node nr = insertNode(n.right, x);
            Node cur = new Node(n.key, n.left, nr);
            return rebalance(cur, x);
        } else {
            return n;
        }
    }

    private Node rebalance(Node n, int insertedKey) {
        int bf = n.balance();
        if (bf > 1 && insertedKey < n.left.key)
            return rotateRight(n);
        if (bf < -1 && insertedKey > n.right.key)
            return rotateLeft(n);
        if (bf > 1 && insertedKey > n.left.key)
            return rotateRight(new Node(n.key, rotateLeft(n.left), n.right));
        if (bf < -1 && insertedKey < n.right.key)
            return rotateLeft(new Node(n.key, n.left, rotateRight(n.right)));
        return n;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = (x == null ? null : x.right);
        Node ny = new Node(y.key, t2, y.right);
        Node nx = new Node(x.key, x.left, ny);
        return nx;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node t2 = (y == null ? null : y.left);
        Node nx = new Node(x.key, x.left, t2);
        Node ny = new Node(y.key, nx, y.right);
        return ny;
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
        System.out.print(n.key + " ");
        printInOrder(n.right);
    }

    private void printInOrderWithBF(Node n) {
        if (n == null)
            return;
        printInOrderWithBF(n.left);
        int bf = (n.left == null ? 0 : n.left.height) - (n.right == null ? 0 : n.right.height);
        System.out.print(n.key + "(平衡因子=" + bf + ") ");
        printInOrderWithBF(n.right);
    }

    public static void main(String[] args) {
        PersistentAVLExercise t = new PersistentAVLExercise();
        System.out.println("=== 持久化 AVL 樹版本控制示範 ===");
        int v0 = 0;
        System.out.println("版本 " + v0 + " 中序: ");
        t.printInOrderWithBF(v0);
        int v1 = t.insert(v0, 10);
        System.out.println("插入 10 產生版本 " + v1 + " 中序: ");
        t.printInOrderWithBF(v1);
        int v2 = t.insert(v1, 20);
        System.out.println("插入 20 產生版本 " + v2 + " 中序: ");
        t.printInOrderWithBF(v2);
        int v3 = t.insert(v2, 30);
        System.out.println("插入 30 產生版本 " + v3 + " 中序: ");
        t.printInOrderWithBF(v3);
        int v4 = t.insert(v3, 25);
        System.out.println("插入 25 產生版本 " + v4 + " 中序: ");
        t.printInOrderWithBF(v4);
        System.out.println("版本 " + v2 + " 是否有效AVL: " + t.isValidAVL(v2));
        System.out.println("版本 " + v4 + " 是否有效AVL: " + t.isValidAVL(v4));
        System.out.println("版本 " + v2 + " 搜尋 25: " + t.search(v2, 25));
        System.out.println("版本 " + v4 + " 搜尋 25: " + t.search(v4, 25));
        System.out.println("版本數量: " + t.versionsCount());
        System.out.println("版本 " + v2 + " 高度: " + t.height(v2));
        System.out.println("版本 " + v4 + " 高度: " + t.height(v4));
    }
}
