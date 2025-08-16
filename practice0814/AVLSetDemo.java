package practice0814;

public class AVLSetDemo {
    public static void main(String[] args) {
        AVLSet s = new AVLSet();

        int[] a = { 10, 20, 30, 40, 50, 25 };
        for (int v : a)
            s.insert(v);

        System.out.println("插入後中序:");
        s.printInOrder();
        System.out.println("插入後先序:");
        s.printPreOrder();
        System.out.println("插入後中序含平衡因子:");
        s.printWithBF();

        s.delete(40);
        System.out.println("刪除 40 後中序:");
        s.printInOrder();
        System.out.println("刪除 40 後先序:");
        s.printPreOrder();
        System.out.println("刪除 40 後中序含平衡因子:");
        s.printWithBF();
    }
}
