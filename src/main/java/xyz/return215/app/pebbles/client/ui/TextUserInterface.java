package xyz.return215.app.pebbles.client.ui;

import java.util.Scanner;

import xyz.return215.app.pebbles.client.ClientHandler;
import xyz.return215.app.pebbles.core.Player;

public class TextUserInterface extends UserInterface {
    private Scanner stdin;
    private ClientHandler client;
    
    public TextUserInterface(ClientHandler c) {
        super(c);
        stdin = new Scanner(System.in);
        start();
    }
    
    @Override
    public void run() { // TODO Auto-generated method stub
        super.run();
        showLogin();
    }
    
    @Override
    public void showLogin() { // TODO Auto-generated method stub
        System.out.print("Username: ");
        String uname = stdin.nextLine();
        
        if (!Player.isSanitizedUsername(uname)) {
            System.out.println(
                    "Your username will be: " + Player.sanitizeUsername(uname));
            System.out.print("Continue (Y/n)?");
        }
        
        client.login(uname);
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
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        stdin.close();
    }
    
}
