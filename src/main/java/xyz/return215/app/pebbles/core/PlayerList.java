package xyz.return215.app.pebbles.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class PlayerList {
    HashSet<Player> players = new HashSet<>();
    
    // I wish we can curry functions. 
    /** Predicate function to match a player with a given username.*/
    private Predicate<Player> matchUsername(String username) {
        return ((Player player) -> player.username.equals(username));
    }
    
    public void add(Player player) {
        players.add(player);
    }
    
    public void add(String username) {
        players.add(new Player(username));
    }
    
    public boolean contains(Player player) {
        return players.contains(player);
    }
    
    public boolean contains(String username) {
        return players.stream().anyMatch(matchUsername(username));
    }
    
    public Optional<Player> get(String username) {
        return players.stream().filter(matchUsername(username)).findAny();
    }
    
    public void remove(Player player) {
        players.remove(player);
    }
    
    public void remove(String username) {
        players.removeIf(matchUsername(username));
    }
    
    public Set<Player> getAll() {
        return Collections.unmodifiableSet(players);
    }
}
