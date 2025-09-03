package LeetCode;

// 題目：Longest Common Prefix
// 給定一個字串陣列 strs，找出它們的最長公共前綴。
// 如果沒有共同前綴，返回空字串 ""。

public class lt_14_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        String prefix = strs[0]; // 假設第一個字串是最長公共前綴
        for (int i = 1; i < strs.length; i++) {
            // 將前綴與後面的字串進行比較
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1); // 去掉一個字元，直到匹配
                if (prefix.isEmpty())
                    return ""; // 若無公共前綴，返回空字串
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] { "flower", "flow", "flight" })); // fl
        System.out.println(longestCommonPrefix(new String[] { "dog", "racecar", "car" })); // ""
        System.out.println(longestCommonPrefix(new String[] { "intelligence", "interact", "intermediate" })); // "inter"
    }
}

/*
 * 解題思路：
 * 1. 假設第一個字串是最長公共前綴，逐步與其他字串進行比較。
 * 2. 使用 `indexOf` 判斷當前前綴是否為該字串的開頭，若不是則縮小前綴長度。
 * 3. 若逐步縮小後前綴變為空字串，則返回空字串。
 * 4. 時間複雜度 O(n*m)，其中 n 是字串數量，m 是字串的最大長度。
 * 5. 空間複雜度 O(1)，因為只使用額外的前綴變數。
 */
