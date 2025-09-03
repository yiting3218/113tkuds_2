package LeetCode;

import java.util.Map;
import java.util.HashMap;

// 題目：Roman to Integer
// 給定一個羅馬數字，將其轉換為整數。

public class lt_13_RomanToInteger {
    public static int romanToInt(String s) {
        // 定義羅馬數字的符號與對應數值
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = romanMap.get(s.charAt(i));
            if (i + 1 < s.length() && current < romanMap.get(s.charAt(i + 1))) {
                // 若當前數字小於下個數字，則要減去當前數字
                result -= current;
            } else {
                result += current;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III")); // 3
        System.out.println(romanToInt("IV")); // 4
        System.out.println(romanToInt("IX")); // 9
        System.out.println(romanToInt("LVIII")); // 58
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }
}

/*
 * 解題思路：
 * 1. 根據羅馬數字的規則，當前數字若小於後一數字，則需從總和中減去該數字（例如 IV 為 4）。
 * 2. 遍歷字串，根據規則計算數字總和。
 * 3. 時間複雜度 O(n)，空間複雜度 O(1)，因為羅馬符號數量固定且只需要一次遍歷。
 */
