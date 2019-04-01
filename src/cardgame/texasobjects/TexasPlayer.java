package cardgame.texasobjects;

import cardgame.abstracts.CardGameBoard;
import cardgame.abstracts.CardGamePlayer;
import cardgame.commonobjects.Card;

import java.util.ArrayList;

public class TexasPlayer extends CardGamePlayer {

    public float balance;
    public boolean isDealer;
    public boolean isSmallBlind;
    public boolean isBigBlind;
    public boolean folded = false;
    // keeps track of the currentBet of each player
    public float currentBet = 0;

    public TexasPlayer(String name) {
        if (this.hand == null) {
            this.hand = new ArrayList<Card>();
        }
        this.name = name;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }


    @Override
    public void bet(float betValue, CardGameBoard cardGameBoard) {
        TexasBoard texasBoard = (TexasBoard)cardGameBoard;
        if (betValue > this.balance) {
            System.out.println("Not enough funds !");
            return;
        }
        String smallOrBigBlindBet = "";
        if (this.isBigBlind && this.currentBet == 0) {
            smallOrBigBlindBet = " (bigBlind)";
        } else if (this.isSmallBlind && this.currentBet == 0) {
            smallOrBigBlindBet = " (smallBlind)";
        }
        this.balance -= betValue;
        this.currentBet += betValue;
        if (texasBoard.highestBet < betValue) {
            texasBoard.highestBet = betValue;
        }
        texasBoard.currentPot += betValue;
        if (betValue > 0) {
            texasBoard.lastBetIsCheck = false;
            System.out.println(this.name+" bets " + betValue + smallOrBigBlindBet);
        } else {
            texasBoard.lastBetIsCheck = true;
            System.out.println(this.name+" checks");
        }
    }

    public void call(TexasBoard board) {
        float highestBet = board.highestBet;
        this.bet(highestBet-currentBet,board);
    }

    public void fold(TexasBoard board){
        this.folded = true;
        board.currentPot += currentBet;
        System.out.println(this.name+" folded");
    }

    public void raise(float raiseValue, TexasBoard board) {
        //this is basically calling a call() then adding the raiseValue
        float highestBet = board.highestBet;
        this.bet(raiseValue+highestBet-currentBet, board);
    }

    public void printHand() {
        String result = "";
        result += hand.get(0)+" and "+ hand.get(1);
        System.out.print("Your cards are: ");
        System.out.println(result);
    }

    public void printBalanceAndBet(TexasBoard board) {
        System.out.println("Your current balance is "+this.balance);
        System.out.println("Your current bet is "+this.currentBet);
        System.out.println("Highest bet on the board now is "+board.highestBet);
    }
}
