package finalexam;

// 題目：班表 k 組反轉
// 將鏈結串列每 k 個節點分組反轉，不足 k 的尾端保持原樣。
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC25_ReverseKGroup_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 k
        int k = Integer.parseInt(sc.nextLine().trim());

        // 讀取「一行」序列，避免等待 EOF
        String line = sc.hasNextLine() ? sc.nextLine().trim() : "";
        ListNode dummy = new ListNode(0), tail = dummy;
        if (!line.isEmpty()) {
            for (String tok : line.split("\\s+")) {
                tail.next = new ListNode(Integer.parseInt(tok));
                tail = tail.next;
            }
        }

        ListNode head = reverseKGroup(dummy.next, k);

        // 輸出反轉後的序列
        StringBuilder out = new StringBuilder();
        while (head != null) {
            out.append(head.val);
            if (head.next != null)
                out.append(' ');
            head = head.next;
        }
        System.out.println(out.toString());
        sc.close();
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        while (true) {
            // 檢查是否有足夠的 k 節點
            ListNode kth = prevGroup;
            for (int i = 0; i < k && kth != null; i++)
                kth = kth.next;
            if (kth == null)
                break;

            ListNode groupNext = kth.next;

            // 原地反轉 [prevGroup.next .. kth]
            ListNode prev = groupNext, cur = prevGroup.next;
            while (cur != groupNext) {
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }

            // 連回主串並前進到下一組
            ListNode tail = prevGroup.next; // 反轉後該組的尾巴
            prevGroup.next = kth;
            prevGroup = tail;
        }
        return dummy.next;
    }
}
