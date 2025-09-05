package finalexam;

// 題目：護理紀錄刪除倒數第 N 筆
// 給定單向鏈結串列，刪除倒數第 k 個節點並回傳新串起點。
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC19_RemoveNth_Node_Clinic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        int k = sc.nextInt();
        ListNode head = removeNthFromEnd(dummy.next, k);
        // 輸出刪除後序列
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null)
                System.out.print(" ");
            head = head.next;
        }
        sc.close();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n+1 步，保證 slow 停在待刪前一節點
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除節點
        slow.next = slow.next.next;
        return dummy.next;
    }
}
