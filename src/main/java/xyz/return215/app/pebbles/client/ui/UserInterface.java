package xyz.return215.app.pebbles.client.ui;

import xyz.return215.app.pebbles.client.ClientHandler;

public abstract class UserInterface extends Thread {
    protected ClientHandler client;
    
    public UserInterface(ClientHandler client) {
        this.client = client;
    }
    
    public abstract void showLogin();
    public abstract void showMain();
    public abstract void showLobby();
    public abstract void showChat();
    public abstract void showGame();
    public abstract void showResults();
}
