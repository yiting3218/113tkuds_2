package LeetCode;

// 題目：Palindrome Number
// 給定整數 x，若 x 是回文數，返回 true，否則返回 false。

public class lt_09_PalindromeNumber {
    public static boolean isPalindrome(int x) {
        // 如果 x 是負數或以 0 開頭的非 0 整數，直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10; // 反轉數字的後半部分
            x /= 10;
        }

        // 若數字長度是奇數，去掉中間的數字再比較
        return x == reversed || x == reversed / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121)); // true
        System.out.println(isPalindrome(-121)); // false
        System.out.println(isPalindrome(10)); // false
        System.out.println(isPalindrome(1221)); // true
    }
}

/*
 * 解題思路：
 * 1. 題目要求判斷一個數字是否為回文數。
 * 2. 若 x 是負數或以 0 開頭的非 0 整數，則一定不是回文數，直接返回 false。
 * 3. 反轉數字的後半部分，並與原數字的前半部分比較。
 * 4. 若數字長度是奇數，則去掉中間的數字再比較。
 * 5. 時間複雜度 O(log(x))，空間複雜度 O(1)。
 */
