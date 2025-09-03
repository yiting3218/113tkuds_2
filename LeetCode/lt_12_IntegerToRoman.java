package LeetCode;

// 題目：Integer to Roman
// 給定一個整數 num，將其轉換為羅馬數字。

public class lt_12_IntegerToRoman {
    public static String intToRoman(int num) {
        // 定義羅馬數字的值與對應符號
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            // 每次取出最大可用的羅馬數字，直到 num 小於該數字
            while (num >= values[i]) {
                result.append(symbols[i]); // 將對應的羅馬符號加入結果
                num -= values[i]; // 減去相應的數值
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3749)); // MMMCDXLIX
        System.out.println(intToRoman(1994)); // MCMXCIV
        System.out.println(intToRoman(58)); // LVIII
        System.out.println(intToRoman(9)); // IX
    }
}

/*
 * 解題思路：
 * 1. 題目要求將一個整數轉換為羅馬數字，羅馬數字由若干個字符組成，並且有對應的規則。
 * 2. 使用數組 values 存儲羅馬數字的數值，symbols 存儲對應的符號。
 * 3. 從最大值開始，每次將該數字的符號加入結果，直到 num 小於該數字，然後繼續處理較小的數字。
 * 4. 時間複雜度 O(1)，空間複雜度 O(1)，因為數組大小固定且遍歷次數有限。
 */
