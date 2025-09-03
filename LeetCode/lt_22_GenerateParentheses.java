package LeetCode;

// 題目：Generate Parentheses
// 給定 n 對括號，生成所有有效的括號組合。

import java.util.*;

public class lt_22_GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // 回溯法生成有效的括號組合
    private static void backtrack(List<String> result, String current, int open, int close, int n) {
        // 如果當前組合的長度達到 2 * n，則加入結果
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // 如果左括號數量小於 n，則可以添加左括號
        if (open < n) {
            backtrack(result, current + "(", open + 1, close, n);
        }

        // 如果右括號數量小於左括號數量，則可以添加右括號
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, n);
        }
    }

    public static void main(String[] args) {
        // 測試用例
        List<String> result = generateParenthesis(3);
        for (String str : result) {
            System.out.println(str);
        }
    }
}

/*
 * 解題思路：
 * 1. 題目要求生成所有有效的括號組合，我們可以使用回溯法來解決。
 * 2. 定義兩個變數 open 和 close 分別表示當前已經使用的左括號和右括號數量。
 * 3. 每次遞迴時，若左括號數量小於 n，則可以繼續添加左括號；若右括號數量小於左括號數量，則可以繼續添加右括號。
 * 4. 當括號組合長度達到 2 * n 時，則把當前組合加入結果。
 * 5. 時間複雜度 O(4^n / sqrt(n))，空間複雜度 O(n)，由於遞迴深度為 n。
 */
