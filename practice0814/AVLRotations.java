package practice0814;

public class AVLRotations {

    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = (x != null) ? x.right : null;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();
        return x;
    }

    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = (y != null) ? y.left : null;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();
        return y;
    }
}
