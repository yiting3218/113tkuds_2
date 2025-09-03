package LeetCode;

// 題目：Swap Nodes in Pairs
// 給定一個鏈結串列，將每兩個相鄰的節點進行交換，並返回其頭節點。

public class lt_24_SwapNodesinPairs {
    // ListNode 類別已經由 LeetCode 提供，你無需再手動定義
    public static ListNode swapPairs(ListNode head) {
        // 如果鏈結串列為空或只有一個元素，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 創建一個虛擬的頭節點，方便操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        // 當有兩個以上節點時，進行交換
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            // 進行交換
            first.next = second.next;
            second.next = first;
            current.next = second;

            // 移動指標
            current = first;
        }

        // 返回新的頭節點
        return dummy.next;
    }

    // 測試方法
    public static void main(String[] args) {
        // 反序列化，並測試 swapPairs 方法
        ListNode list = deserialize("[1,2,3,4]");
        ListNode result = swapPairs(list);

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
 * 1. 題目要求將鏈結串列中的每兩個相鄰節點交換。
 * 2. 使用一個虛擬的頭節點 dummy 來簡化邊界情況的處理。
 * 3. 每次處理兩個節點，如果它們存在，就將這兩個節點進行交換，然後繼續處理下一組節點。
 * 4. 時間複雜度 O(n)，其中 n 是鏈結串列的長度，每個節點最多處理一次。
 * 5. 空間複雜度 O(1)，除了幾個指標外，額外空間並不隨著輸入大小增長。
 */
