package finalexam;

// 題目：北捷進出站最長有效片段
// 給定只包含 '(' 與 ')' 的字串，找出最長有效（括號匹配）的子字串長度。
import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(longestValidParentheses(s));
        sc.close();
    }

    public static int longestValidParentheses(String s) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 棧底放基準

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i); // 進站事件
            } else {
                stack.pop(); // 嘗試配對出站
                if (stack.isEmpty()) {
                    stack.push(i); // 置新基準
                } else {
                    ans = Math.max(ans, i - stack.peek()); // 計算有效長度
                }
            }
        }
        return ans;
    }
}
