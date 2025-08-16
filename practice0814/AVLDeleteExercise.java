package practice0814;

public class AVLDeleteExercise {
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

    public void delete(int x) {
        root = delete(root, x);
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    public int height() {
        return height(root);
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

    private Node minNode(Node n) {
        Node cur = n;
        while (cur.left != null)
            cur = cur.left;
        return cur;
    }

    private Node delete(Node n, int x) {
        if (n == null)
            return null;
        if (x < n.key)
            n.left = delete(n.left, x);
        else if (x > n.key)
            n.right = delete(n.right, x);
        else {
            if (n.left == null && n.right == null) {
                n = null;
            } else if (n.left == null) {
                n = n.right;
            } else if (n.right == null) {
                n = n.left;
            } else {
                Node succ = minNode(n.right);
                n.key = succ.key;
                n.right = delete(n.right, succ.key);
            }
        }
        if (n == null)
            return null;
        n.update();
        int bf = n.balance();
        if (bf > 1 && n.left.balance() >= 0)
            return rotateRight(n);
        if (bf > 1 && n.left.balance() < 0) {
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (bf < -1 && n.right.balance() <= 0)
            return rotateLeft(n);
        if (bf < -1 && n.right.balance() > 0) {
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

    private void printInOrder(Node n) {
        if (n == null)
            return;
        printInOrder(n.left);
        int bf = (n.left == null ? 0 : n.left.height) - (n.right == null ? 0 : n.right.height);
        System.out.print(n.key + "(平衡因子=" + bf + ") ");
        printInOrder(n.right);
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

    public static void main(String[] args) {
        AVLDeleteExercise t = new AVLDeleteExercise();
        int[] init = { 30, 20, 40, 10, 25, 35, 50, 5, 22, 37, 60 };
        System.out.println("=== 建立初始樹 ===");
        for (int v : init)
            t.insert(v);
        System.out.print("中序: ");
        t.printInOrder();
        System.out.println("高度: " + t.height());
        System.out.println("有效AVL: " + t.isValidAVL());
        System.out.println();

        System.out.println("=== 刪除葉子節點: 5 ===");
        t.delete(5);
        System.out.print("中序: ");
        t.printInOrder();
        System.out.println("高度: " + t.height());
        System.out.println("有效AVL: " + t.isValidAVL());
        System.out.println();

        System.out.println("=== 刪除只有一個子節點的節點: 60 ===");
        t.delete(60);
        System.out.print("中序: ");
        t.printInOrder();
        System.out.println("高度: " + t.height());
        System.out.println("有效AVL: " + t.isValidAVL());
        System.out.println();

        System.out.println("=== 刪除有兩個子節點的節點: 30 ===");
        t.delete(30);
        System.out.print("中序: ");
        t.printInOrder();
        System.out.println("高度: " + t.height());
        System.out.println("有效AVL: " + t.isValidAVL());
        System.out.println();
    }
}
