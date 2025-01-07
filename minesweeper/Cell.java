package minesweeper;

public class Cell {
    private boolean mine;
    private boolean revealed;
    private boolean marked;
    private int number;

    public Cell() {
        this.mine = false;
        this.revealed = false;
        this.marked = false;
        this.number = 0;
    }

    public boolean isMine() {
        return this.mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        if (this.marked) {
            return "*";
        }
        if (this.revealed) {
            if (this.mine) {
                return "X";
            } else {
                if (this.number > 0) {
                    return String.valueOf(this.number);
                } else {
                    return "/";
                }
            }
        }
        return ".";
    }
}
