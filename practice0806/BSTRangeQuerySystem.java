package practice0806;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int data;
        TreeNode left, right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 插入節點（建 BST）
     */
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (val < root.data)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);
        return root;
    }

    /**
     * 範圍查詢：印出[min, max]內的所有節點
     */
    public static void rangeQuery(TreeNode root, int min, int max) {
        if (root == null)
            return;
        if (root.data > min)
            rangeQuery(root.left, min, max);
        if (root.data >= min && root.data <= max)
            System.out.print(root.data + " ");
        if (root.data < max)
            rangeQuery(root.right, min, max);
    }

    /**
     * 範圍計數
     */
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null)
            return 0;
        if (root.data < min)
            return rangeCount(root.right, min, max);
        if (root.data > max)
            return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    /**
     * 範圍總和
     */
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null)
            return 0;
        if (root.data < min)
            return rangeSum(root.right, min, max);
        if (root.data > max)
            return rangeSum(root.left, min, max);
        return root.data + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    /**
     * 找出最接近 target 的節點值
     */
    public static int findClosest(TreeNode root, int target) {
        int closest = root.data;
        while (root != null) {
            if (Math.abs(target - root.data) < Math.abs(target - closest)) {
                closest = root.data;
            }
            if (target < root.data)
                root = root.left;
            else if (target > root.data)
                root = root.right;
            else
                break;
        }
        return closest;
    }

    public static void main(String[] args) {
        // 測試資料
        int[] values = { 20, 10, 5, 15, 30, 25, 35 };
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 10, max = 30;
        int target = 22;

        System.out.println("=== BST 範圍查詢系統 ===");
        System.out.print("在 [" + min + ", " + max + "] 範圍內的節點：");
        rangeQuery(root, min, max);
        System.out.println();

        System.out.println("節點數量：" + rangeCount(root, min, max));
        System.out.println("節點總和：" + rangeSum(root, min, max));
        System.out.println("最接近 " + target + " 的節點值：" + findClosest(root, target));
    }
}
