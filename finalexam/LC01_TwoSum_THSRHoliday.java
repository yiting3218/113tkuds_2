package finalexam;

import java.util.*;

// 題目:高鐵連假加班車 Two Sum
// 給定班次座位數陣列與目標需求 target，找出兩個不同班次索引，其座位數和正好等於 target。
// 若不存在則輸出 -1 -1。

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 和 target
        int n = sc.nextInt();
        long target = sc.nextLong();

        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        // HashMap: key=需要的數, value=索引
        Map<Long, Integer> map = new HashMap<>();

        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < n; i++) {
            long val = seats[i];
            if (map.containsKey(val)) {
                idx1 = map.get(val);
                idx2 = i;
                break;
            } else {
                map.put(target - val, i);
            }
        }

        if (idx1 == -1) {
            System.out.println("-1 -1");
        } else {
            System.out.println(idx1 + " " + idx2);
        }

        sc.close();
    }
}
