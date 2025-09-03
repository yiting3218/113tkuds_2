package LeetCode;

// 題目：Longest Palindromic Substring
// 給定字串 s，回傳 s 中最長的回文子字串。

public class lt_05_LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return s;

        int n = s.length();
        int bestL = 0, bestR = 0;

        for (int i = 0; i < n; i++) {
            int[] odd = expand(s, i, i); // 奇數中心
            int[] even = expand(s, i, i + 1); // 偶數中心

            if (odd[1] - odd[0] > bestR - bestL) {
                bestL = odd[0];
                bestR = odd[1];
            }
            if (even[1] - even[0] > bestR - bestL) {
                bestL = even[0];
                bestR = even[1];
            }
        }
        return s.substring(bestL, bestR + 1);
    }

    // 從左右指標向外擴展，回傳最終的 [L, R]（含）
    private static int[] expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return new int[] { l + 1, r - 1 };
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // bab 或 aba
        System.out.println(longestPalindrome("cbbd")); // bb
        System.out.println(longestPalindrome("a")); // a
        System.out.println(longestPalindrome("ac")); // a 或 c
    }
}

/*
 * 解題思路：
 * 1. 使用中心擴展法：對每個位置同時嘗試「奇數中心 (i,i)」與「偶數中心 (i,i+1)」。
 * 2. 向左右擴展直到字元不同或越界，更新目前最長區間 [L,R]。
 * 3. 回傳 s.substring(L, R+1) 作為最長回文子字串。
 * 4. 時間複雜度 O(n^2)，空間複雜度 O(1)。
 */
