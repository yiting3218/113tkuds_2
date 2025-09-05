package LeetCode;

// 題目：Sudoku Solver
// 以原地修改的方式解出 9x9 數獨，空格以 '.' 表示。
class lt_37_SudokuSolver {
    // 三組約束表：rows[r][d]、cols[c][d]、boxes[b][d] 是否已使用數字 d(1..9)
    private final boolean[][] rows = new boolean[9][10];
    private final boolean[][] cols = new boolean[9][10];
    private final boolean[][] boxes = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        // 預處理已填數字
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.')
                    continue;
                int d = ch - '0';
                int b = boxIndex(r, c);
                rows[r][d] = cols[c][d] = boxes[b][d] = true;
            }
        }
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int r, int c) {
        // 走到下一格
        if (c == 9) {
            r++;
            c = 0;
        }
        if (r == 9)
            return true; // 全部填完

        if (board[r][c] != '.')
            return dfs(board, r, c + 1);

        int b = boxIndex(r, c);
        for (int d = 1; d <= 9; d++) {
            if (rows[r][d] || cols[c][d] || boxes[b][d])
                continue; // 不合法
            // 放入 d
            rows[r][d] = cols[c][d] = boxes[b][d] = true;
            board[r][c] = (char) ('0' + d);

            if (dfs(board, r, c + 1))
                return true; // 成功則傳遞

            // 回溯
            rows[r][d] = cols[c][d] = boxes[b][d] = false;
            board[r][c] = '.';
        }
        return false; // 此格無解，回退上一層
    }

    private int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}
/*
 * 解題思路：
 * 1. 以三組布林 rows/cols/boxes 紀錄每列、每行、每個 3x3 宮已使用的數字。
 * 2. 深度優先搜尋：從左到右、由上而下找到 '.'，嘗試填入 1..9 中符合三種約束的數字。
 * 3. 若後續無法完成則回溯還原，繼續嘗試其他數字。
 * 4. 3x3 宮索引為 (r/3)*3 + (c/3)，快速定位宮的約束。
 * 5. 最壞時間接近指數，但約束剪枝有效；空間為 O(1)（固定大小表 + 遞迴棧）。
 */
