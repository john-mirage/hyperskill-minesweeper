package minesweeper;

public class Game {
    private final Board board;

    public Game(int boardSize, int numberOfMines) {
        this.board = new Board(boardSize, numberOfMines);
    }

    public void play() {
        while (true) {
            this.board.display();
            Action action = UserInterface.askForAction(this.board);
            if (action.isMine()) {
                this.board.mark(action.getRow(), action.getColumn());
            } else {
                if (this.board.explore(action.getRow(), action.getColumn())) {
                    System.out.println("You stepped on a mine and failed!");
                    break;
                }
            }
            if (this.board.checkWin() || this.board.checkAlternateWin()) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }
        this.board.display();
    }
}
