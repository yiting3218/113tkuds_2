package LeetCode;

// 題目：Find the Index of the First Occurrence in a String
// 給定兩個字符串 needle 和 haystack，返回 needle 在 haystack 中第一次出現的索引位置，
// 如果 needle 不存在於 haystack 中，則返回 -1。

class lt_28_FindtheIndexoftheFirstOccurrenceinaString {
    public int strStr(String haystack, String needle) {
        // 若 needle 為空字符串，直接返回 0
        if (needle.isEmpty()) {
            return 0;
        }
        // 使用內建的 indexOf 函數，查找第一次出現的位置
        return haystack.indexOf(needle);
    }
}

/*
 * 解題思路：
 * 1. 題目要求返回 needle 在 haystack 中第一次出現的索引，若不存在則返回 -1。
 * 2. 利用 Java 字符串的 indexOf 函數來實現，該方法會返回子字符串第一次出現的索引，如果找不到該子字符串則返回 -1。
 * 3. 時間複雜度 O(n)，其中 n 是 haystack 字符串的長度。
 * 4. 空間複雜度 O(1)，只使用常數空間。
 */
