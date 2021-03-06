package lv.roma.battleship.model;

public class Game {
    private Player player1;
    private Player player2;

    public void join(Player player) {
        if (player1 == null) {
            player1 = player;
        } else {
            player2 = player;
        }
    }

    public boolean isReady(){
        return player1 != null && player2 != null;
    }
    public boolean gameReady() { return player1.getOwnField().isValid() && player2.getOwnField().isValid();}
}
