package xyz.return215.app.pebbles.core;

import java.util.Random;
import java.util.Scanner;

public class Game {
    /**
     * The player of the game
     */
    private Player player1, player2;
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
                GameState.initState(7, 7, playerTwoPlays));
        
        while (game.state.getPhase() != GameStatePhase.OVER) {
            playerTwoPlays = game.state.isCurrentlyPlayerTwo();
            System.out.println(game.state.getStateText());
            System.out.println(game.state.getAllPlayersBoardText());
            if (game.state.getPhase() == GameStatePhase.WAITING) {
                int index = stdin.nextInt();
                try {
                game.state.playerSelectPit(playerTwoPlays, index);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                game.state.tickPhase();
            }
        }
        System.out.println(game.state.getStateText());
        System.out.println(game.state.getAllPlayersBoardText());
        
        stdin.close();
    }
}
