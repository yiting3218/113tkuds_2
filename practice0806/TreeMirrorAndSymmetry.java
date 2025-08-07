package practice0806;

public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 判斷是否為對稱樹（左右子樹為鏡像）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    // 判斷兩棵子樹是否為鏡像
    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return t1.data == t2.data &&
                isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left);
    }

    // 將一棵樹轉為鏡像樹（原地修改）
    public static void mirror(TreeNode root) {
        if (root == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);
    }

    // 比較兩棵樹是否互為鏡像
    public static boolean areMirrors(TreeNode root1, TreeNode root2) {
        return isMirror(root1, root2);
    }

    // 判斷 subTree 是否為 mainTree 的子樹
    public static boolean isSubtree(TreeNode main, TreeNode sub) {
        if (main == null)
            return false;
        if (isSameTree(main, sub))
            return true;
        return isSubtree(main.left, sub) || isSubtree(main.right, sub);
    }

    // 判斷兩棵樹是否完全相同
    public static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        return a.data == b.data &&
                isSameTree(a.left, b.left) &&
                isSameTree(a.right, b.right);
    }

    // 印出中序走訪
    public static void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        // 測試樹
        // 1
        // / \
        // 2 2
        // / \
        // 3 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root));

        System.out.print("原樹中序走訪: ");
        inOrder(root);
        System.out.println();

        mirror(root);
        System.out.print("鏡像後中序走訪: ");
        inOrder(root);
        System.out.println();

        // 建立一棵鏡像的樹來比較
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(3);

        System.out.println("兩棵樹是否互為鏡像: " + areMirrors(root, root2));

        // 測試子樹
        TreeNode sub = new TreeNode(2);
        sub.right = new TreeNode(3);

        System.out.println("是否為子樹: " + isSubtree(root2, sub));
    }
}