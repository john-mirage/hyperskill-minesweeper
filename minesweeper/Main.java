package minesweeper;

public class Main {
    public static void main(String[] args) {
        int boardSize = 9;
        int numberOfMines = UserInterface.askForNumberOfMines(boardSize * boardSize);
        Game game = new Game(boardSize, numberOfMines);
        game.play();
        UserInterface.close();
    }
}
