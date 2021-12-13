package xyz.return215.app.pebbles.core;

import java.io.Serializable;

public class Player implements Serializable {
    /**
     * Serialization version unique ID
     */
    private static final long serialVersionUID = -3468647621762468543L;

    protected String username;
    private int winCount = 0;
    private int plays = 0;
    private int winStreak = 0;
    
    /**
     * Create a new player
     * 
     * @param username
     */
    public Player(String username) { this.username = username; }
    
    /**
     * @return the username
     */
    public final String getUsername() { return username; }
    
    /**
     * @return the winCount
     */
    public final int getWinCount() { return winCount; }
    
    /**
     * @return the plays
     */
    public final int getPlays() { return plays; }
    
    /**
     * @return the winStreak
     */
    public final int getWinStreak() { return winStreak; }
    
    /**
     * Declare the player wins a match.
     * 
     * Count the match and add to win count and streak.
     */
    public void win() {
        plays++;
        winCount++;
        winStreak++;
    }
    
    /**
     * Declare the player loses a match.
     * 
     * The player resets their win streak and counts their play.
     */
    public void lose() {
        plays++;
        winStreak = 0;
    }
    
    /**
     * Declare the player ties a match (nobody wins or loses).
     * 
     * The play counts, but does not change win count or streak.
     */
    public void tie() {
        plays++;
    }
}
