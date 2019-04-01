package cardgame;

import cardgame.abstracts.CardGame;
import cardgame.interfaces.ConsoleInterface;
import cardgame.texasobjects.TexasConsoleInterface;
import cardgame.texasobjects.TexasHoldemGame;

import java.util.Scanner;

public class Main {
    // static scanner we'll use for user input
    static Scanner scanner = new Scanner(System.in);
    static ConsoleInterface consoleInterface;

    public static void main(String[] args) {
        // we create the game
        CardGame chosenGame = CardGameFactory.getGame(scanner);
        if (chosenGame instanceof TexasHoldemGame) {
            consoleInterface = new TexasConsoleInterface(scanner, (TexasHoldemGame)chosenGame);
        }
        try {
            consoleInterface.runGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
