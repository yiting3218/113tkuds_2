package LeetCode;

// 題目：String to Integer (atoi)
// 給定一個字串 s，將其轉換為 32 位有符號整數。

public class lt_08_StringtoInteger {
    public static int myAtoi(String s) {
        int i = 0, n = s.length();
        long result = 0;
        int sign = 1;

        // 步驟 1：跳過前導空白字符
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 步驟 2：處理符號
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 步驟 3：處理數字
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            i++;

            // 步驟 4：檢查是否超出範圍
            if (result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }

        return (int) result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42")); // 42
        System.out.println(myAtoi("   -42")); // -42
        System.out.println(myAtoi("4193 with words")); // 4193
        System.out.println(myAtoi("words and 987")); // 0
        System.out.println(myAtoi("-91283472332")); // -2147483648 (溢出)
    }
}

/*
 * 解題思路：
 * 1. 題目要求將字串轉換為 32 位整數，並處理前導空白、符號、數字及範圍溢出。
 * 2. 步驟 1：先跳過所有前導空白字符。
 * 3. 步驟 2：處理符號，若有 "-" 則結果為負，若有 "+" 則結果為正。
 * 4. 步驟 3：讀取數字字符，並組合成整數。
 * 5. 步驟 4：若結果超過 32 位整數範圍，則返回該範圍的最大值或最小值。
 * 6. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
