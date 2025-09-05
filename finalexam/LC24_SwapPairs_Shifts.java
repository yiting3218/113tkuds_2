package finalexam;

// 題目：班表兩兩交換
// 將鏈結串列中相鄰兩節點成對交換，奇數長度最後一個保留。
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC24_SwapPairs_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode dummy = new ListNode(0), tail = dummy;

        // 讀「一行」整數，避免等到 EOF 才結束
        if (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                for (String tok : line.split("\\s+")) {
                    tail.next = new ListNode(Integer.parseInt(tok));
                    tail = tail.next;
                }
            }
        }

        ListNode head = swapPairs(dummy.next);

        // 輸出交換後序列
        StringBuilder out = new StringBuilder();
        while (head != null) {
            out.append(head.val);
            if (head.next != null)
                out.append(' ');
            head = head.next;
        }
        System.out.println(out.toString()); // 加換行
        sc.close();
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = prev.next.next;

            a.next = b.next;
            b.next = a;
            prev.next = b;

            prev = a;
        }
        return dummy.next;
    }
}
