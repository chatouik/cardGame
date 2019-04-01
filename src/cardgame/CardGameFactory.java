package cardgame;

import cardgame.abstracts.CardGame;
import cardgame.enums.CardGameName;
import cardgame.texasobjects.TexasHoldemGame;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class CardGameFactory {

    public static CardGame getGame(Scanner scanner) {
        //clear the console
        System.out.print("\033[1;1H\033[2J");
        System.out.println("Choose a game !");
        int gameIndex = 1;
        for (CardGameName gameName : Arrays.asList(CardGameName.values())) {
            System.out.println(gameIndex+"- "+gameName);
            gameIndex++;
        }
        switch (scanner.next()){
            case "1":
                return new TexasHoldemGame();
            case "2":
                System.out.println("Not implemented yet !");
            default:
                throw new IllegalArgumentException("Invalid input !");

        }
    }
}
