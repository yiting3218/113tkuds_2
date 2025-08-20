package midterm;

/*
 * 時間複雜度: O(n)
 * 說明：遍歷每個節點檢查紅黑樹的性質，每個節點最多被訪問一次，因此時間複雜度為 O(n)，其中 n 是樹的節點數。
 */

import java.util.*;

class Node {
    int data;
    String color;
    Node left, right;

    Node(int item, String color) {
        data = item;
        this.color = color;
        left = right = null;
    }
}

public class M10_RBPropertiesCheck {

    public static boolean checkRoot(Node root) {
        return root.color.equals("B");
    }

    public static boolean checkRedRedViolation(Node node) {
        if (node == null)
            return true;

        if (node.color.equals("R")) {
            if ((node.left != null && node.left.color.equals("R"))
                    || (node.right != null && node.right.color.equals("R"))) {
                return false;
            }
        }

        return checkRedRedViolation(node.left) && checkRedRedViolation(node.right);
    }

    public static int checkBlackHeight(Node node) {
        if (node == null) {
            return 1;
        }

        int leftBlackHeight = checkBlackHeight(node.left);
        int rightBlackHeight = checkBlackHeight(node.right);

        if (leftBlackHeight == -1 || rightBlackHeight == -1 || leftBlackHeight != rightBlackHeight) {
            return -1;
        }

        return leftBlackHeight + (node.color.equals("B") ? 1 : 0);
    }

    public static Node buildTree(int[] values, String[] colors) {
        if (values.length == 0)
            return null;

        Node root = new Node(values[0], colors[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (i < values.length) {
            Node current = queue.poll();
            if (values[i] != -1) {
                current.left = new Node(values[i], colors[i]);
                queue.add(current.left);
            }
            i++;

            if (i < values.length && values[i] != -1) {
                current.right = new Node(values[i], colors[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] values = new int[n];
        String[] colors = new String[n];

        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            colors[i] = scanner.next();
        }

        Node root = buildTree(values, colors);

        if (!checkRoot(root)) {
            System.out.println("RootNotBlack");
        } else if (!checkRedRedViolation(root)) {
            System.out.println("RedRedViolation");
        } else if (checkBlackHeight(root) == -1) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }

        scanner.close();
    }
}
