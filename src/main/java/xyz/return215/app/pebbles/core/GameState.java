package xyz.return215.app.pebbles.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Pebbles Game State
 * 
 * @author Muhammad Hidayat
 */
public class GameState implements Serializable {
    /*************************
     * FIELDS AND PROPERTIES
     *************************/
    
    /**
     * Serialization version unique ID
     */
    private static final long serialVersionUID = 2428215208939688078L;
    
    private int pits[];
    protected int pitCount;
    protected int playerPitCount;
    protected int totalPitCount;
    protected int onHand = 0;
    protected int cursor = 0;
    protected boolean currentlyPlayerTwo;
    protected GameStatePhase phase;
    
    /******************
     * STATE CREATION
     ******************/
                        
                        /**
                         * @return the pitCount
                         */
    public final int getPitCount() { return pitCount; }
    
    /**
     * @return the playerPitCount
     */
    public final int getPlayerPitCount() { return playerPitCount; }
    
    /**
     * @return the totalPitCount
     */
    public final int getTotalPitCount() { return totalPitCount; }
    
    /**
     * @return the onHand
     */
    public final int getOnHand() { return onHand; }
    
    /**
     * @return the cursor
     */
    public final int getCursor() { return cursor; }
    
    /**
     * @return the currentlyPlayerTwo
     */
    public final boolean isCurrentlyPlayerTwo() {
        return currentlyPlayerTwo;
    }
    
    /**
     * @return the phase
     */
    public final GameStatePhase getPhase() { return phase; }
    
    /**
     * Create a new empty game state with specified initial value and starting
     * order.
     * 
     * @param pitCount  Number of pits (not including home pot) per player
     * @param initValue Initial value of each pit
     */
    private GameState(int pitCount, int initValue, boolean startPlayerTwo) {
        this.pitCount = pitCount;
        this.playerPitCount = pitCount + 1;
        this.totalPitCount = 2 * (this.playerPitCount);
        this.pits = new int[this.totalPitCount];
        this.phase = GameStatePhase.WAITING;
        this.currentlyPlayerTwo = startPlayerTwo;
        for (int i = 0; i < totalPitCount; i++) {
            pits[i] = ((i + 1) % playerPitCount) == 0 ? 0 : initValue;
        }
    }
    
    /**
     * Create a new game state with in much more detail
     * 
     * @param pits
     * @param pitCount
     * @param playerPitCount
     * @param totalPitCount
     * @param onHand
     * @param cursor
     * @param currentlyPlayerTwo
     */
    protected GameState(int[] pits, int pitCount, int playerPitCount,
            int totalPitCount, int onHand, int cursor,
            boolean currentlyPlayerTwo, GameStatePhase phase) {
        this.pits = pits;
        this.pitCount = pitCount;
        this.playerPitCount = playerPitCount;
        this.totalPitCount = totalPitCount;
        this.onHand = onHand;
        this.cursor = cursor;
        this.currentlyPlayerTwo = currentlyPlayerTwo;
        this.phase = phase;
    }
    
    /**
     * Initialize a fresh new game state
     * 
     * @param pitCount       Number of pits (not including home pot) per player
     * @param initValue      Initial value of each pit
     * @param startPlayerTwo Player two plays first
     * @return A new game state
     */
    public static GameState initState(int pitCount, int initValue,
            boolean startPlayerTwo) {
        return new GameState(pitCount, initValue, startPlayerTwo);
    }
    
    public static GameState flipState(GameState state) {
        // flip pits, cursor, currentlyPlayerTwo
        List<Integer> newPitL = new ArrayList<Integer>();
        for (int i = 0; i < state.playerPitCount; i++) {
            Collections.swap(newPitL, i, i + state.playerPitCount);
        }
        int[] newPit = newPitL.stream().mapToInt(Integer::intValue).toArray();
        int newCursor = (state.cursor < state.playerPitCount)
                ? state.cursor + state.playerPitCount
                : state.cursor - state.playerPitCount;
        return new GameState(newPit, state.pitCount, state.playerPitCount,
                state.totalPitCount, state.onHand, newCursor,
                !state.currentlyPlayerTwo, state.phase);
    }
    
