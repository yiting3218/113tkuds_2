package practice0806;

import java.util.*;

public class MultiWayTreeAndDecisionTree {

    static class MultiNode {
        String data;
        List<MultiNode> children;

        MultiNode(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static MultiNode buildSampleTree() {
        MultiNode root = new MultiNode("Root");
        MultiNode A = new MultiNode("A");
        MultiNode B = new MultiNode("B");
        MultiNode C = new MultiNode("C");
        MultiNode D = new MultiNode("D");
        MultiNode E = new MultiNode("E");

        root.children.add(A);
        root.children.add(B);
        root.children.add(C);

        A.children.add(D);
        A.children.add(E);

        return root;
    }

    public static void dfs(MultiNode node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        for (MultiNode child : node.children) {
            dfs(child);
        }
    }

    public static void bfs(MultiNode root) {
        if (root == null)
            return;
        Queue<MultiNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiNode current = queue.poll();
            System.out.print(current.data + " ");
            for (MultiNode child : current.children) {
                queue.offer(child);
            }
        }
    }

    public static int height(MultiNode node) {
        if (node == null)
            return 0;
        int maxHeight = 0;
        for (MultiNode child : node.children) {
            maxHeight = Math.max(maxHeight, height(child));
        }
        return maxHeight + 1;
    }

    public static void printDegrees(MultiNode node) {
        if (node == null)
            return;
        System.out.println("節點 " + node.data + " 的度數: " + node.children.size());
        for (MultiNode child : node.children) {
            printDegrees(child);
        }
    }

    static class DecisionNode {
        String question;
        DecisionNode yes;
        DecisionNode no;

        DecisionNode(String question) {
            this.question = question;
        }
    }

    public static DecisionNode buildGuessNumberTree() {
        DecisionNode root = new DecisionNode("是大於5嗎?");
        root.yes = new DecisionNode("是偶數嗎?");
        root.no = new DecisionNode("是奇數嗎?");

        root.yes.yes = new DecisionNode("你猜的是 6");
        root.yes.no = new DecisionNode("你猜的是 7");

        root.no.yes = new DecisionNode("你猜的是 3");
        root.no.no = new DecisionNode("你猜的是 2");

        return root;
    }

    public static void runDecisionTree(Scanner scanner, DecisionNode node) {
        if (node.yes == null && node.no == null) {
            System.out.println(node.question);
            return;
        }
        System.out.println(node.question + " (yes/no)");
        String ans = scanner.nextLine().trim().toLowerCase();
        if (ans.equals("yes"))
            runDecisionTree(scanner, node.yes);
        else
            runDecisionTree(scanner, node.no);
    }

    public static void main(String[] args) {
        System.out.println("=== 多路樹操作 ===");
        MultiNode root = buildSampleTree();
        System.out.print("DFS: ");
        dfs(root);
        System.out.println();
        System.out.print("BFS: ");
        bfs(root);
        System.out.println();
        System.out.println("高度: " + height(root));
        printDegrees(root);

        System.out.println("\n=== 決策樹猜數字遊戲 ===");
        Scanner sc = new Scanner(System.in);
        DecisionNode decisionRoot = buildGuessNumberTree();
        runDecisionTree(sc, decisionRoot);
    }
}