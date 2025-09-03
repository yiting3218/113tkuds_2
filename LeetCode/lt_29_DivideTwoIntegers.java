package LeetCode;

// 題目：Divide Two Integers
// 給定兩個整數 dividend 和 divisor，請在不使用乘法、除法與 mod 運算符的情況下，求兩數相除的結果。
// 要注意結果應該是截斷零的，並且要處理溢位的情況。

class lt_29_DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // 特例處理：若除數為 0，返回最大值
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

        // 處理溢位情況：當 dividend 為 Integer.MIN_VALUE，divisor 為 -1 時，結果會超過 int 範圍
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 記錄符號，最後返回正負號的結果
        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);
        int result = 0;

        // 不斷將除數累加，直到超過被除數
        while (dividendLong >= divisorLong) {
            long temp = divisorLong;
            int multiple = 1;

            // 通過將除數翻倍來加速除法運算
            while (dividendLong >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            // 減去這一部分，並累加商
            dividendLong -= temp;
            result += multiple;
        }

        // 根據符號返回最終結果
        return (dividend < 0) ^ (divisor < 0) ? -result : result;
    }
}

/*
 * 解題思路：
 * 1. 題目要求在不使用乘法、除法與 mod 運算符的情況下實現除法，且需要處理結果的溢位情況。
 * 2. 通過將除數倍增來加速除法運算，直到被除數小於除數為止。
 * 3. 若除數為 0 或溢位情況，返回相應的特殊值。
 * 4. 時間複雜度 O(log(n))，其中 n 是被除數。每次除數翻倍能加速運算。
 * 5. 空間複雜度 O(1)，僅使用常數空間。
 */
