package practice0806;

import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 每層儲存在不同 List 中
    public static List<List<Integer>> levelOrderPerLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                level.add(curr.val);
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
            result.add(level);
        }
        return result;
    }

    // 2. 之字形層序走訪
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (leftToRight)
                    level.addLast(curr.val);
                else
                    level.addFirst(curr.val);
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    // 3. 每層最後一個節點
    public static void printLastNodeEachLevel(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                last = curr;
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
            System.out.println("層的最後節點：" + last.val);
        }
    }

    // 4. 垂直層序走訪（水平座標分組）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        // Map<水平位置, List of nodes>
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> colQ = new LinkedList<>();

        nodeQ.offer(root);
        colQ.offer(0);

        while (!nodeQ.isEmpty()) {
            TreeNode curr = nodeQ.poll();
            int col = colQ.poll();

            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(curr.val);

            if (curr.left != null) {
                nodeQ.offer(curr.left);
                colQ.offer(col - 1);
            }
            if (curr.right != null) {
                nodeQ.offer(curr.right);
                colQ.offer(col + 1);
            }
        }

        for (List<Integer> list : map.values()) {
            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        // 測試用樹：
        // 1
        // / \
        // 2 3
        // / \ / \
        // 4 5 6 7

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("1. 每層分 List：");
        System.out.println(levelOrderPerLevel(root));

        System.out.println("\n2. 之字形層序：");
        System.out.println(zigzagLevelOrder(root));

        System.out.println("\n3. 每層最後節點：");
        printLastNodeEachLevel(root);

        System.out.println("\n4. 垂直層序走訪：");
        System.out.println(verticalOrder(root));
    }
}
