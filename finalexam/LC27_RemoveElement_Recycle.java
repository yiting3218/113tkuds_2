package finalexam;

// 題目：回收站清單移除指定元素
// 從陣列中就地移除所有等於 val 的元素，回傳新長度並輸出前段結果。
import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        int k = removeElement(nums, val); // 新長度
        System.out.println(k);

        // 輸出前段結果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(nums[i]);
        }
        if (k > 0)
            System.out.println(sb.toString());
        sc.close();
    }

    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) {
                nums[write] = x;
                write++;
            }
        }
        return write;
    }
}
