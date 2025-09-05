package LeetCode;

// 題目：Longest Valid Parentheses
// 給定只包含 '(' 和 ')' 的字串，回傳最長合法括號子字串的長度。
import java.util.*;

class lt_32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int ans = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1); // 基準索引，方便計算長度

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i); // 記錄左括號位置
            } else {
                st.pop(); // 嘗試配對一個左括號/基準
                if (st.isEmpty()) {
                    st.push(i); // 無法配對，重設基準
                } else {
                    ans = Math.max(ans, i - st.peek()); // 以目前基準計算有效長度
                }
            }
        }
        return ans;
    }
}
/*
 * 解題思路：
 * 1. 以棧保存未匹配的索引，先壓入 -1 當基準。
 * 2. '(' 進棧；')' 先彈出，若棧空則把當前索引設為新基準，否則以 i - 棧頂得當前合法長度。
 * 3. 時間複雜度 O(n)，空間複雜度 O(n)。
 */
