package practice0814;

public class AVLSet {
    private AVLNode root;

    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    public void printPreOrder() {
        printPreOrder(root);
        System.out.println();
    }

    public void printWithBF() {
        printWithBF(root);
        System.out.println();
    }

    private int height(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    private int balanceOf(AVLNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private AVLNode insert(AVLNode node, int key) {
        if (node == null)
            return new AVLNode(key);
        if (key < node.data)
            node.left = insert(node.left, key);
        else if (key > node.data)
            node.right = insert(node.right, key);
        else
            return node;
        node.updateHeight();
        int balance = balanceOf(node);
        if (balance > 1 && key < node.left.data)
            return rightRotate(node);
        if (balance < -1 && key > node.right.data)
            return leftRotate(node);
        if (balance > 1 && key > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode cur = node;
        while (cur.left != null)
            cur = cur.left;
        return cur;
    }

    private AVLNode delete(AVLNode node, int key) {
        if (node == null)
            return null;
        if (key < node.data)
            node.left = delete(node.left, key);
        else if (key > node.data)
            node.right = delete(node.right, key);
        else {
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                node = temp;
            } else {
                AVLNode succ = minValueNode(node.right);
                node.data = succ.data;
                node.right = delete(node.right, succ.data);
            }
        }
        if (node == null)
            return null;
        node.updateHeight();
        int balance = balanceOf(node);
        if (balance > 1 && balanceOf(node.left) >= 0)
            return rightRotate(node);
        if (balance > 1 && balanceOf(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && balanceOf(node.right) <= 0)
            return leftRotate(node);
        if (balance < -1 && balanceOf(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = (x != null) ? x.right : null;
        x.right = y;
        y.left = t2;
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = (y != null) ? y.left : null;
        y.left = x;
        x.right = t2;
        x.updateHeight();
        y.updateHeight();
        return y;
    }

    private void printInOrder(AVLNode node) {
        if (node == null)
            return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    private void printPreOrder(AVLNode node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    private void printWithBF(AVLNode node) {
        if (node == null)
            return;
        printWithBF(node.left);
        System.out.print(node.data + "(平衡因子=" + node.getBalance() + ") ");
        printWithBF(node.right);
    }
}
