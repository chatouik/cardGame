package cardgame.tests;

import cardgame.texasobjects.TexasHoldemGame;
import cardgame.texasobjects.TexasPlayer;
import cardgame.utils.ScoreUtil;

import java.io.IOException;

import static org.junit.Assert.*;

public class TexasHoldemGameTest {

    // we test the creation of players and a game, then the scores
    @org.junit.Test
    public void testScores() {
        TexasHoldemGame texasHoldemGame = new TexasHoldemGame();
        TexasPlayer player1 = new TexasPlayer("test1");
        player1.setBalance(0);
        TexasPlayer player2 = new TexasPlayer("test2");
        player2.setBalance(1000);
        TexasPlayer player3 = new TexasPlayer("test3");
        player3.setBalance(0);
        TexasPlayer player4 = new TexasPlayer("test4");
        player4.setBalance(0);
        texasHoldemGame.addPlayer(player1);
        texasHoldemGame.addPlayer(player2);
        texasHoldemGame.addPlayer(player3);
        texasHoldemGame.addPlayer(player4);
        try {
            ScoreUtil.createScores(texasHoldemGame,"scores.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void checkDeck() {
        TexasHoldemGame texasHoldemGame = new TexasHoldemGame();
        assertEquals(texasHoldemGame.board.deck.size(),52);
    }

    @org.junit.Test
    public void testBet() {
        TexasHoldemGame texasHoldemGame = new TexasHoldemGame();
        TexasPlayer player1 = new TexasPlayer("test1");
        texasHoldemGame.addPlayer(player1);
        player1.setBalance(1000);
        player1.bet(300,texasHoldemGame.board);
        assertEquals(player1.balance,700.f,0.0f);
    }

    @org.junit.Test
    public void testInvalidBet() {
        TexasHoldemGame texasHoldemGame = new TexasHoldemGame();
        TexasPlayer player1 = new TexasPlayer("test1");
        texasHoldemGame.addPlayer(player1);
        player1.setBalance(200);
        player1.bet(300,texasHoldemGame.board);
        assertEquals(player1.balance,200.f,0.0f);
    }

    @org.junit.Test
    public void testDealCard() {
        TexasHoldemGame texasHoldemGame = new TexasHoldemGame();
        TexasPlayer player1 = new TexasPlayer("test1");
        TexasPlayer player2 = new TexasPlayer("test2");
        texasHoldemGame.addPlayer(player1);
        texasHoldemGame.addPlayer(player2);
        for (int i = 0; i<10 ;i++) {
            texasHoldemGame.dealCard();
        }
        int size1 = player1.hand.size();
        int size2 = player2.hand.size();
        assertTrue(size1 == size2 || size1 == 10);
    }
}