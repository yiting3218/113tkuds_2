package LeetCode;

// 題目：Reverse Integer
// 給定一個 32 位有符號整數 x，返回其反轉後的數字。
// 如果反轉後的數字超過 32 位有符號整數範圍，則回傳 0。

public class lt_07_ReverseInteger {
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // 檢查是否會溢出
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;

            result = result * 10 + pop;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123)); // 321
        System.out.println(reverse(-123)); // -321
        System.out.println(reverse(120)); // 21
        System.out.println(reverse(0)); // 0
        System.out.println(reverse(1534236469)); // 0 (溢出)
    }
}

/*
 * 解題思路：
 * 1. 題目要求反轉整數並處理溢出情況。
 * 2. 使用 x % 10 取出當前最低位數字，並將其加到 result 上。
 * 3. 在每次乘 10 前，先檢查是否會溢出（超過 32 位範圍）。
 * 4. 如果溢出，則返回 0。
 * 5. 時間複雜度 O(log(x))，因為每次取出一位數字，空間複雜度 O(1)。
 */
