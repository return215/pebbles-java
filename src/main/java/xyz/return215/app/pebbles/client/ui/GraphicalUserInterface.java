package xyz.return215.app.pebbles.client.ui;

import xyz.return215.app.pebbles.client.ClientHandler;
import xyz.return215.app.pebbles.client.ui.graphical.frame.LobbyFrame;
import xyz.return215.app.pebbles.client.ui.graphical.frame.LoginFrame;

public class GraphicalUserInterface extends UserInterface {
    
    LoginFrame loginUI;
    LobbyFrame lobbyUI;
    
    public GraphicalUserInterface(ClientHandler client) {
        super(client);
        loginUI = new LoginFrame(client);
        lobbyUI = new LobbyFrame(client);
        start();
    }
    
    @Override
    public void run() { // TODO Auto-generated method stub
        super.run();
        showLogin();
    }
    
    @Override
    public void showLogin() { // TODO Auto-generated method stub
        loginUI.setVisible(true);
    }
    
    @Override
    public void showMain() { // TODO Auto-generated method stub
    }
    
    @Override
    public void showLobby() { // TODO Auto-generated method stub
    }
    
    @Override
    public void showChat() { // TODO Auto-generated method stub
    }
    
    @Override
    public void showGame() { // TODO Auto-generated method stub
    }
    
    @Override
    public void showResults() { // TODO Auto-generated method stub
    }
    
}
