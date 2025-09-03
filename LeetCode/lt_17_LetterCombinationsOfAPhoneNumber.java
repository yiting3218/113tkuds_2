package LeetCode;

// 題目：Letter Combinations of a Phone Number
// 給定一個數字字串，返回所有可能的字母組合，這些字母可以由該數字所代表的字母組成。

import java.util.*;

public class lt_17_LetterCombinationsOfAPhoneNumber {
    public static List<String> letterCombinations(String digits) {
        // 若輸入空字串，則直接返回空列表
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        // 定義數字到字母的對應關係
        String[] mapping = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        List<String> result = new ArrayList<>();
        backtrack(result, mapping, digits, 0, new StringBuilder());
        return result;
    }

    // 回溯方法來生成所有字母組合
    private static void backtrack(List<String> result, String[] mapping, String digits, int index,
            StringBuilder current) {
        // 若當前字串長度等於 digits 長度，則加入結果
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 獲取當前 digit 對應的字母組
        String letters = mapping[digits.charAt(index) - '0'];

        // 遍歷當前字母組，生成所有字母組合
        for (char c : letters.toCharArray()) {
            current.append(c); // 添加當前字母
            backtrack(result, mapping, digits, index + 1, current); // 進入下一層
            current.deleteCharAt(current.length() - 1); // 移除當前字母，回溯
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23")); // [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println(letterCombinations("")); // []
        System.out.println(letterCombinations("2")); // [a, b, c]
    }
}

/*
 * 解題思路：
 * 1. 題目要求根據數字字串生成所有可能的字母組合。
 * 2. 使用回溯法生成所有組合，每次對應一個數字，選擇對應字母，並遞歸處理。
 * 3. 當當前字母組合達到與輸入字串長度相同時，將其加入結果列表。
 * 4. 時間複雜度 O(4^n)，其中 n 是字串長度。由於每個數字最多有 4 個字母對應，空間複雜度 O(n)。
 */
