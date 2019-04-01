package cardgame.abstracts;

import cardgame.commonobjects.Card;
import cardgame.enums.Rank;
import cardgame.enums.Suit;

import java.util.*;

public abstract class CardGameBoard {
    public Stack<Card> deck;

    // this puts all the cards in random order
    public void initializeDeck() {
        int randNumber;
        deck = new Stack<>();
        List<Card> orderedCards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                orderedCards.add(new Card(rank, suit));
            }
        }
        Random rand = new Random();
        while (orderedCards.size() != 0) {
            randNumber = rand.nextInt(orderedCards.size());
            deck.push(orderedCards.get(randNumber));
            orderedCards.remove(randNumber);
        }
    }

}
