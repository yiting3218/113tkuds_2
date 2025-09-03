package LeetCode;

// 題目：Merge k Sorted Lists
// 給定一個包含 k 個鏈結串列的陣列，每個鏈結串列已經排序，將它們合併為一個排序過的鏈結串列。

import java.util.*;

public class lt_23_MergeKSortedLists {
    // ListNode 類別已經由 LeetCode 提供，你無需再手動定義
    public static ListNode mergeKLists(ListNode[] lists) {
        // 使用優先隊列（最小堆）來合併 k 個已排序的鏈結串列
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a, b) -> a.val - b.val);

        // 將所有鏈結串列的頭節點加入優先隊列
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }

        // 創建一個虛擬的頭節點
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 使用最小堆進行合併
        while (!pq.isEmpty()) {
            ListNode node = pq.poll(); // 取出最小的節點
            current.next = node; // 添加到結果鏈結串列
            current = current.next; // 移動指標

            // 如果取出的節點有下一個節點，將其加入優先隊列
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        // 返回合併後的鏈結串列，跳過虛擬的頭節點
        return dummy.next;
    }

    // 測試方法
    public static void main(String[] args) {
        // 反序列化，並測試 mergeKLists 方法
        ListNode list1 = deserialize("[1,4,5]");
        ListNode list2 = deserialize("[1,3,4]");
        ListNode list3 = deserialize("[2,6]");
        ListNode[] lists = new ListNode[] { list1, list2, list3 };

        ListNode result = mergeKLists(lists);

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
 * 1. 題目要求將 k 個已排序的鏈結串列合併成一個排序過的鏈結串列。
 * 2. 使用最小堆（優先隊列）來幫助我們每次取出最小的節點，並將這些節點依序添加到結果中。
 * 3. 將所有鏈結串列的頭節點加入最小堆，然後每次取出最小的節點並添加到結果中，並將其下一個節點加入堆中。
 * 4. 時間複雜度 O(N log k)，其中 N 是所有鏈結串列中節點的總數，k 是鏈結串列的數量。每次操作堆的時間複雜度是 O(log
 * k)，總共需要進行 N 次操作。
 * 5. 空間複雜度 O(k)，用來存儲最小堆中最多 k 個元素。
 */
