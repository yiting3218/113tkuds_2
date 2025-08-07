package practice0806;

import java.util.*;

public class RecursiveTreePreview {

    /*** 1. 模擬資料夾結構，遞迴計算總檔案數 ***/

    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        public FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.children = new ArrayList<>();
        }
    }

    public static int countFiles(FileNode node) {
        if (node.isFile)
            return 1;

        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    /*** 2. 遞迴列印多層選單結構 ***/

    static class MenuItem {
        String label;
        List<MenuItem> children;

        public MenuItem(String label) {
            this.label = label;
            this.children = new ArrayList<>();
        }
    }

    public static void printMenu(MenuItem item, int level) {
        for (int i = 0; i < level; i++)
            System.out.print("  ");
        System.out.println("- " + item.label);

        for (MenuItem child : item.children) {
            printMenu(child, level + 1);
        }
    }

    /*** 3. 遞迴展平巢狀陣列 ***/

    public static List<Integer> flattenList(Object[] arr) {
        List<Integer> result = new ArrayList<>();
        for (Object obj : arr) {
            if (obj instanceof Integer) {
                result.add((Integer) obj);
            } else if (obj instanceof Object[]) {
                result.addAll(flattenList((Object[]) obj));
            }
        }
        return result;
    }

    /*** 4. 遞迴計算巢狀清單的最大深度 ***/

    public static int maxDepth(Object[] arr) {
        int max = 1;
        for (Object obj : arr) {
            if (obj instanceof Object[]) {
                max = Math.max(max, 1 + maxDepth((Object[]) obj));
            }
        }
        return max;
    }

    /*** 主程式 ***/
    public static void main(String[] args) {

        // 測試 1：資料夾總檔案數
        FileNode root = new FileNode("root", false);
        root.children.add(new FileNode("file1.txt", true));
        FileNode folder1 = new FileNode("folder1", false);
        folder1.children.add(new FileNode("file2.txt", true));
        folder1.children.add(new FileNode("file3.txt", true));
        FileNode folder2 = new FileNode("folder2", false);
        folder2.children.add(new FileNode("file4.txt", true));
        folder1.children.add(folder2);
        root.children.add(folder1);
        System.out.println("=== 總檔案數 ===");
        System.out.println("總檔案數：" + countFiles(root));

        // 測試 2：多層選單列印
        MenuItem menu = new MenuItem("主選單");
        MenuItem fileMenu = new MenuItem("檔案");
        fileMenu.children.add(new MenuItem("開啟"));
        fileMenu.children.add(new MenuItem("儲存"));
        MenuItem editMenu = new MenuItem("編輯");
        editMenu.children.add(new MenuItem("複製"));
        editMenu.children.add(new MenuItem("貼上"));
        menu.children.add(fileMenu);
        menu.children.add(editMenu);
        System.out.println("\n=== 多層選單列印 ===");
        printMenu(menu, 0);

        // 測試 3：展平巢狀陣列
        Object[] nestedArray = { 1, new Object[] { 2, 3 }, 4, new Object[] { 5, new Object[] { 6, 7 } } };
        System.out.println("\n=== 展平巢狀陣列 ===");
        List<Integer> flatList = flattenList(nestedArray);
        System.out.println("展平結果：" + flatList);

        // 測試 4：巢狀清單最大深度
        System.out.println("\n=== 巢狀清單最大深度 ===");
        System.out.println("最大深度：" + maxDepth(nestedArray));
    }
}
