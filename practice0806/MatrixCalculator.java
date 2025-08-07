package practice0806;

import java.util.Arrays;

public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] matrixA = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };

        int[][] matrixB = {
                { 7, 8, 9 },
                { 10, 11, 12 }
        };

        int[][] matrixC = {
                { 1, 2 },
                { 3, 4 },
                { 5, 6 }
        };

        System.out.println("=== 矩陣 A ===");
        printMatrix(matrixA);

        System.out.println("\n=== 矩陣 B ===");
        printMatrix(matrixB);

        System.out.println("\n=== A + B ===");
        printMatrix(addMatrix(matrixA, matrixB));

        System.out.println("\n=== A × C ===");
        printMatrix(multiplyMatrix(matrixA, matrixC));

        System.out.println("\n=== A 的轉置 ===");
        printMatrix(transposeMatrix(matrixA));

        System.out.println("\n=== A 的最大值與最小值 ===");
        findMinMax(matrixA);
    }

    // 矩陣加法
    public static int[][] addMatrix(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bCols = b[0].length;

        int[][] result = new int[aRows][bCols];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }

    // 尋找最大值與最小值
    public static void findMinMax(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max)
                    max = value;
                if (value < min)
                    min = value;
            }
        }

        System.out.println("最大值：" + max);
        System.out.println("最小值：" + min);
    }

    // 印出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
