import java.util.Scanner;

public class SudokuGame {
    private static final int SIZE = 9; // Size of the Sudoku grid

    // Function to print the Sudoku grid
    public void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Function to check if placing a number is valid
    public boolean isValid(int[][] board, int row, int col, int num) {
        // Check if the number is already in the current row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check if the number is already in the current column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check if the number is in the current 3x3 box
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to solve the Sudoku using backtracking
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) { // Empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Place number

                            if (solveSudoku(board)) {
                                return true; // Recursively solve
                            }

                            board[row][col] = 0; // Reset on backtrack
                        }
                    }
                    return false; // Trigger backtracking
                }
            }
        }
        return true; // Puzzle solved
    }

    public static void main(String[] args) {
        SudokuGame sudokuGame = new SudokuGame();
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[][] {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("\nInitial Sudoku Board:");
        sudokuGame.printBoard(board);

        if (sudokuGame.solveSudoku(board)) {
            System.out.println("\nSolved Sudoku Board:");
            sudokuGame.printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }

        scanner.close();
    }
}
