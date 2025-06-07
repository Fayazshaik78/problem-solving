package com.Girus.problemsolving;
//🔸 Problem 5: Matrix Islands with Diagonals
//Count the number of islands in a matrix of 0s and 1s. Islands are formed using horizontal, vertical, or diagonal connections.

public class MatrixIslandsWithDiagonals {

    public static void main(String[] args) {
        // Sample matrix input
        int[][] grid = {
            {1, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 0, 1, 1},
            {0, 0, 1, 0}
        };

        int result = countIslands(grid);
        System.out.println("✅ Number of islands: " + result);
    }

    // Function to count islands using DFS
    public static int countIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int islandCount = 0;

        // Check each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the cell is 1 and not visited
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

 
    static final int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static void dfs(int[][] grid, boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        for (int d = 0; d < 8; d++) {
            int newRow = row + dRow[d];
            int newCol = col + dCol[d];

            if (isSafe(grid, visited, newRow, newCol)) {
                dfs(grid, visited, newRow, newCol);
            }
        }
    }
    private static boolean isSafe(int[][] grid, boolean[][] visited, int r, int c) {
        return r >= 0 && r < grid.length &&
               c >= 0 && c < grid[0].length &&
               grid[r][c] == 1 && !visited[r][c];
    }
}
