package practice0806;

public class RecursiveMathCalculator {

    /**
     * 計算組合數 C(n, k)
     * C(n, k) = C(n-1, k-1) + C(n-1, k)
     * 停止條件：C(n, 0) = C(n, n) = 1
     */
    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    /**
     * 計算卡塔蘭數 Catalan(n)
     * C(n) = Σ C(i) * C(n-1-i)，i從0到n-1
     * 停止條件：C(0) = 1
     */
    public static long catalan(int n) {
        if (n == 0) {
            return 1;
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }

        return result;
    }

    /**
     * 漢諾塔問題的最少移動步數
     * hanoi(n) = 2 × hanoi(n-1) + 1
     * 停止條件：n = 1 時步數為 1
     */
    public static long hanoiMoves(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * hanoiMoves(n - 1) + 1;
    }

    /**
     * 判斷數字是否為回文數
     * 使用字串反轉方式
     */
    public static boolean isPalindromeNumber(int number) {
        String str = String.valueOf(number);
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    private static boolean isPalindromeHelper(String str, int start, int end) {
        if (start >= end)
            return true;
        if (str.charAt(start) != str.charAt(end))
            return false;
        return isPalindromeHelper(str, start + 1, end - 1);
    }

    public static void main(String[] args) {
        System.out.println("=== 組合數 C(n, k) ===");
        System.out.println("C(5, 2) = " + combination(5, 2));
        System.out.println("C(6, 3) = " + combination(6, 3));

        System.out.println("\n=== 卡塔蘭數 Catalan(n) ===");
        for (int i = 0; i <= 10; i++) {
            System.out.printf("Catalan(%d) = %d\n", i, catalan(i));
        }

        System.out.println("\n=== 漢諾塔最少步數 ===");
        for (int n = 1; n <= 5; n++) {
            System.out.printf("hanoiMoves(%d) = %d\n", n, hanoiMoves(n));
        }

        System.out.println("\n=== 數字是否為回文 ===");
        int[] testNumbers = { 121, 12321, 1001, 1234, 1221, 99899 };
        for (int num : testNumbers) {
            System.out.printf("%d 是回文數？%s\n", num, isPalindromeNumber(num));
        }
    }
}
