package xyz.return215.app.pebbles.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

import xyz.return215.app.pebbles.core.Player;
import xyz.return215.app.pebbles.net.NetProtocol;
import xyz.return215.app.pebbles.net.NetProtocol.NetCommands;
import static xyz.return215.app.pebbles.util.ServerUtil.*;

public class ServerThread extends Thread {
    private Socket client;
    private int port;
    private String host;
    private String remoteHost;
    private PrintWriter cout;
    private BufferedReader cin;
    private ServerHandler server;
    private Player player;
    
    public ServerThread(ServerHandler sh, Socket s) {
        super(ServerThread.class.getSimpleName());
        server = sh;
        client = s;
        port = client.getPort();
        host = client.getInetAddress().getHostAddress();
        remoteHost = hostPortString(host, port);
        try {
            cout = new PrintWriter(client.getOutputStream(), true);
            cin = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Connected to client on " + remoteHost + ".");
            
            String inputLine;
            
            while ((inputLine = cin.readLine()) != null) {
                // process inputs here
                Optional<NetCommands> c = NetProtocol.getCommand(inputLine);
                
                String[] args = NetProtocol.getArgs(inputLine);
                
                boolean status = false;
                
                switch (c.orElse(NetCommands.UNKNOWN)) {
                case GAME_SELECT:
                    unknownRequest("not implemented");
                break;
                case LOBBY_CREATE:
                    unknownRequest("not implemented");
                break;
                case LOBBY_JOIN:
                    unknownRequest("not implemented");
                break;
                case ROOM_CHAT:
                    roomChat(player.getUsername(), NetProtocol.getRest(inputLine, 1));
                break;
                case ROOM_LEAVE:
                    roomLeave(player.getUsername());
                break;
                case ROOM_READY:
                    unknownRequest("not implemented");
                break;
                case USERNAME_SET:
                    setUsername(args[1]);
                break;
                default:
                    unknownRequest();
                }
                
            }
            
            client.close();
            System.out
                    .println("Connection to client " + remoteHost + " closed.");
        } catch (IOException ie) {
            System.err.println("Error reading from client " + remoteHost + ":\n"
                    + ie.getMessage());
            ie.printStackTrace();
        }
    }
    
    public void send(String message) { cout.println(message); }
    
    public void setUsername(String username) {
        boolean status = server.hasPlayer(username);
        if (!status) {
            player = new Player(username);
            server.addPlayer(this, player);
        }
        String response = NetProtocol.usernameSetResult(username, !status);
        send(response);
    }
    
    public void userInfo(String username) {
        boolean status = server.hasPlayer(username);
        String response;
        if (status) {
            Player p = server.getPlayer(username).get();
            response = NetProtocol.usernameInfoSuccess(p);
        } else {
            response = NetProtocol.usernameInfoFail(username);
        }
        send(response);
    }
    
    public void roomChat(String username, String message) {
        String response = parameterize(NetCommands.ROOM_CHAT.value, username,
                message);
        server.broadcastAll(response);
    }
    
    public void roomJoin(String username) {
        server.broadcastAll(NetProtocol.roomJoin(username));
    }

    public void roomLeave(String username) {
        server.broadcastAll(NetProtocol.roomLeave(username));
    }
    
    public void unknownRequest() {
        send(NetProtocol.unknownCommand());
    }
    
    public void unknownRequest(String message) {
        send(NetProtocol.unknownCommand(message));
    }
}
