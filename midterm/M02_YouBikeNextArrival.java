package midterm;

import java.util.Scanner;

public class M02_YouBikeNextArrival {
    public static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public static String findNextArrival(String[] times, int n, int queryTime) {
        int left = 0, right = n - 1;
        String result = "No bike";

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midTime = timeToMinutes(times[mid]);

            if (midTime > queryTime) {
                result = times[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        String[] times = new String[n];
        for (int i = 0; i < n; i++) {
            times[i] = scanner.nextLine();
        }

        String query = scanner.nextLine();
        int queryTime = timeToMinutes(query);

        System.out.println(findNextArrival(times, n, queryTime));

        scanner.close();
    }
}