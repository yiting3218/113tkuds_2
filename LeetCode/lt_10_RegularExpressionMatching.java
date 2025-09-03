package LeetCode;

// 題目：Regular Expression Matching
// 給定一個輸入字串 s 和一個模式 p，實現正規表達式匹配，其中 '.' 匹配任何單一字元，
// '*' 匹配零個或多個前面元素的字元，要求完全匹配整個字串（而非部分匹配）。

public class lt_10_RegularExpressionMatching {
    public static boolean isMatch(String s, String p) {
        // dp[i][j] 代表 s[0...i-1] 和 p[0...j-1] 是否匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true; // 空字串與空模式匹配

        // 處理模式 p 中以 * 開頭的情況
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; // * 表示匹配前一個字符 0 次
            }
        }

        // 填充 dp 表格
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1]; // 直接匹配
                } else if (p.charAt(j - 1) == '*') {
                    // * 匹配前一個字符 0 次、1 次或多次
                    dp[i][j] = dp[i][j - 2]
                            || (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a")); // false
        System.out.println(isMatch("aa", "a*")); // true
        System.out.println(isMatch("ab", ".*")); // true
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false
    }
}

/*
 * 解題思路：
 * 1. 使用動態規劃（DP），定義 dp[i][j] 為 s[0...i-1] 和 p[0...j-1] 是否匹配。
 * 2. 當 p[j-1] 為 '*' 時，根據 * 的特性進行兩種情況的判斷：
 * 匹配 0 次前一字符：檢查 dp[i][j-2]。
 * 匹配 1 次或多次前一字符：檢查 dp[i-1][j] 並且確保當前字符與前一字符匹配（即 s[i-1] == p[j-2] 或 p[j-2] 是
 * '.'）。
 * 3. 時間複雜度 O(m * n)，空間複雜度 O(m * n)，m 和 n 分別是 s 和 p 的長度。
 */
