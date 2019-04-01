package cardgame.abstracts;

import cardgame.commonobjects.Card;

import java.util.List;

public abstract class CardGamePlayer {
    public String name;
    // public CardGameHand hand;
    public List<Card> hand;

    public abstract void bet(float betValue, CardGameBoard cardGameBoard);
}
