package xyz.return215.app.pebbles;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import xyz.return215.app.pebbles.server.ServerHandler;
import xyz.return215.app.pebbles.server.ServerThread;
import xyz.return215.app.pebbles.util.ServerUtil;

public class PebblesServer {
    
    private static CommandLineParser clp = new DefaultParser();
    private static HelpFormatter hf = new HelpFormatter();
    private static CommandLine cmd;
    private static Options opts = new Options();
    
    private static int port;
    
    private static final String CMD_SYNTAX = PebblesServer.class
            .getSimpleName();
    
    private ArrayList<ServerThread> clients = new ArrayList<>();
    
    public static void main(String[] args) {
        
        try {
            cmd = parseArguments(args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            showHelp();
            System.exit(2);
        }
        
        // check for help
        if (cmd.hasOption("h")) {
            showHelp();
            System.exit(0);
        }
        
        port = (cmd.hasOption("p")) ? Integer.valueOf(cmd.getOptionValue("p"))
                : ServerUtil.DEFAULT_PORT;
        
        // start server handler
        new ServerHandler(port);
    }
    
    public static void showHelp() { hf.printHelp(CMD_SYNTAX, opts, true); }
    
    public static CommandLine parseArguments(String[] args)
            throws ParseException {
        Option p = Option.builder("p").longOpt("port").argName("port_num")
                .desc("Port to bind").hasArg().required(false).build();
        
        Option h = Option.builder("h").longOpt("help").desc("Show this help message")
                .hasArg(false).required(false).build();
        
        opts.addOption(p);
        opts.addOption(h);
        
        return clp.parse(opts, args, false);
    }
}
