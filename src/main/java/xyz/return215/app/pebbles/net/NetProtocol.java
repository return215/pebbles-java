package xyz.return215.app.pebbles.net;

import static xyz.return215.app.pebbles.util.ServerUtil.parameterize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import xyz.return215.app.pebbles.core.Player;

public class NetProtocol {
    public enum NetCommands {
        USERNAME_SET("/us"), USERNAME_INFO("/ui"), LOBBY_CREATE("/lc"),
        LOBBY_JOIN("/lj"), LOBBY_INFO("/li"), ROOM_JOIN("/rj"),
        ROOM_CHAT("/rc"), ROOM_READY("/rr"), ROOM_LEAVE("/rl"),
        GAME_START("/gs"), GAME_TURN("/gt"), GAME_SELECT("/gp"),
        GAME_FINISH("/gf"), GAME_WINNER("/gw"), UNKNOWN("/xx");
        
        public final String value;
        
        private NetCommands(String v) { value = v; }
        
        private static final Map<String, NetCommands> mapByCommand = new HashMap<>();
        
        static {
            for (NetCommands n: values()) {
                mapByCommand.put(n.value, n);
            }
        }
        
        public static Optional<NetCommands> fromCommand(String command) {
            return Optional.ofNullable(mapByCommand.get(command));
        }
    }
    
    public static String setBoolStat(boolean status) {
        return status ? "+" : "-";
    }
    
    public static boolean getBoolStat(String stat) {
        return stat.equals("+") ? true : false;
    }
    
    public static String usernameSet(String username) {
        return parameterize(NetCommands.USERNAME_SET.value, username);
    }
    
    public static String usernameSetResult(String username, boolean success) {
        return parameterize(NetCommands.USERNAME_SET.value,
                setBoolStat(success), username);
    }
    
    public static String usernameInfoFail(String username) {
        return parameterize(NetCommands.USERNAME_INFO.value, setBoolStat(false),
                username);
    }
    
    public static String usernameInfoSuccess(Player player) {
        return parameterize(NetCommands.USERNAME_INFO.value, setBoolStat(true),
                player.getUsername(), String.valueOf(player.getPlays()),
                String.valueOf(player.getWinCount()),
                String.valueOf(player.getWinStreak()));
    }
    
    public static String lobbyCreate() {
        return parameterize(NetCommands.LOBBY_CREATE.value);
    }
    
    public static String lobbyJoin(String roomID) {
        return parameterize(NetCommands.LOBBY_JOIN.value, roomID);
    }
    
    public static String lobbyInfo(boolean status, String roomID,
            String hostUsername) {
        return parameterize(NetCommands.LOBBY_INFO.value, setBoolStat(status),
                roomID, hostUsername);
    }
    
    public static String roomChat(String message) {
        return parameterize(NetCommands.ROOM_CHAT.value, message);
    }
    
    public static String roomChat(String message, String username) {
        return parameterize(NetCommands.ROOM_CHAT.value, username, message);
    }
    
    public static String roomJoin(String username) {
        return parameterize(NetCommands.ROOM_JOIN.value, username);
    }
    
    public static String roomLeave() {
        return parameterize(NetCommands.ROOM_LEAVE.value);
    }
    
    public static String roomLeave(String username) {
        return parameterize(NetCommands.ROOM_LEAVE.value, username);
    }
    
    public static String roomReady(boolean ready) {
        return parameterize(NetCommands.ROOM_READY.value, setBoolStat(ready));
    }
    
    public static String roomReady(boolean ready, String username) {
        return parameterize(NetCommands.ROOM_READY.value, setBoolStat(ready),
                username);
    }
    
    public static String gameStart(int pitCount, int initValue) {
        return parameterize(NetCommands.GAME_START.value,
                String.valueOf(pitCount), String.valueOf(initValue));
    }
    
    public static String gameTurn(String username) {
        return parameterize(NetCommands.GAME_TURN.value, username);
    }
    
    public static String gameSelect(int pit) {
        return parameterize(NetCommands.GAME_SELECT.value, String.valueOf(pit));
    }
    
    public static String gameSelect(String username, int pit) {
        return parameterize(NetCommands.GAME_SELECT.value, username,
                String.valueOf(pit));
    }
    
    public static String gameSelect(String username, int pit, boolean ok) {
        return parameterize(NetCommands.GAME_SELECT.value, setBoolStat(ok),
                username, String.valueOf(pit));
    }
    
    public static String gameEnd() {
        return parameterize(NetCommands.GAME_FINISH.value);
    }
    
    public static String gameResults(String winnerUsername, int scoreWin,
            int scoreLose) {
        return parameterize(NetCommands.GAME_WINNER.value,
                String.valueOf(scoreWin), String.valueOf(scoreLose));
    }
    
    public static String unknownCommand() {
        return parameterize(NetCommands.UNKNOWN.value);
    }
    
    public static String unknownCommand(String message) {
        return parameterize(NetCommands.UNKNOWN.value, message);
    }
    
    public static Optional<NetCommands> getCommand(String string) {
        return NetCommands.fromCommand(string.trim().split(" ", 2)[0]);
    }
    
    public static String[] getArgs(String string) {
        String clean = string.trim();
        String[] cleanSplit = clean.split(" ");
        return Arrays.copyOfRange(cleanSplit, 1, cleanSplit.length);
    }
    
    public static String getRest(String string, int idx) {
        String clean = string.trim();
        String[] cleanSplit = clean.split(" ", idx + 1);
        return cleanSplit[cleanSplit.length - 1];
    }
}
