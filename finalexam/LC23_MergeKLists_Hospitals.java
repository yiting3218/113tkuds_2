package finalexam;

// 題目：多院區即時叫號合併
// 給定 k 條已排序的候診名單（單向鏈結串列），合併為一條升序串列，效率需優於兩兩合併。
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LC23_MergeKLists_Hospitals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode[] lists = new ListNode[k];

        // 讀取 k 行序列，每行以 -1 結束，可能為空串列（直接 -1）
        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(0), tail = dummy;
            while (true) {
                int x = sc.nextInt();
                if (x == -1)
                    break;
                tail.next = new ListNode(x);
                tail = tail.next;
            }
            lists[i] = dummy.next;
        }

        ListNode merged = mergeKLists(lists);

        // 輸出合併後序列（空則不輸出）
        while (merged != null) {
            System.out.print(merged.val);
            if (merged.next != null)
                System.out.print(" ");
            merged = merged.next;
        }
        sc.close();
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // 初始將各串列頭節點放入堆中
        for (ListNode node : lists) {
            if (node != null)
                pq.offer(node);
        }

        ListNode dummy = new ListNode(0), tail = dummy;

        // 不斷彈出最小節點，並將其下一個節點加入堆
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null)
                pq.offer(cur.next);
        }
        return dummy.next;
    }
}