    /****************************
     * UTILITY INSTANCE METHODS
     ***************************
     */
       
       /**
        * Check whether the selected player is currently playing
        * 
        * @param isPlayerTwo The player to check
        * @return
        */
    private boolean isValidPlayer(boolean isPlayerTwo) {
        return isPlayerTwo == currentlyPlayerTwo;
    }
    
    /**
     * Check if the specified pit index is part of the home pot
     * 
     * @param isPlayerTwo
     * @param pit
     * @return
     */
    private boolean isHomePot(int pit) {
        return pit == getPlayerHomePotPos(currentlyPlayerTwo)
                || pit == getPlayerHomePotPos(!currentlyPlayerTwo);
    }
    
    private boolean isOwnPit(boolean isPlayerTwo, int globalPit) {
        int startPos = getPlayerPitPos(isPlayerTwo, 0);
        int endPos = startPos + playerPitCount;
        return startPos <= globalPit && globalPit < endPos;
    }
    
    private int incrementCursor() {
        return cursor = (cursor + 1) % totalPitCount;
    }
    
    public int getCrossPit(boolean isPlayerTwo, int pit) {
        return getPlayerPitPos(!isPlayerTwo,
                isPlayerTwo ? 2 * pitCount - pit : pitCount - pit - 1);
    }
    
    /**
     * Get the pits (not home pot) that belongs to a player.
     * 
     * @param isPlayerTwo Selector for player two (true)
     * @return
     */
    public int[] getPlayerPits(boolean isPlayerTwo) {
        int startPos = getPlayerPitPos(isPlayerTwo, 0);
        int endPos = startPos + pitCount;
        return Arrays.copyOfRange(pits, startPos, endPos);
    }
    
    public int getPlayerHomePot(boolean isPlayerTwo) {
        return pits[getPlayerHomePotPos(isPlayerTwo)];
    }
    
    public int getPlayerHomePotPos(boolean isPlayerTwo) {
        return (isPlayerTwo ? 2 * playerPitCount : playerPitCount) - 1;
    }
    
    /**
     * Get pit position from player's relative position
     * 
     * @param isPlayerTwo
     * @param pit         Pit position relative to the player
     * @return Global pit position
     */
    public int getPlayerPitPos(boolean isPlayerTwo, int pit) {
        return isPlayerTwo ? playerPitCount + pit : pit;
    }
    
    public String getStateText() {
        return ("Current player: " + (currentlyPlayerTwo ? "2" : "1") + "\n"
                + "On hand: " + onHand + "\n" + "Phase: " + phase + "\n");
        
    }
    
    public String getPlayerBoardText(boolean isPlayerTwo) {
        int[] pits = getPlayerPits(isPlayerTwo);
        int pot = getPlayerHomePot(isPlayerTwo);
        StringBuilder s = new StringBuilder("Player ");
        s.append(isPlayerTwo ? "2" : "1").append(" [");
        for (int i = 0; i < pits.length; i++) {
            if (i != 0)
                s.append(", ");
            s.append(pits[i]);
        }
        s.append("] (").append(pot).append(")");
        return s.toString();
    }
    
    public String getPlayerBoardTextReverse(boolean isPlayerTwo) {
        int[] pits = getPlayerPits(isPlayerTwo);
        int pot = getPlayerHomePot(isPlayerTwo);
        StringBuilder s = new StringBuilder("Player ");
        s.append(isPlayerTwo ? "2" : "1").append(" (");
        s.append(pot).append(") [");
        for (int i = pits.length - 1; i >= 0; i--) {
            if (i != pits.length - 1)
                s.append(", ");
            s.append(pits[i]);
        }
        s.append("]");
        return s.toString();
    }
    
    public String getAllPlayersBoardText() {
        return getPlayerBoardText(currentlyPlayerTwo) + "\n"
                + getPlayerBoardTextReverse(!currentlyPlayerTwo) + "\n";
    }
    
