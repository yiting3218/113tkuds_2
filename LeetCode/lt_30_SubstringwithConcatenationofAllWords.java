package LeetCode;

// 題目：Substring with Concatenation of All Words
// 給定字串 s 和一個字串陣列 words，求所有包含 words 中所有字串排列的子字串的起始索引。

class lt_30_SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int wordCount = words.length;
        int totalLength = wordLength * wordCount;

        // 建立字典，儲存每個字串在 words 中出現的次數
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // 用來檢查當前窗口是否包含所有 words 的字串
        for (int i = 0; i < wordLength; i++) {
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> currentMap = new HashMap<>();

            // 當右指針掃描字串 s
            while (right + wordLength <= s.length()) {
                String word = s.substring(right, right + wordLength);
                right += wordLength;

                // 如果當前字串是 words 中的字串，則處理
                if (wordMap.containsKey(word)) {
                    currentMap.put(word, currentMap.getOrDefault(word, 0) + 1);
                    count++;

                    // 當當前字串數量超過所需的數量時，左指針移動，縮小窗口
                    while (currentMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                        left += wordLength;
                        count--;
                    }

                    // 當窗口中的字串數量與 words 中的字串數量一致時，記錄起始位置
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // 當當前字串不是 words 中的字串，重置窗口
                    currentMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return result;
    }
}
/*
 * 解題思路：
 * 1. 先將每個 word 記錄在字典中，方便檢查字串是否出現過。
 * 2. 用雙指針方法，掃描字符串，並對每個起點檢查是否能找到符合條件的子字串。
 * 3. 使用滑動窗口的方式，保證每次找到的字串正好是 words 的排列，並且不會有重複。
 */
