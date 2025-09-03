package LeetCode;

// 題目：Valid Parentheses
// 給定一個字串，包含 '(', ')', '{', '}', '[' 和 ']'，判斷該字串是否有效。

import java.util.Stack;

public class lt_20_ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        // 遍歷字串中的每個字符
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 如果是開括號，將其推入棧
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // 如果是閉括號，檢查棧頂是否匹配
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // 最後檢查棧是否為空，若空則為有效，否則為無效
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // 測試用例
        System.out.println(isValid("()")); // true
        System.out.println(isValid("()[]{}")); // true
        System.out.println(isValid("(]")); // false
        System.out.println(isValid("([)]")); // false
        System.out.println(isValid("{[]}")); // true
    }
}

/*
 * 解題思路：
 * 1. 題目要求判斷括號是否匹配，使用棧來解決這個問題。
 * 2. 遍歷字串中的每個字符，對於開括號，將其推入棧；對於閉括號，則檢查棧頂是否為對應的開括號。
 * 3. 若結束後棧為空，則所有的括號都有對應的匹配，返回 true，否則返回 false。
 * 4. 時間複雜度 O(n)，其中 n 是字串的長度，每個字符只處理一次。
 * 5. 空間複雜度 O(n)，在最壞情況下，棧需要存儲所有的開括號。
 */
