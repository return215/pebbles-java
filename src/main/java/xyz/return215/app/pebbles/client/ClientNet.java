package xyz.return215.app.pebbles.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

import xyz.return215.app.pebbles.net.NetProtocol;
import xyz.return215.app.pebbles.net.NetProtocol.NetCommands;
import xyz.return215.app.pebbles.util.ServerUtil;

public class ClientNet extends Thread {
    private String hostname;
    private int port;
    
    private String hostPortName;
    /** Server socket connection */
    private Socket socket;
    /** Writer to send commands to the server */
    private PrintWriter serverOut;
    private BufferedReader serverIn;
    
    private boolean connected = false;
    
    private ClientHandler client;
    
    private long retryDelay = 5000;
    
    /**
     * @return the hostname
     */
    public final String getHostname() { return hostname; }
    
    /**
     * @return the port
     */
    public final int getPort() { return port; }
    
    /**
     * @return the hostPortName
     */
    public final String getHostPortName() { return hostPortName; }
    
    public final boolean isConnected() { return connected; }
    
    public ClientNet(ClientHandler client) {
        this(ServerUtil.DEFAULT_HOSTNAME, ServerUtil.DEFAULT_PORT, client);
    }
    
    public ClientNet(String hostname, ClientHandler client) {
        this(hostname, ServerUtil.DEFAULT_PORT, client);
    }
    
    public ClientNet(int port, ClientHandler client) {
        this(ServerUtil.DEFAULT_HOSTNAME, port, client);
    }
    
    public ClientNet(String hostname, int port, ClientHandler client) {
        this.hostname = hostname;
        this.port = port;
        this.hostPortName = ServerUtil.hostPortString(hostname, port);
        this.client = client;
        start();
    }
    
    @Override
    public void run() { // TODO Auto-generated method stub
        try {
            // Try to connect until succeed
            while (!connect()) {
                System.out.println("Failed to connect. Retrying in "
                        + retryDelay / 1000.0f + "seconds");
                wait(retryDelay);
            }
            
            String inputLine;
            
            while ((inputLine = serverIn.readLine()) != null) {
                // process inputs here
                Optional<NetCommands> c = NetProtocol.getCommand(inputLine);
                
                String[] args = NetProtocol.getArgs(inputLine);
                
                switch (c.orElse(NetCommands.UNKNOWN)) {
                case USERNAME_SET:
                    if (NetProtocol.getBoolStat(args[0])) {
                        client.loggedIn = true;
                    }
                break;
                case USERNAME_INFO:
                    if (NetProtocol.getBoolStat(args[0])) {
                        client.showUserInfo(args[1], args[2], args[3], args[4]);
                    } else {
                        client.showUserInfoFail(args[1]);
                    }
                break;
                case LOBBY_INFO:
                break;
                case ROOM_CHAT:
                    client.showChat(args[0], NetProtocol.getRest(inputLine, 1));
                break;
                case ROOM_JOIN:
                    client.showChatJoin(args[0]);
                break;
                case ROOM_LEAVE:
                    client.showChatLeave(args[0]);
                break;
                case ROOM_READY:
                break;
                case GAME_START:
                break;
                case GAME_TURN:
                break;
                case GAME_SELECT:
                break;
                case GAME_FINISH:
                break;
                case GAME_WINNER:
                break;
                default:
                break;
                }
                
            }
            
            // handle inputs
            
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Client connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean connect() {
        // Stop double-connecting
        if (connected)
            return connected;
        
        try {
            socket = new Socket(hostname, port);
            serverOut = new PrintWriter(socket.getOutputStream(), true);
            serverIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server " + hostPortName);
        } catch (UnknownHostException e) {
            System.err.println("Unable to connect to host " + hostPortName
                    + ": " + e.getMessage());
            return connected = false;
        } catch (IOException e) {
            System.err.println(
                    "Unable to initialize socket I/O: " + e.getMessage());
            return connected = false;
        }
        return connected = true;
    }
    
    public void send(String message) {
        serverOut.println(message);
    }
    
    public boolean disconnect() {
        // Do not disconnect if it has aready been disconnected before.
        if (!connected)
            return connected;
        
        try {
            serverIn.close();
            serverOut.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Unable to close socket I/O: " + e.getMessage());
            e.printStackTrace();
        }
        return connected = false;
    }

    public void login(String username) {
        send(NetProtocol.usernameSet(username));
    }
    
    public void newLobby() {
        send(NetProtocol.lobbyCreate());
    }
    
    public void joinLobby(String roomID) {
        send(NetProtocol.lobbyJoin(roomID));
    }
    
    public void roomChat(String message) {
        send(NetProtocol.roomChat(message));
    }
    
    public void roomReady(boolean isReady) {
        send(NetProtocol.roomReady(isReady));
    }
    
    public void roomLeave() {
        send(NetProtocol.roomLeave());
    }
    
    public void unknownRequest() {
        send(NetProtocol.unknownCommand());
    }
    
    public void unknownRequest(String message) {
        send(NetProtocol.unknownCommand(message));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        disconnect();
    }
}
