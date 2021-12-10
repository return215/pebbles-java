package xyz.return215.app.pebbles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import xyz.return215.app.pebbles.util.ServerUtil;

public class PebblesClient {
    private static CommandLineParser clp = new DefaultParser();
    private static HelpFormatter hf = new HelpFormatter();
    private static CommandLine cmd;
    private static Options opts = new Options();
    
    private static int port;
    private static String host;
    
    /** Default server port. It is the T9 of "dakon". */
    private static final int DEFAULT_PORT = 32566;
    private static final String DEFAULT_HOST = "localhost";
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
        host = (cmd.hasOption("h")) ? cmd.getOptionValue("h") : DEFAULT_HOST;
        String hostText = ServerUtil.hostPortString(host, port);
        
        System.out.println("Connecting to " + hostText + "...");
        try (
            Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String remoteHost = ServerUtil.hostPortString(clientSocket.getInetAddress().toString(), clientSocket.getPort());
            System.out.println("Connected to server on " + remoteHost + ".");
            
            String inputLine;
            
            while ((inputLine = stdin.readLine()) != null) {
                out.println(inputLine);
                System.out.println(in.readLine());
            }
        } catch (UnknownHostException uhe) {
            System.err.println("Cannot find server host " + hostText);
            System.err.println(uhe.getMessage());
            System.exit(1);
        } catch (IOException ie) {
            System.err.println("Error reading from " + hostText);
            System.err.println(ie.getMessage());
            System.exit(1);
        }
    }
    
    public static CommandLine parseArguments(String[] args) throws ParseException {
        Option p = Option.builder("p").longOpt("port").argName("port_num").desc("Port to connect").hasArg()
                .required(false).build();
        
        Option h = Option.builder("h").longOpt("hostname").argName("hostname").desc("Server host name").hasArg()
                .required(false).build();
        
        opts.addOption(p);
        opts.addOption(h);
        
        return clp.parse(opts, args, false);
    }
}
