package minesweeper;

public class Action {
    private final int row;
    private final int column;
    private final boolean mine;

    public Action(int row, int column, boolean mine) {
        this.row = row;
        this.column = column;
        this.mine = mine;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isMine() {
        return this.mine;
    }
}