    private boolean isGameOver() {
        return Arrays.stream(getPlayerPits(currentlyPlayerTwo))
                .allMatch(i -> i == 0)
                || Arrays.stream(getPlayerPits(!currentlyPlayerTwo))
                        .allMatch(i -> i == 0);
    }
    
    /******************
     * PHASE ADVANCING
     ******************/
    
    /**
     * 
     * @param isPlayerTwo Is player two playing
     * @param globalPit   Player's pit index
     * @return The current instance of the game state
     */
    private GameState selectPit(boolean isPlayerTwo, int globalPit)
            throws IllegalStateException, IllegalArgumentException {
        
        cursor = globalPit;
        // // sanity checks
        // if (this.phase != GameStatePhase.WAITING) {
        // throw new IllegalStateException("Unable to select at this phase");
        // }
        if (!isValidPlayer(isPlayerTwo)) {
            throw new IllegalStateException("It is not your turn yet");
        }
        if (isHomePot(cursor)) {
            throw new IllegalArgumentException("Pit number " + globalPit
                    + " is the home pot, cannot be used.");
        }
        
        phase = GameStatePhase.SELECTING;
        
        return this;
    }
    
    public GameState playerSelectPit(boolean isPlayerTwo, int localPit) {
        if (localPit >= pitCount) {
            throw new IllegalArgumentException(
                    "Pit " + localPit + " is not in valid range of 0.."
                            + (pitCount - 1) + ". Try again.");
        }
        return selectPit(isPlayerTwo, getPlayerPitPos(isPlayerTwo, localPit));
    }
    
    public GameState tickPhase() {
        switch (phase) {
        case SELECTING:
            if (pits[cursor] == 0) {
                phase = GameStatePhase.WAITING;
            } else {
                onHand = pits[cursor];
                pits[cursor] = 0;
                // point to the next pit
                incrementCursor();
                phase = GameStatePhase.MOVING;
            }
        break;
        case MOVING:
            if (onHand == 0) {
                if (cursor == getPlayerHomePotPos(currentlyPlayerTwo)) {
                    // another turn
                    if (isGameOver()) {
                        phase = GameStatePhase.ENDING;
                    } else {
                        phase = GameStatePhase.WAITING;
                    }
                } else if (!isHomePot(cursor) && pits[cursor] == 1) {
                    if (isOwnPit(currentlyPlayerTwo, cursor)) {
                        phase = GameStatePhase.CAPTURING;
                    } else {
                        if (isGameOver()) {
                            phase = GameStatePhase.ENDING;
                        } else {
                            // next turn
                            phase = GameStatePhase.WAITING;
                            currentlyPlayerTwo = !currentlyPlayerTwo;
                        }
                    }
                } else {
                    selectPit(currentlyPlayerTwo, cursor);
                }
                break;
            }
            if (cursor == getPlayerHomePotPos(!currentlyPlayerTwo)) {
                incrementCursor();
            }
            pits[cursor]++;
            onHand--;
            // further empty checks are done in next tick
            if (onHand != 0)
                incrementCursor();
        break;
        case CAPTURING:
            int crossPit = getCrossPit(currentlyPlayerTwo, cursor);
            int homePos = getPlayerHomePotPos(currentlyPlayerTwo);
            pits[homePos] += pits[cursor] + pits[crossPit];
            pits[cursor] = pits[crossPit] = 0;
            if (isGameOver()) {
                phase = GameStatePhase.ENDING;
            } else {
                // next turn
                phase = GameStatePhase.WAITING;
                currentlyPlayerTwo = !currentlyPlayerTwo;
            }
        break;
        case ENDING:
            cursor = 0;
            for (; cursor < totalPitCount; cursor++) {
                if (isHomePot(cursor))
                    continue;
                if (cursor < playerPitCount) {
                    pits[playerPitCount - 1] += pits[cursor];
                } else {
                    pits[2 * playerPitCount - 1] += pits[cursor];
                }
                pits[cursor] = 0;
            }
            phase = GameStatePhase.OVER;
        break;
        default:
        }
        
        return this;
    }
}
