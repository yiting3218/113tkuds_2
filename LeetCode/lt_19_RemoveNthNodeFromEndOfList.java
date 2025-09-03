package LeetCode;

// 題目：Remove Nth Node From End of List
// 給定一個鏈結串列的頭節點 head，刪除倒數第 n 個節點，並返回鏈結串列的頭。

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class lt_19_RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 假設頭節點
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        // 移動 first 指標，使其與 second 之間距離為 n
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }

        // 同時移動 first 和 second，直到 first 到達鏈結串列的尾端
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // second 的 next 就是要刪除的節點，將其刪除
        second.next = second.next.next;

        return dummy.next; // 返回新的頭節點
    }

    public static void main(String[] args) {
        // 反序列化，並測試 removeNthFromEnd 方法
        ListNode head = deserialize("[1,2,3,4,5]");
        ListNode result = removeNthFromEnd(head, 2);

        // 輸出結果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    // deserialize 方法：將數字陣列轉換為 ListNode
    public static ListNode deserialize(String str) {
        String[] parts = str.replaceAll("[\\[\\] ]", "").split(",");
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (String part : parts) {
            current.next = new ListNode(Integer.parseInt(part));
            current = current.next;
        }
        return dummy.next;
    }
}

/*
 * 解題思路：
 * 1. 使用雙指標法，創建一個 dummy 節點，使 head 節點方便處理。
 * 2. 先讓 first 指標向前移動 n + 1 步，使得 first 和 second 之間的距離為 n。
 * 3. 然後同時移動 first 和 second，直到 first 到達鏈結串列的末端，這樣 second 就會指向倒數第 n + 1 個節點。
 * 4. 刪除 second 指向的節點，即 second.next。
 * 5. 時間複雜度 O(n)，其中 n 是鏈結串列的長度，空間複雜度 O(1)，因為只用了兩個指標。
 */
