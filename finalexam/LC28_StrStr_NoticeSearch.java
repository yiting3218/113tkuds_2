package finalexam;

// 題目：公告全文搜尋
// 回傳 needle 在 haystack 中首次出現的起始索引；不存在回 -1。
import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.hasNextLine() ? sc.nextLine() : "";
        String needle = sc.hasNextLine() ? sc.nextLine() : "";
        System.out.println(strStr(haystack, needle));
        sc.close();
    }

    // 使用 KMP（Knuth-Morris-Pratt）避免重複比對
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (needle.length() > haystack.length())
            return -1;

        int[] lps = buildLPS(needle); // 最長相等前後綴表
        int i = 0, j = 0; // i: haystack 指標, j: needle 指標

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) {
                    return i - j; // 完整匹配
                }
            } else {
                if (j > 0) {
                    j = lps[j - 1]; // 回退到可延續的位置
                } else {
                    i++; // 無法回退時，前進 haystack
                }
            }
        }
        return -1;
    }

    // 建立 LPS（Longest Prefix Suffix）表：每個位置的最長相等前後綴長度
    private static int[] buildLPS(String p) {
        int n = p.length();
        int[] lps = new int[n];
        int len = 0; // 目前匹配的前綴長度
        for (int i = 1; i < n;) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1]; // 縮短前綴，嘗試次佳
            } else {
                lps[i++] = 0; // 無法延續
            }
        }
        return lps;
    }
}
