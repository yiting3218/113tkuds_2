package finalexam;

// 題目：去重學生成績單
// 已排序的學號清單，就地移除重複，回傳新長度並輸出前段內容。
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        int k = removeDuplicates(nums); // 新長度
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

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int write = 1; // 指向可寫位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[write - 1]) { // 與前一保留值不同才寫入
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}
