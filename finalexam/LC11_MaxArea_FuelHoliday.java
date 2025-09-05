package finalexam;

// 題目：連假油量促銷最大區間
// 給定整數陣列 heights，選兩個位置形成最大 (j-i)*min(height[i], height[j])。
import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        System.out.println(maxArea(heights));
        sc.close();
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int area = h * (right - left);
            ans = Math.max(ans, area);
            // 移動較短邊，因為提升短邊才有可能增加面積
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
