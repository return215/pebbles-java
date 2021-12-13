package xyz.return215.app.pebbles.core;

/**
 * Phases of the game state
 * 
 * @author Muhammad Hidayat
 */
public enum GameStatePhase {
    /** The game is currently waiting for the player to select */
    WAITING,
    /** The player selects a pit and is about to move */
    SELECTING,
    /** The player moves to next pit and drops a pebble into a qualified pit */
    MOVING,
    /** The player captures the pebbles from the opponent into his pot */
    CAPTURING,
    /** The game is ending and the score is being tallied */
    ENDING,
    /** game over */
    OVER
}