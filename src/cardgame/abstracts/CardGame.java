package cardgame.abstracts;


import cardgame.enums.CardGameName;

public abstract class CardGame {
    public CardGameName name;
    public CardGameBoard board;

    // deals one card per player
    public abstract void dealCard();

}
