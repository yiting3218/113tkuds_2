package LeetCode;

// 題目：Reverse Nodes in k-Group
// 給定一個鏈結串列，將每 k 個節點進行反轉，並返回修改後的鏈結串列。

public class lt_25_ReverseNodesinkGroup {
    // ListNode 類別已經由 LeetCode 提供，你無需再手動定義
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 如果鏈結串列為空或只有一個節點，直接返回
        if (head == null || k == 1) {
            return head;
        }

        // 創建一個虛擬的頭節點
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        ListNode next = null;
        ListNode prev = null;
        int count = 0;

        // 計算鏈結串列的長度
        while (head != null) {
            count++;
            head = head.next;
        }

        // 每次反轉 k 個節點
        while (count >= k) {
            head = current.next; // 獲取當前的起始節點
            next = head.next;
            // 反轉 k 個節點
            for (int i = 1; i < k; i++) {
                head.next = next.next;
                next.next = current.next;
                current.next = next;
                next = head.next;
            }
            current = head;
            count -= k;
        }

        return dummy.next;
    }

    // 測試方法
    public static void main(String[] args) {
        // 反序列化，並測試 reverseKGroup 方法
        ListNode list = deserialize("[1,2,3,4,5]");
        int k = 2;
        ListNode result = reverseKGroup(list, k);

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
 * 1. 題目要求將每 k 個相鄰的節點進行反轉。這需要處理好節點間的連結。
 * 2. 使用虛擬的頭節點來簡化邊界情況的處理。
 * 3. 先計算鏈結串列的長度，然後每次反轉 k 個節點，直到剩餘節點不足 k 個。
 * 4. 每次反轉時，將 k 個節點的指標進行更新，保證反轉後的鏈結串列依然是連貫的。
 * 5. 時間複雜度 O(n)，其中 n 是鏈結串列的長度，每個節點最多處理一次。
 * 6. 空間複雜度 O(1)，除了幾個指標外，額外空間並不隨著輸入大小增長。
 */
