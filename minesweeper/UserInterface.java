package minesweeper;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static int askForNumberOfMines(int availableCells) {
        while (true) {
            try {
                System.out.print("How many mines do you want on the field? ");
                int numberOfMines = Integer.parseInt(scanner.nextLine());
                if (numberOfMines > 0) {
                    if (numberOfMines <= availableCells) {
                        return numberOfMines;
                    } else {
                        System.out.printf("You can only have %d mines\n", availableCells);
                    }
                } else {
                    System.out.println("Please enter a positive number!.");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a number!");
            }
        }
    }

    public static Action askForAction(Board board) {
        while (true) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            String input = scanner.nextLine();
            if (input.matches("^\\d \\d (?:free|mine)$")) {
                String[] actionParts = input.split(" ");
                try {
                    int column = Integer.parseInt(actionParts[0]) - 1;
                    int row = Integer.parseInt(actionParts[1]) - 1;
                    String action = actionParts[2];
                    if (board.isInBounds(row, column)) {
                        return new Action(row, column, action.equals("mine"));
                    } else {
                        System.out.println("Please enter valid inbound coordinates!");
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Please enter valid coordinates!");
                }
            } else {
                System.out.println("Please enter valid action!");
            }
        }
    }

    public static void close() {
        scanner.close();
    }
}
