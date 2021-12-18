package xyz.return215.app.pebbles.client;

import xyz.return215.app.pebbles.client.ui.GraphicalUserInterface;
import xyz.return215.app.pebbles.client.ui.TextUserInterface;
import xyz.return215.app.pebbles.client.ui.UserInterface;

public class ClientHandler extends Thread {
    String hostname;
    int port;
    UserInterface ui;
    ClientNet net;
    boolean loggedIn = false;
    
    public ClientHandler(String hostname, int port, boolean useTextUI) {
        this.hostname = hostname;
        this.port = port;
        ui = useTextUI ? new TextUserInterface(this)
                : new GraphicalUserInterface(this);
        net = new ClientNet(hostname, port, this);
        start();
    }
    
    public void login(String username) { net.login(username); }
    
    public void showUserInfoFail(String username) {
        // TODO
    }
    
    public void showUserInfo(String username, String playCount, String winCount,
            String winStreak) {
        // TODO
    }
    
    public void showChat(String username, String message) {
        // TODO
    }
    
    public void showChatJoin(String username) {
        // TODO
    }
    
    public void showChatLeave(String username) {
        // TODO
    }
}
