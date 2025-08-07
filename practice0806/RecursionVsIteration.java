package practice0806;

import java.util.Stack;

public class RecursionVsIteration {

    /*** 一、計算二項式係數 C(n, k) ***/

    // 遞迴版本：C(n, k) = C(n-1, k-1) + C(n-1, k)
    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    // 迭代版本：使用 DP（動態規劃）
    public static int binomialIterative(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            int maxK = Math.min(i, k);
            for (int j = 0; j <= maxK; j++) {
                if (j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    /*** 二、陣列中所有元素的乘積 ***/

    // 遞迴版本
    public static int productRecursive(int[] arr, int index) {
        if (index >= arr.length)
            return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    // 迭代版本
    public static int productIterative(int[] arr) {
        int product = 1;
        for (int num : arr) {
            product *= num;
        }
        return product;
    }

    /*** 三、字串中元音字母的數量 ***/

    // 遞迴版本
    public static int countVowelsRecursive(String str, int index) {
        if (index >= str.length())
            return 0;
        char ch = Character.toLowerCase(str.charAt(index));
        int count = (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') ? 1 : 0;
        return count + countVowelsRecursive(str, index + 1);
    }

    // 迭代版本
    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char ch : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(ch) != -1)
                count++;
        }
        return count;
    }

    /*** 四、檢查括號是否配對正確 ***/

    // 遞迴版本（只處理 ()）
    public static boolean isParenthesesBalancedRecursive(String str) {
        return checkBalanced(str, 0, 0);
    }

    private static boolean checkBalanced(String str, int index, int openCount) {
        if (index == str.length())
            return openCount == 0;
        char ch = str.charAt(index);
        if (ch == '(') {
            return checkBalanced(str, index + 1, openCount + 1);
        } else if (ch == ')') {
            if (openCount <= 0)
                return false;
            return checkBalanced(str, index + 1, openCount - 1);
        } else {
            return checkBalanced(str, index + 1, openCount); // 忽略其他字符
        }
    }

    // 迭代版本
    public static boolean isParenthesesBalancedIterative(String str) {
        int balance = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '(')
                balance++;
            else if (ch == ')') {
                if (balance <= 0)
                    return false;
                balance--;
            }
        }
        return balance == 0;
    }

    /*** 主程式 ***/
    public static void main(String[] args) {
        // 1. 二項式係數
        int n = 10, k = 4;
        System.out.println("=== 二項式係數 C(n, k) ===");
        System.out.println("遞迴版本：" + binomialRecursive(n, k));
        System.out.println("迭代版本：" + binomialIterative(n, k));

        // 2. 陣列乘積
        int[] numbers = { 2, 3, 4, 5 };
        System.out.println("\n=== 陣列乘積 ===");
        System.out.println("遞迴版本：" + productRecursive(numbers, 0));
        System.out.println("迭代版本：" + productIterative(numbers));

        // 3. 元音數量
        String text = "Recursion And Iteration Are Fun!";
        System.out.println("\n=== 元音字母數量 ===");
        System.out.println("遞迴版本：" + countVowelsRecursive(text, 0));
        System.out.println("迭代版本：" + countVowelsIterative(text));

        // 4. 括號配對
        String brackets = "((a + b) * (c - d))";
        String invalid = "((a + b) * (c - d)";
        System.out.println("\n=== 括號配對 ===");
        System.out.println("正確配對（遞迴）：" + isParenthesesBalancedRecursive(brackets));
        System.out.println("正確配對（迭代）：" + isParenthesesBalancedIterative(brackets));
        System.out.println("錯誤配對（遞迴）：" + isParenthesesBalancedRecursive(invalid));
        System.out.println("錯誤配對（迭代）：" + isParenthesesBalancedIterative(invalid));
    }
}
