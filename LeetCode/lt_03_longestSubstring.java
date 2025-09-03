package LeetCode;

// 題目：Longest Substring Without Repeating Characters
// 給定字串 s，找出「不含重複字元」的最長子字串長度。

public class lt_03_longestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        // last[c] 紀錄字元 c 上一次出現的索引；初始為 -1 代表沒出現過
        int[] last = new int[256];
        for (int i = 0; i < 256; i++)
            last[i] = -1;

        int left = 0; // 滑動視窗左邊界（包含）
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);

            // 若字元 c 在視窗內出現過，將左邊界右移到「上一次 c 的位置 + 1」
            if (last[c] >= left) {
                left = last[c] + 1;
            }

            // 更新 c 的最後出現位置
            last[c] = i;
            // 更新最大長度
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 輸出 3
        System.out.println(lengthOfLongestSubstring("bbbbb")); // 輸出 1
        System.out.println(lengthOfLongestSubstring("pwwkew")); // 輸出 3
    }
}

/*
 * 解題思路：
 * 1. 使用滑動視窗維護不含重複字元的子字串。
 * 2. 建立陣列 last[c]，紀錄每個字元最後一次出現的位置。
 * 3. 當 s[i] 在視窗內重複時，將左邊界 left 移動到 last[s[i]] + 1。
 * 4. 每次更新當前最長長度 = i - left + 1。
 * 5. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
