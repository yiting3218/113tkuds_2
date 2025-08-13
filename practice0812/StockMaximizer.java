package practice0812;

import java.util.*;

public class StockMaximizer {

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0)
            return 0;
        if (k >= n / 2) {
            int sum = 0;
            for (int i = 1; i < n; i++)
                if (prices[i] > prices[i - 1])
                    sum += prices[i] - prices[i - 1];
            return sum;
        }
        int[] prev = new int[n];
        int[] curr = new int[n];
        for (int t = 1; t <= k; t++) {
            int maxDiff = -prices[0];
            for (int i = 1; i < n; i++) {
                curr[i] = Math.max(curr[i - 1], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, prev[i] - prices[i]);
            }
            int[] tmp = prev;
            prev = curr;
            curr = tmp;
            Arrays.fill(curr, 0);
        }
        return prev[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[] { 2, 4, 1 }));
        System.out.println(maxProfit(2, new int[] { 3, 2, 6, 5, 0, 3 }));
        System.out.println(maxProfit(2, new int[] { 1, 2, 3, 4, 5 }));
    }
}
