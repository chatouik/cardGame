package cardgame.texasobjects;

import cardgame.abstracts.CardGame;
import cardgame.enums.CardGameName;

import java.util.ArrayList;
import java.util.List;

public class TexasHoldemGame extends CardGame {

    public float smallBlind;
    public float bigBlind;
    public List<TexasPlayer> players = null;
    // easier to keep track of the dealer using this dealer object + circular list
    public TexasPlayer dealer;

    public static final int nbMaxPlayers = 8;

    public TexasHoldemGame() {

        if (this.players == null) {
            this.players = new ArrayList<>();
        }
        this.board = new TexasBoard();
        this.name = CardGameName.TEXAS_HOLDEM;
    }

    public void addPlayer(TexasPlayer player) {
        this.players.add(player);
        if (player.isDealer) {
            dealer = player;
        }
    }

    @Override
    public void dealCard() {
        for (TexasPlayer player : players) {
            if (!player.folded) {
                player.hand.add(board.deck.pop());
            }
        }
    }


}
