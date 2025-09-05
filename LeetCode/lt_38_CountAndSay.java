package LeetCode;

// 題目：Count and Say
// 給定正整數 n，回傳「數數念」序列的第 n 個字串（count-and-say）。
class lt_38_CountAndSay {
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int j = 0;
            while (j < s.length()) {
                char ch = s.charAt(j);
                int k = j;
                // 計算連續相同字元的長度
                while (k < s.length() && s.charAt(k) == ch)
                    k++;
                next.append(k - j).append(ch); // 報數：次數 + 字元
                j = k;
            }
            s = next.toString();
        }
        return s;
    }
}
/*
 * 解題思路：
 * 1. 以 "1" 為起始字串，從第 2 項迭代生成到第 n 項。
 * 2. 對當前字串做 run-length encoding：數連續相同字元的個數並輸出「次數+字元」。
 * 3. 使用 StringBuilder 逐段構造下一項字串。
 * 4. 時間複雜度約為 O(總生成長度)，空間複雜度為 O(當前項長度)。
 */
