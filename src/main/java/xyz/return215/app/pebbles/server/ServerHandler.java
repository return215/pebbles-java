package xyz.return215.app.pebbles.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import xyz.return215.app.pebbles.core.Player;
import xyz.return215.app.pebbles.core.PlayerList;

public class ServerHandler {
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    
    private HashMap<ServerThread, Player> clients = new HashMap<>();
    private PlayerList players = new PlayerList();
    
    public ServerHandler(int port) {
        this.port = port;
        System.out.println("Listening for clients on port " + this.port + "...");
        
        try 
        {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            while (true) {
                // When a client connects, create a new thread
                clientSocket = serverSocket.accept();
                new ServerThread(this, clientSocket).start();
            }
        } catch (IOException ie) {
            System.err.println("Error listening on port " + port + ":\n"
                    + ie.getMessage());
            ie.printStackTrace();
            System.exit(1);
        }
    }
    
    public synchronized void addClient(ServerThread client) {
        clients.putIfAbsent(client, null);
    }
    
    public synchronized void removeClient(ServerThread client) {
        Player p = clients.get(client);
        if (p != null)
            players.remove(p);
        
        clients.remove(client);
    }
    
    public synchronized void addPlayer(ServerThread client, Player player) {
        clients.put(client, player);
        players.add(player);
    }
    
    public synchronized boolean hasPlayer(String playername) {
        return players.contains(playername);
    }
    
    public synchronized Optional<Player> getClient(ServerThread client) {
        return Optional.ofNullable(clients.get(client));
    }
    
    public synchronized Optional<Player> getPlayer(String username) {
        return players.get(username);
    }
    
    public synchronized Set<Player> getPlayers() {
        return players.getAll();
    }
    
    public synchronized Map<ServerThread, Player> getClients() {
        return Collections.unmodifiableMap(clients);
    }
    
    public void broadcastAll(String message) {
        for (Entry<ServerThread, Player> client: clients.entrySet()) {
            client.getKey().send(message);
        }
    }
}
