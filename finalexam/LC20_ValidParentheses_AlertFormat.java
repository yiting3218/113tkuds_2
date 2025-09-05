package finalexam;

// 題目：緊急通報格式括號檢查
// 給定一個只包含 ()[]{} 的字串，檢查是否正確配對與巢狀。
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(isValid(s));
        sc.close();
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                // ch 是閉括號，檢查棧頂是否匹配
                if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            } else {
                // 開括號直接推入棧
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
