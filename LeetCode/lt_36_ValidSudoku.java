package LeetCode;

// 題目：Valid Sudoku
// 判斷 9x9 的數獨盤面是否有效（僅需驗證已填入的格子），需滿足每列、每行與每個 3x3 宮都不重複出現 1-9。
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9]; // 3x3 宮索引：(r/3)*3 + (c/3)

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.')
                    continue;
                int d = ch - '1';
                int b = (r / 3) * 3 + (c / 3);

                // 若當前數字已在列/行/盒出現則無效
                if (rows[r][d] || cols[c][d] || boxes[b][d])
                    return false;

                rows[r][d] = cols[c][d] = boxes[b][d] = true;
            }
        }
        return true;
    }
}
/*
 * 解題思路：
 * 1. 使用三組 9x9 布林表分別記錄每列、每行、每個 3x3 宮是否出現過某數字。
 * 2. 掃描盤面，遇到數字 d 即檢查 rows[r][d]、cols[c][d]、boxes[b][d] 是否已標記。
 * 3. 若任一已標記代表重複，立即回傳 false；否則將三處標記為已見。
 * 4. 全部掃描無衝突則為有效盤面，回傳 true。
 * 5. 時間複雜度 O(81)，空間複雜度 O(1)（常數大小的表）。
 */
