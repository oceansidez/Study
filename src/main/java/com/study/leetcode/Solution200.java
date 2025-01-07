package com.study.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/number-of-islands/
 * 1905. 统计子岛屿
 * 解法：
 */
public class Solution200 {
    // 使用 dfs
    public static int numIslands(char[][] grid) {
        int res = 0;
        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j, row, col);
                }
            }
        }
        return res;
    }

    public static void dfs(char[][] grid, int x, int y, int row, int col) {
        if (x < 0 || y < 0 || x >= row || y >= col || grid[x][y] == '0') {
            return;
        }
        // 条件
        // 将上下左右的1变为0
        if (grid[x][y] == '1') {
            grid[x][y] = '0';
        }
        dfs(grid, x + 1, y, row, col);
        dfs(grid, x - 1, y, row, col);
        dfs(grid, x, y + 1, row, col);
        dfs(grid, x, y - 1, row, col);
    }


    // 使用 bfs
    public static int numIslands2(char[][] grid) {
        int res = 0;
        int row = grid.length;
        int col = grid[0].length;
        if (grid == null || grid.length == 0) {
            return 0;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    bfs(grid, i, j, row, col);
                }
            }
        }
        return res;
    }

    // bfs, 每次计算后需要变更x、y
    private static void bfs(char[][] grid, int x, int y, int row, int col) {
        Queue<Integer> queue = new LinkedList<>();
        grid[x][y] = '0';
        queue.offer(col * x + y);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            x = poll / col;
            y = poll % col;
            if (x - 1 >= 0 && grid[x - 1][y] == '1') {
                grid[x - 1][y] = '0';
                x = x - 1;
                queue.offer(col * x + y);
            }
            if (x + 1 < row && grid[x + 1][y] == '1') {
                grid[x + 1][y] = '0';
                x = x + 1;
                queue.offer(col * x + y);
            }
            if (y - 1 >= 0 && grid[x][y - 1] == '1') {
                grid[x][y - 1] = '0';
                y = y - 1;
                queue.offer(col * x + y);
            }
            if (y + 1 < col && grid[x][y + 1] == '1') {
                grid[x][y + 1] = '0';
                y = y + 1;
                queue.offer(col * x + y);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int i = numIslands(grid);
        System.out.println("i = " + i);
    }
}
