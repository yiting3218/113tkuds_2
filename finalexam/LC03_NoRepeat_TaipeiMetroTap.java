package finalexam;

import java.util.*;

// 題目：北捷刷卡最長無重複片段
// 給定刷卡紀錄字串 s，找出最長的不含重複字元的子字串長度。

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        // Map 存每個字元最後一次出現的索引
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果這個字元出現過，左指針要跳到它上次出現位置 +1
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }

            // 更新這個字元最新位置
            map.put(c, right);

            // 更新目前視窗長度
            ans = Math.max(ans, right - left + 1);
        }

        System.out.println(ans);
        sc.close();
    }
}
