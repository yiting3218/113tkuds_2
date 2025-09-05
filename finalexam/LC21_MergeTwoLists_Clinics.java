package finalexam;

// 題目：合併兩院區掛號清單
// 將兩條已排序的單向鏈結串列合併為一條升序串列。
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC21_MergeTwoLists_Clinics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        ListNode dummy1 = new ListNode(0), cur1 = dummy1;
        for (int i = 0; i < n; i++) {
            cur1.next = new ListNode(sc.nextInt());
            cur1 = cur1.next;
        }
        ListNode dummy2 = new ListNode(0), cur2 = dummy2;
        for (int i = 0; i < m; i++) {
            cur2.next = new ListNode(sc.nextInt());
            cur2 = cur2.next;
        }
        ListNode merged = mergeTwoLists(dummy1.next, dummy2.next);
        // 輸出合併後的序列
        while (merged != null) {
            System.out.print(merged.val);
            if (merged.next != null)
                System.out.print(" ");
            merged = merged.next;
        }
        sc.close();
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null)
            tail.next = l1;
        if (l2 != null)
            tail.next = l2;
        return dummy.next;
    }
}
