package midterm;

/*
 * 時間複雜度: O(log(min(a, b)))
 * 說明：遞迴的歐幾里得算法計算 GCD，每次遞迴將問題規模減半，因此時間複雜度為 O(log(min(a, b)))。
 */

import java.util.Scanner;

public class M05_GCD_LCM_Recursive {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static int lcm(int a, int b, int gcd) {
        return a / gcd * b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int gcd = gcd(a, b);

        int lcm = lcm(a, b, gcd);

        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);

        scanner.close();
    }
}
