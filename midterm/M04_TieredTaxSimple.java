package midterm;

/*
 * 時間複雜度: O(n)
 * 說明：遍歷每個收入一次，根據級距計算稅額，總時間複雜度為 O(n)。
 */

import java.util.Scanner;

public class M04_TieredTaxSimple {

    public static int calculateTax(int income) {
        int tax = 0;

        if (income <= 120000) {
            tax = (int) (income * 0.05);
        } else if (income <= 500000) {
            tax = (int) (120000 * 0.05 + (income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax = (int) (120000 * 0.05 + 380000 * 0.12 + (income - 500000) * 0.20);
        } else {
            tax = (int) (120000 * 0.05 + 380000 * 0.12 + 500000 * 0.20 + (income - 1000000) * 0.30);
        }

        return tax;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int totalTax = 0;

        for (int i = 0; i < n; i++) {
            int income = scanner.nextInt();
            int tax = calculateTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        int averageTax = totalTax / n;
        System.out.println("Average: " + averageTax);

        scanner.close();
    }
}
