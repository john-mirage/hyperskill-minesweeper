package minesweeper;

import java.util.Random;

public class Board {
    private final int boardSize;
    private final int numberOfMines;
    private final Cell[][] grid;

    public Board(int boardSize, int numberOfMines) {
        this.boardSize = boardSize;
        this.numberOfMines = numberOfMines;
        this.grid = new Cell[boardSize][boardSize];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                this.grid[row][col] = new Cell();
            }
        }
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < this.numberOfMines) {
            int row = random.nextInt(this.boardSize);
            int col = random.nextInt(this.boardSize);
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                minesPlaced++;
            }
        }
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                if (!grid[row][col].isMine()) {
                    grid[row][col].setNumber(countAdjacentMines(row, col));
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int newRow = row + dr;
                int newCol = col + dc;
                if (isInBounds(newRow, newCol) && grid[newRow][newCol].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.boardSize && col >= 0 && col < this.boardSize;
    }

    public boolean explore(int row, int col) {
        if (!this.isInBounds(row, col) || this.grid[row][col].isRevealed()) {
            return false;
        }
        this.grid[row][col].setRevealed(true);
        if (this.grid[row][col].isMarked()) {
            this.grid[row][col].setMarked(false);
        }
        if (this.grid[row][col].isMine()) {
            return true; // Hit a mine
        }
        // If the cell is blank (0), reveal its neighbors
        if (this.grid[row][col].getNumber() == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    explore(row + dr, col + dc);
                }
            }
        }
        return false;
    }

    public void mark(int row, int col) {
        if (!this.grid[row][col].isRevealed()) {
            this.grid[row][col].setMarked(!this.grid[row][col].isMarked());
        }
    }

    public boolean checkWin() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                if (!this.grid[row][col].isMine() && !this.grid[row][col].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkAlternateWin() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                if (!this.grid[row][col].isMine() && this.grid[row][col].isMarked()) {
                    return false;
                } else if (this.grid[row][col].isMine() && !this.grid[row][col].isMarked()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display() {
        System.out.print("""
                 |123456789|
                -|---------|
                """);
        for (int row = 0; row < this.boardSize; row++) {
            System.out.printf("%d|", row + 1);
            for (int col = 0; col < this.boardSize; col++) {
                System.out.print(this.grid[row][col]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }
}
