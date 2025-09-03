package LeetCode;

// 題目：Zigzag Conversion
// 將字串 s 依照給定的行數 numRows 排列成 Z 字型，再逐行讀取輸出。

public class lt_06_ZigzagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)
            return s;
        // 建立 numRows 個 StringBuilder 來模擬每一行

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            rows[i] = new StringBuilder();

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[curRow].append(c);
            if (curRow == 0 || curRow == numRows - 1)
                goingDown = !goingDown; // 轉向
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows)
            res.append(row);
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(convert("A", 1)); // A
    }
}

/*
 * 解題思路：
 * 1. 題目要求將字串以 Z 字型排列後逐行讀取。
 * 2. 建立 numRows 個 StringBuilder，模擬每一行。
 * 3. 使用 curRow 追蹤當前行，goingDown 控制方向。
 * 4. 每個字元放入對應行，遇到最上行或最下行時改變方向。
 * 5. 將所有行依序合併即為結果。
 * 6. 時間複雜度 O(n)，空間複雜度 O(n)。
 */
