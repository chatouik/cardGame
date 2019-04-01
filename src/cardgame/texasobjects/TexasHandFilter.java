package cardgame.texasobjects;

import cardgame.commonobjects.Card;

import java.util.HashMap;
import java.util.List;

public class TexasHandFilter {
    //public List<> playerHands;
    public HashMap<String,List<Card>> playerHands;

    public TexasHandFilter(List<TexasPlayer> players) {
        for (TexasPlayer player : players) {
            playerHands.put(player.name,player.hand);
        }
    }

    public TexasPlayer isRoyalFlushFilter() {
        return null;
    }

    public TexasPlayer isFourOfAKindFilter() {
        return null;
    }

    public TexasPlayer isFullHouseFilter() {
        return null;
    }

    public TexasPlayer isFlushFilter() {
        return null;
    }

    public TexasPlayer isStraightFilter() {
        return null;
    }

    public TexasPlayer isThreeOfAKindFilter() {
        return null;
    }

    public TexasPlayer isTwoPairFilter() {
        return null;
    }

    public TexasPlayer isOnePairFilter() {
        return null;
    }
}
