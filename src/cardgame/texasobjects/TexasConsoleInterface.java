package cardgame.texasobjects;

import cardgame.exceptions.TexasExceptions;
import cardgame.interfaces.ConsoleInterface;
import cardgame.utils.CircularIterator;
import cardgame.utils.CircularList;
import cardgame.utils.ConsoleUtil;
import java.util.Scanner;

public class TexasConsoleInterface implements ConsoleInterface {

    public static Scanner scanner = null;
    public static TexasHoldemGame texasGame = null;
    CircularIterator<TexasPlayer> playersIterator;
    public static TexasBoard board = null;

    public TexasConsoleInterface(Scanner scanner, TexasHoldemGame texasGame) {
        this.scanner = scanner;
        this.texasGame = texasGame;
        this.board = (TexasBoard) texasGame.board;
        this.playersIterator = new CircularList<>(texasGame.players).iterator();
    }

    @Override
    public void runGame() throws TexasExceptions {
        if (scanner == null || texasGame == null) {
            throw new TexasExceptions("Scanner or TexasGame is null, exiting");
        }
        // Adding Players
        addPlayers();
        // Setup balance/blind/etc
        setupGame();
        System.out.println("STARTING PREFLOP");
        preflop();
        // flop turn
        System.out.println("STARTING FLOP");
        flop(3);
        System.out.println("STARTING TURNANDRIVER");
        turnAndRiver();
        System.out.println("STARTING SHOWNDOWN");
        showDown();

    }

    private void showDown() {
        TexasHandFilter filter = new TexasHandFilter(texasGame.players);
    }

    private void turnAndRiver() {
        flop(1);
        flop(1);
    }


    private void preflop() {
        // we deal 2 cards per player
        texasGame.dealCard();
        texasGame.dealCard();
        // currentPlayer is the currentPlayer of the loop
        TexasPlayer currentPlayer;
        boolean foundDealer = false;
        // first player to make a move (after big blind)
        TexasPlayer firstPlayer = null;
        // Then the next player to the dealer puts the small blind, and next one the big blind
        while (playersIterator.hasNext()) {
            currentPlayer = playersIterator.next();
            // we only pass this condition once, we find the first player to play, then go in the else if for the other players
            if (currentPlayer.isDealer && !foundDealer) {
                System.out.println(currentPlayer.name+" is the dealer");
                TexasPlayer smallBlindPlayer = playersIterator.next();
                smallBlindPlayer.isSmallBlind = true;
                TexasPlayer bigBlindPlayer = playersIterator.next();
                bigBlindPlayer.isBigBlind = true;
                smallBlindPlayer.bet(texasGame.smallBlind, board);
                bigBlindPlayer.bet(texasGame.bigBlind, board);
                foundDealer = true;
            } else {
                // this condition means that no player re-raises
                if (checkForNoReRaise(currentPlayer)) {
                    break;
                }
                playTurn(currentPlayer);
            }
        }
    }

    // We check if we should break the loop, if so, we update the board pots
    // check is true if the last player action was (c)eck
    private boolean checkForNoReRaise(TexasPlayer currentPlayer) {
        if (currentPlayer.currentBet == board.highestBet && board.currentPot > 0 && !board.lastBetIsCheck) {
            // this is the end of the a step, we move all the bets to the total pot
            board.pot += board.currentPot;
            board.currentPot = 0;
            board.highestBet = 0;
            for (TexasPlayer player : texasGame.players) {
                player.currentBet = 0;
            }
            return true;
        }
        return false;
    }

    private void flop(int nbrCards) {
        TexasPlayer firstPlayer = null;
        // we deal the first 3 community cards
        for (int i = 0; i<nbrCards; i++) {
            board.dealBoardCard();
        }
        boolean foundDealer = false;
        while (playersIterator.hasNext()) {
            TexasPlayer currentPlayer = playersIterator.next();
            // first player to make a move should be player next to dealer
            if (!currentPlayer.isDealer && !foundDealer) {
                continue;
            } else if (currentPlayer.isDealer && !foundDealer) {
                firstPlayer = currentPlayer;
            }
            // this condition means that no player re-raises or everyone checks
            boolean breakLoop = (foundDealer && checkForNoReRaise(currentPlayer)) ||(foundDealer && firstPlayer.equals(currentPlayer));
            if (breakLoop) {
                break;
            }
            foundDealer = true;
            playTurn(currentPlayer);
        }
    }

    private void playTurn(TexasPlayer currentPlayer) {
        if (currentPlayer.folded) {
            return;
        }
        ConsoleUtil.clear();
        board.printBoard();
        // we check what options are available for the player
        if (currentPlayer.currentBet <= board.highestBet) {
            System.out.println("It's your turn "+currentPlayer.name);
            currentPlayer.printHand();
            currentPlayer.printBalanceAndBet(board);
            String commands = "What would you like to do ? (c)all/(r)aise/(f)old";
            if (board.highestBet == 0) {
                commands = "What would you like to do ? (c)heck/(r)aise/(f)old";
            }
            System.out.println(commands);
            String character = scanner.next().toLowerCase();
            switch (character) {
                case "c":
                    currentPlayer.call(board);
                    break;
                case "r":
                    System.out.println("What is the raise value ?");
                    float raiseValue = scanner.nextFloat();
                    currentPlayer.raise(raiseValue,board);
                    break;
                case "f":
                    currentPlayer.fold(board);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void setupGame() {
        float startingBalance, smallBlind, bigBlind;
        System.out.println("What should be the starting balance for the players in € ?");
        startingBalance = Float.parseFloat(scanner.next());
        for (TexasPlayer texasPlayer : texasGame.players) {
            texasPlayer.setBalance(startingBalance);
        }
        System.out.println("What should be the small blind in € ?");
        smallBlind = Float.parseFloat(scanner.next());
        texasGame.smallBlind = smallBlind;
        System.out.println("What should be the big blind in € ?");
        bigBlind = Float.parseFloat(scanner.next());
        texasGame.bigBlind = bigBlind;
    }

    @Override
    public void addPlayers() {
        // first player
        System.out.println("Enter Player Name : ");
        String name = scanner.next();
        TexasPlayer firstPlayer = new TexasPlayer(name);
        // first player added is dealer
        firstPlayer.setDealer(true);
        texasGame.addPlayer(firstPlayer);
        // add second player then others
        do {
            System.out.println("Would you like to add a (r)eal player or (c)omputer player ?");
            String character = scanner.next().toLowerCase();
            if (character.equals("r")) {
                System.out.println("Enter Player Name : ");
                name = scanner.next();
                texasGame.addPlayer(new TexasPlayer(name));
                System.out.println("Would you like to add other players ? (y/n)");
            } else if (character.equals("c")) {

            }
        } while (!scanner.next().toLowerCase().contains("n") && (texasGame.players.size() <= TexasHoldemGame.nbMaxPlayers));
    }
}
