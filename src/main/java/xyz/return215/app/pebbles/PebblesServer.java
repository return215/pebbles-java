package xyz.return215.app.pebbles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import xyz.return215.app.pebbles.util.ServerUtil;

public class PebblesServer {
    
    private static CommandLineParser clp = new DefaultParser();
    private static HelpFormatter hf = new HelpFormatter();
    private static CommandLine cmd;
    private static Options opts = new Options();
    
    private static int port;
    
    /** Default server port. It is the T9 of "dakon". */
    private static final int DEFAULT_PORT = 32566;
    private static final String CMD_SYNTAX = PebblesServer.class.getSimpleName();
    
    public static void main(String[] args) {
        
        try {
            cmd = parseArguments(args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            hf.printHelp(CMD_SYNTAX, opts, true);
            System.exit(2);
        }
        
        port = (cmd.hasOption("p")) ? Integer.valueOf(cmd.getOptionValue("p")) : DEFAULT_PORT;
        System.out.println("Listening for a client on port " + port + "...");
        
        try (
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String remoteHost = ServerUtil.hostPortString(clientSocket.getInetAddress().toString(), clientSocket.getPort());
            System.out.println("Connected to client on " + remoteHost + ".");
            
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException ie) {
            System.err.println("Error reading from port " + port);
            System.err.println(ie.getMessage());
            System.exit(1);
        }
        
    }
    
    public static CommandLine parseArguments(String[] args) throws ParseException {
        Option p = Option.builder("p").longOpt("port").argName("port_num").desc("Port to bind").hasArg().required(false)
                .build();
        
        opts.addOption(p);
        
        return clp.parse(opts, args, false);
    }
}
