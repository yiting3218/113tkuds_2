package midterm;

public class M06_PalindromeClean {

    public static boolean isPalindrome(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z]", "").toLowerCase();

        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = new java.util.Scanner(System.in).nextLine();

        if (isPalindrome(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
