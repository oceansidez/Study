package com.study.base.leetcode;

/**
 * https://leetcode.cn/problems/count-sub-islands/description/
 * 1905. 统计子岛屿
 * 解法：
 */
public class Solution1905 {
    int row, col;
    boolean notSub;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        row = grid1.length;
        col = grid1[0].length;
        int cnt = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid2[i][j] == 1) {
                    notSub = false;
                    dfs(grid1, grid2, i, j);
                    if (!notSub) {
                        cnt++;
                    }
                }

            }
        }

        return cnt;
    }

    private void dfs(int[][] grid1, int[][] grid2, int x, int y) {
        if (x < 0 || y < 0 || x >= row || y >= col || grid2[x][y] == 0) {
            return;
        }

        if (grid1[x][y] != 1) {
            notSub = true;
        }
        grid2[x][y] = 0;
        dfs(grid1, grid2, x + 1, y);
        dfs(grid1, grid2, x - 1, y);
        dfs(grid1, grid2, x, y + 1);
        dfs(grid1, grid2, x, y - 1);
    }

    public static void main(String[] args) {
        Solution1905 solution1905 = new Solution1905();
//        int[][] grid1 = {{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}};
//        int[][] grid2 = {{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}};

        int[][] grid1 = {{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}};
        int[][] grid2 = {{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};

        int i = solution1905.countSubIslands(grid1, grid2);
        System.out.println("i = " + i);
    }
}
