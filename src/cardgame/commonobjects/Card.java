package cardgame.commonobjects;

import cardgame.enums.CardColor;
import cardgame.enums.Rank;
import cardgame.enums.Suit;

public class Card {
    private Rank rank;
    private Suit suit;
    private CardColor color;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card(Rank rank, Suit suit, CardColor color) {
        this.rank = rank;
        this.suit = suit;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", rank.toString(), suit.toString());
    }


}
