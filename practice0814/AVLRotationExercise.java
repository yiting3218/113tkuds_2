package practice0814;

public class AVLRotationExercise {
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

    private static void updateAll(Node n) {
        if (n == null)
            return;
        updateAll(n.left);
        updateAll(n.right);
        n.update();
    }

    private static void printInOrderWithBF(Node n) {
        inOrderWithBF(n);
        System.out.println();
    }

    private static void inOrderWithBF(Node n) {
        if (n == null)
            return;
        inOrderWithBF(n.left);
        int bf = (n.left == null ? 0 : n.left.height) - (n.right == null ? 0 : n.right.height);
        System.out.print(n.key + "(平衡因子=" + bf + ") ");
        inOrderWithBF(n.right);
    }

    private static int checkAVL(Node n) {
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

    private static boolean isAVL(Node n) {
        return checkAVL(n) != -1;
    }

    private static Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = (x == null ? null : x.right);
        x.right = y;
        y.left = t2;
        y.update();
        x.update();
        return x;
    }

    private static Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = (y == null ? null : y.left);
        y.left = x;
        x.right = t2;
        x.update();
        y.update();
        return y;
    }

    private static Node leftRightRotate(Node c) {
        c.left = leftRotate(c.left);
        return rightRotate(c);
    }

    private static Node rightLeftRotate(Node a) {
        a.right = rightRotate(a.right);
        return leftRotate(a);
    }

    private static void demoLL() {
        Node c = new Node(10);
        Node a = new Node(5);
        Node x = new Node(3);
        c.left = a;
        a.left = x;
        updateAll(c);
        System.out.println("LL 範例：右旋前");
        printInOrderWithBF(c);
        Node newRoot = rightRotate(c);
        updateAll(newRoot);
        System.out.println("LL 範例：右旋後");
        printInOrderWithBF(newRoot);
        System.out.println("有效AVL: " + isAVL(newRoot));
        System.out.println();
    }

    private static void demoRR() {
        Node a = new Node(10);
        Node c = new Node(15);
        Node z = new Node(20);
        a.right = c;
        c.right = z;
        updateAll(a);
        System.out.println("RR 範例：左旋前");
        printInOrderWithBF(a);
        Node newRoot = leftRotate(a);
        updateAll(newRoot);
        System.out.println("RR 範例：左旋後");
        printInOrderWithBF(newRoot);
        System.out.println("有效AVL: " + isAVL(newRoot));
        System.out.println();
    }

    private static void demoLR() {
        Node c = new Node(10);
        Node a = new Node(5);
        Node b = new Node(7);
        Node w = new Node(4);
        Node x = new Node(6);
        Node y = new Node(8);
        c.left = a;
        a.right = b;
        a.left = w;
        b.left = x;
        b.right = y;
        updateAll(c);
        System.out.println("LR 範例：雙旋前");
        printInOrderWithBF(c);
        Node newRoot = leftRightRotate(c);
        updateAll(newRoot);
        System.out.println("LR 範例：雙旋後");
        printInOrderWithBF(newRoot);
        System.out.println("有效AVL: " + isAVL(newRoot));
        System.out.println();
    }

    private static void demoRL() {
        Node a = new Node(10);
        Node c = new Node(15);
        Node b = new Node(12);
        Node x = new Node(11);
        Node y = new Node(13);
        Node z = new Node(20);
        a.right = c;
        c.left = b;
        b.left = x;
        b.right = y;
        c.right = z;
        updateAll(a);
        System.out.println("RL 範例：雙旋前");
        printInOrderWithBF(a);
        Node newRoot = rightLeftRotate(a);
        updateAll(newRoot);
        System.out.println("RL 範例：雙旋後");
        printInOrderWithBF(newRoot);
        System.out.println("有效AVL: " + isAVL(newRoot));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== AVL 旋轉操作實作與測試 ===");
        demoLL();
        demoRR();
        demoLR();
        demoRL();
    }
}
