package LeetCode;

// 題目：Merge Two Sorted Lists
// 給定兩個排序過的鏈結串列 list1 和 list2，將它們合併為一個排序後的鏈結串列。

public class lt_21_MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 創建一個虛擬的頭節點，方便處理頭節點的合併
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 當兩個鏈結串列都有元素時，繼續比較並合併
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1; // 把較小的節點添加到結果中
                list1 = list1.next; // 移動 list1 指標
            } else {
                current.next = list2; // 把較小的節點添加到結果中
                list2 = list2.next; // 移動 list2 指標
            }
            current = current.next; // 移動 current 指標
        }

        // 如果 list1 還有剩餘的節點，將它們直接加到結果中
        if (list1 != null) {
            current.next = list1;
        }

        // 如果 list2 還有剩餘的節點，將它們直接加到結果中
        if (list2 != null) {
            current.next = list2;
        }

        // 返回合併後的鏈結串列，跳過虛擬的頭節點
        return dummy.next;
    }

    // 測試方法
    public static void main(String[] args) {
        // 反序列化，並測試 mergeTwoLists 方法
        ListNode list1 = deserialize("[1,2,4]");
        ListNode list2 = deserialize("[1,3,4]");
        ListNode result = mergeTwoLists(list1, list2);

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
 * 1. 題目要求將兩個排序的鏈結串列合併成一個排序過的鏈結串列。
 * 2. 使用一個虛擬的頭節點 dummy，這樣方便操作第一個元素。
 * 3. 使用一個指標 current，遍歷兩個鏈結串列，選擇較小的元素放入合併後的結果中。
 * 4. 最後，如果有剩餘的元素，直接將它們附加到結果中。
 * 5. 時間複雜度 O(n)，其中 n 是兩個鏈結串列的總長度，空間複雜度 O(1)。
 */
