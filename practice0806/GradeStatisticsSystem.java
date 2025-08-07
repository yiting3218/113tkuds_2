package practice0806;

public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = { 85, 92, 78, 96, 87, 73, 89, 94, 82, 90 };

        int sum = 0;
        int max = scores[0];
        int min = scores[0];

        for (int score : scores) {
            sum += score;
            if (score > max)
                max = score;
            if (score < min)
                min = score;
        }

        double average = (double) sum / scores.length;

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int score : scores) {
            if (score >= 90)
                countA++;
            else if (score >= 80)
                countB++;
            else if (score >= 70)
                countC++;
            else if (score >= 60)
                countD++;
            else
                countF++;
        }

        int countAboveAverage = 0;
        for (int score : scores) {
            if (score > average)
                countAboveAverage++;
        }

        System.out.println("=== 成績報表 ===");
        System.out.println("原始成績：");
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("學生 %2d：%d\n", i + 1, scores[i]);
        }

        System.out.printf("\n平均成績：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);

        System.out.println("\n等第分布：");
        System.out.println("A (90~100)：" + countA + " 人");
        System.out.println("B (80~89)：" + countB + " 人");
        System.out.println("C (70~79)：" + countC + " 人");
        System.out.println("D (60~69)：" + countD + " 人");
        System.out.println("F ( 0~59)：" + countF + " 人");

        System.out.println("\n高於平均成績的人數：" + countAboveAverage + " 人");
    }
}
