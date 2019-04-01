package cardgame.interfaces;

import java.util.Scanner;

public interface ConsoleInterface {
    // main call to run the different game steps
    void runGame() throws Exception;

    // add human/bot players
    void addPlayers() throws Exception;

    // setup the different game args (blind and balance for example for texasHoldem)
    void setupGame();
}
