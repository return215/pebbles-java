package xyz.return215.app.pebbles.core;

import java.util.Random;
import java.util.Scanner;

public class Game {
    /**
     * The player of the game
     */
    private Player player1, player2;
    
    /**
     * @return the player1
     */
    public final Player getPlayer1() { return player1; }
    
    /**
     * @return the player2
     */
    public final Player getPlayer2() { return player2; }
    
    /**
     * @return the state
     */
    public final GameState getState() { return state; }
    
    private GameState state;
    
    /**
     * @param player1
     * @param player2
     * @param state
     */
    public Game(Player player1, Player player2, GameState state) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
    }
    
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        String p1name = stdin.nextLine();
        String p2name = stdin.nextLine();
        
        Random r = new Random();
        boolean playerTwoPlays = r.nextBoolean();
        // playerTwoPlays = true;
        
        Game game = new Game(new Player(p1name), new Player(p2name),
                GameState.initState(4, 5, playerTwoPlays));
        
        Player p1 = game.getPlayer1();
        Player p2 = game.getPlayer2();
        GameState state = game.getState();
        
        while (state.getPhase() != GameStatePhase.OVER) {
            playerTwoPlays = state.isCurrentlyPlayerTwo();
            System.out.println("Current turn: "
                    + (playerTwoPlays ? p2.getUsername() : p1.getUsername()));
            System.out.println(state.getStateText());
            System.out.println(state.getAllPlayersBoardText());
            if (state.getPhase() == GameStatePhase.WAITING) {
                int index = stdin.nextInt();
                try {
                    state.playerSelectPit(playerTwoPlays, index);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                state.tickPhase();
            }
        }
        System.out.println(state.getStateText());
        System.out.println(state.getAllPlayersBoardText());
        
        int p1score = state.getPlayerHomePot(false);
        int p2score = state.getPlayerHomePot(true);
        
        int diff = (p1score - p2score);
        
        if (diff < 0) {
            System.out.println(p2.getUsername() + " wins by " + (-diff));
        } else if (diff > 0) {
            System.out.println(p1.getUsername() + " wins by " + diff);
        } else {
            System.out.println("Round draw.");
        }
        
        stdin.close();
    }
}
