package xyz.return215.app.pebbles.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import xyz.return215.app.pebbles.util.ServerUtil;

public class PebblesServerThread extends Thread {
    private Socket socket;
    private int port;
    private String host;
    private String remoteHost;
    
    public PebblesServerThread(Socket s) {
        super(PebblesServerThread.class.getSimpleName());
        socket = s;
        port = socket.getPort();
        host = socket.getInetAddress().getHostAddress();
        remoteHost = ServerUtil.hostPortString(host, port);
    }
    
    @Override
    public void run() { // TODO Auto-generated method stub
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println("Connected to client on " + remoteHost + ".");
            
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
            
            socket.close();
            System.out.println("Connection to client " + remoteHost + " closed.");
        } catch (IOException ie) {
            System.err.println("Error reading from client " + remoteHost + ":\n" + ie.getMessage());
            ie.printStackTrace();
        }
    }
}
