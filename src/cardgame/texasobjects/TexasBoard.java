package cardgame.texasobjects;

import cardgame.abstracts.CardGameBoard;
import cardgame.commonobjects.Card;

import java.util.ArrayList;
import java.util.List;

public class TexasBoard extends CardGameBoard {

    // total pot on the table
    public float pot;
    // pot of current step flop/river/etc
    public float currentPot;
    // this keeps track of the highest bet for each turn, -1 if no bet has been made yet.
    public float highestBet = -1;
    // Community cards
    public List<Card> communityCards;
    // true if last bet was a check
    public boolean lastBetIsCheck = false;

    public TexasBoard() {
        if (this.deck == null) {
            this.initializeDeck();
        }
        communityCards = new ArrayList<>();

    }

    public void dealBoardCard() {
        communityCards.add(deck.pop());
    }

    public void printBoard() {
        if (communityCards.size() > 0) {
            System.out.println("Community cards are: ");
            String result = "";
            for (Card card : communityCards) {
                result += card.toString()+" - ";
            }
            System.out.println(result);
        }
        System.out.println("Current Pot is : "+this.currentPot );
        System.out.println("Total Pot is : "+this.pot );
    }
}
