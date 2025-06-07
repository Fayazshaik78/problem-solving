package com.Girus.problemsolving;
 
//🔸 Problem 1: Sudoku Validator With Custom Zones
//Validate a 9x9 Sudoku board. In addition to standard validation, check custom zones (each with 9 unique cells) for digits 1–9 without repetition.


import java.util.*;
public class SudokuValidator {
    public static void main(String[] args) {
    	
        char[][] board = {
            {'5','3','4','6','7','8','9','1','2'},
            {'6','7','2','1','9','5','3','4','8'},
            {'1','9','8','3','4','2','5','6','7'},
            {'8','5','9','7','6','1','4','2','3'},
            {'4','2','6','8','5','3','7','9','1'},
            {'7','1','3','9','2','4','8','5','6'},
            {'9','6','1','5','3','7','2','8','4'},
            {'2','8','7','4','1','9','6','3','5'},
            {'3','4','5','2','8','6','1','7','9'}
        };

        List<List<int[]>> customZones = getStandard3x3Zones();

        boolean isValid = validateSudoku(board, customZones);

        if (isValid) {
            System.out.println("✅ Sudoku is valid!");
        } else {
            System.out.println("❌ Sudoku is invalid!");
        }
    }

    public static boolean validateSudoku(char[][] board, List<List<int[]>> zones) {
        return areAllRowsValid(board) &&
               areAllColumnsValid(board) &&
               areAllZonesValid(board, zones);
    }


    public static boolean areAllRowsValid(char[][] board) {
        for (int row = 0; row < 9; row++) {
            Set<Character> seen = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                char current = board[row][col];
                if (current < '1' || current > '9' || seen.contains(current)) {
                    return false; // invalid digit or duplicate
                }
                seen.add(current);
            }
        }
        return true;
    }


    public static boolean areAllColumnsValid(char[][] board) {
        for (int col = 0; col < 9; col++) {
            Set<Character> seen = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                char current = board[row][col];
                if (current < '1' || current > '9' || seen.contains(current)) {
                    return false;
                }
                seen.add(current);
            }
        }
        return true;
    }

    // This method checks if each custom zone (like a box) is valid
    public static boolean areAllZonesValid(char[][] board, List<List<int[]>> zones) {
        for (List<int[]> zone : zones) {
            Set<Character> seen = new HashSet<>();
            for (int[] cell : zone) {
                int row = cell[0];
                int col = cell[1];
                char current = board[row][col];
                if (current < '1' || current > '9' || seen.contains(current)) {
                    return false;
                }
                seen.add(current);
            }
        }
        return true;
    }

   
    public static List<List<int[]>> getStandard3x3Zones() {
        List<List<int[]>> zones = new ArrayList<>();


        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                List<int[]> zone = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int row = blockRow * 3 + i;
                        int col = blockCol * 3 + j;
                        zone.add(new int[]{row, col});
                    }
                }
                zones.add(zone); 
            }
        }

        return zones;
    }
}
// sample input for code .
//5 3 4 | 6 7 8 | 9 1 2
//6 7 2 | 1 9 5 | 3 4 8
//1 9 8 | 3 4 2 | 5 6 7
//---------------------
//8 5 9 | 7 6 1 | 4 2 3
//4 2 6 | 8 5 3 | 7 9 1
//7 1 3 | 9 2 4 | 8 5 6
//---------------------
//9 6 1 | 5 3 7 | 2 8 4
//2 8 7 | 4 1 9 | 6 3 5
//3 4 5 | 2 8 6 | 1 7 9

