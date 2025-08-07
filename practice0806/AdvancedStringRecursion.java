package practice0806;

import java.util.*;

public class AdvancedStringRecursion {

    /**
     * 遞迴產生所有排列組合（不重複字元）
     */
    public static void generatePermutations(String str) {
        System.out.println("=== 所有排列組合 ===");
        permuteHelper("", str);
    }

    private static void permuteHelper(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            String newPrefix = prefix + remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            permuteHelper(newPrefix, newRemaining);
        }
    }

    /**
     * 遞迴實作字串匹配（子字串比對）
     * 回傳是否 s 中有包含 pattern
     */
    public static boolean recursiveMatch(String s, String pattern, int sIndex, int pIndex) {
        if (pIndex == pattern.length())
            return true; // 成功比對整個 pattern
        if (sIndex == s.length())
            return false; // s 用完還沒成功

        if (s.charAt(sIndex) == pattern.charAt(pIndex)) {
            return recursiveMatch(s, pattern, sIndex + 1, pIndex + 1);
        } else {
            return recursiveMatch(s, pattern, sIndex + 1, 0); // pattern 從頭開始比對
        }
    }

    /**
     * 遞迴移除重複字符
     */
    public static String removeDuplicates(String str) {
        return removeDupHelper(str, 0, new HashSet<>());
    }

    private static String removeDupHelper(String str, int index, Set<Character> seen) {
        if (index >= str.length())
            return "";
        char current = str.charAt(index);
        if (seen.contains(current)) {
            return removeDupHelper(str, index + 1, seen);
        } else {
            seen.add(current);
            return current + removeDupHelper(str, index + 1, seen);
        }
    }

    /**
     * 遞迴列出所有子字串（子區間）
     */
    public static void generateSubstrings(String str) {
        System.out.println("=== 所有子字串 ===");
        substrHelper(str, 0, "");
    }

    private static void substrHelper(String str, int index, String current) {
        if (index == str.length())
            return;

        for (int i = index + 1; i <= str.length(); i++) {
            String sub = str.substring(index, i);
            System.out.println(sub);
            substrHelper(str, i, sub);
        }
    }

    public static void main(String[] args) {
        String test = "abc";
        String text = "abcde";
        String pattern = "cde";

        generatePermutations(test);

        System.out.println("\n=== 字串匹配 ===");
        boolean matchResult = recursiveMatch(text, pattern, 0, 0);
        System.out.printf("'%s' 是否包含 '%s'：%s\n", text, pattern, matchResult);

        System.out.println("\n=== 移除重複字符 ===");
        String withDup = "banana";
        String noDup = removeDuplicates(withDup);
        System.out.printf("'%s' 去除重複後：%s\n", withDup, noDup);

        System.out.println("\n=== 所有子字串 ===");
        generateSubstrings("abc");
    }
}
