package com.Girus.problemsolving;

//🔸 Problem 3: Knights and Portals
//Given a grid, find the shortest path from the top-left to bottom-right. You may teleport between any two empty cells exactly once

import java.util.*;

public class KnightsAndPortals {

    static class Cell {
        int row, col, dist;
        boolean teleported;

        public Cell(int row, int col, int dist, boolean teleported) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.teleported = teleported;
        }
    }

    public static void main(String[] args) {
        // Sample test case
        int[][] grid = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        int result = shortestPathWithTeleport(grid);
        System.out.println("✅ Shortest path with one teleport: " + result);
    }

    
    public static int shortestPathWithTeleport(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        List<int[]> emptyCells = new ArrayList<>();

        // Find all empty cells (used later for teleportation)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 0)
                    emptyCells.add(new int[]{i, j});

        
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};


        Queue<Cell> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2]; 
        queue.offer(new Cell(0, 0, 0, false));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (current.row == n - 1 && current.col == m - 1) {
                return current.dist;
            }

            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                if (isValid(newRow, newCol, n, m, grid) && !visited[newRow][newCol][current.teleported ? 1 : 0]) {
                    visited[newRow][newCol][current.teleported ? 1 : 0] = true;
                    queue.offer(new Cell(newRow, newCol, current.dist + 1, current.teleported));
                }
            }

           
            if (!current.teleported) {
                for (int[] cell : emptyCells) {
                    int r = cell[0], c = cell[1];
                    if ((r != current.row || c != current.col) && !visited[r][c][1]) {
                        visited[r][c][1] = true;
                        queue.offer(new Cell(r, c, current.dist + 1, true));
                    }
                }
            }
        }

        return -1; 
    }


    private static boolean isValid(int r, int c, int n, int m, int[][] grid) {
        return r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 0;
    }
}
