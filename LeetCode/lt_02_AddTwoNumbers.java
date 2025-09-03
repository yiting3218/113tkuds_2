package LeetCode;

// 題目：Add Two Numbers
// 給定兩個非空的鏈結串列，分別表示兩個非負整數（數字以反向儲存，每個節點一位數），請回傳它們的和（同樣以鏈結串列形式，反向儲存）。

public class lt_02_AddTwoNumbers {
    // 鏈結串列定義
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;

            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummy.next;
    }
}

/*
 * 解題思路：
 * 1. 題目要求將兩個以反向鏈結串列表示的數字相加。
 * 2. 逐位相加並處理進位，長度不足視為 0，最後若仍有進位需補節點。
 * 3. 時間複雜度 O(max(m,n))，空間複雜度 O(1)。
 */
