package LeetCode;

// 題目：Container With Most Water
// 給定一個整數陣列 height，代表 n 條垂直線，找出兩條線和 x 軸形成的容器，能夠存最多水。

public class lt_11_ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int area = h * (right - left);
            maxArea = Math.max(maxArea, area);

            // 移動較短的那條線
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 })); // 49
        System.out.println(maxArea(new int[] { 1, 1 })); // 1
        System.out.println(maxArea(new int[] { 4, 3, 2, 1, 4 })); // 16
        System.out.println(maxArea(new int[] { 1, 2, 1 })); // 2
    }
}

/*
 * 解題思路：
 * 1. 題目要求找到最大水面積，使用雙指標從左右兩邊開始，計算每次的面積並移動較短的指標。
 * 2. 左指標和右指標之間的水面積是由高度較小的那條線決定，且區間越大面積越大。
 * 3. 使用雙指標縮小區間，每次移動較短的線，直到兩個指標相遇。
 * 4. 時間複雜度 O(n)，空間複雜度 O(1)。
 */
