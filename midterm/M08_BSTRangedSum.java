package midterm;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

public class M08_BSTRangedSum {

    public static Node buildTree(int[] arr) {
        if (arr.length == 0)
            return null;

        Node root = new Node(arr[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (i < arr.length) {
            Node current = queue.poll();
            if (arr[i] != -1) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static int calculateSum(Node root, int L, int R) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        if (root.data >= L && root.data <= R) {
            sum += root.data;
        }

        if (root.data > L) {
            sum += calculateSum(root.left, L, R);
        }

        if (root.data < R) {
            sum += calculateSum(root.right, L, R);
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int L = scanner.nextInt();
        int R = scanner.nextInt();

        Node root = buildTree(arr);

        int sum = calculateSum(root, L, R);

        System.out.println("Sum: " + sum);

        scanner.close();
    }
}
