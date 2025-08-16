package practice0814;

public class AVLRotationsDemo {

    public static void main(String[] args) {
        demoLL_RightRotate();
        System.out.println("----");
        demoRR_LeftRotate();
    }

    private static void demoLL_RightRotate() {
        AVLNode y = new AVLNode(10);
        AVLNode x = new AVLNode(5);
        AVLNode a = new AVLNode(3);
        y.left = x;
        x.left = a;

        updateAllHeights(y);

        System.out.println("右旋前 (LL 不平衡):");
        printInOrderWithBF(y);

        AVLNode newRoot = AVLRotations.rightRotate(y);
        updateAllHeights(newRoot);

        System.out.println("右旋後:");
        printInOrderWithBF(newRoot);
    }

    private static void demoRR_LeftRotate() {
        AVLNode x = new AVLNode(10);
        AVLNode y = new AVLNode(15);
        AVLNode c = new AVLNode(20);
        x.right = y;
        y.right = c;

        updateAllHeights(x);

        System.out.println("左旋前 (RR 不平衡):");
        printInOrderWithBF(x);

        AVLNode newRoot = AVLRotations.leftRotate(x);
        updateAllHeights(newRoot);

        System.out.println("左旋後:");
        printInOrderWithBF(newRoot);
    }

    private static void updateAllHeights(AVLNode node) {
        if (node == null)
            return;
        updateAllHeights(node.left);
        updateAllHeights(node.right);
        node.updateHeight();
    }

    private static void printInOrderWithBF(AVLNode node) {
        inOrderWithBF(node);
        System.out.println();
    }

    private static void inOrderWithBF(AVLNode node) {
        if (node == null)
            return;
        inOrderWithBF(node.left);
        int bf = (node.left != null ? node.left.height : 0) - (node.right != null ? node.right.height : 0);
        System.out.print(node.data + "(平衡因子=" + bf + ") ");
        inOrderWithBF(node.right);
    }
}
