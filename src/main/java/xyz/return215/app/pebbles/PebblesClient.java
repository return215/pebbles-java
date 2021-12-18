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

import xyz.return215.app.pebbles.client.ClientHandler;
import xyz.return215.app.pebbles.util.ServerUtil;

public class PebblesClient {
    private static CommandLineParser clp = new DefaultParser();
    private static HelpFormatter hf = new HelpFormatter();
    private static CommandLine cmd;
    private static Options opts = new Options();
    
    private static final String CMD_SYNTAX = PebblesServer.class
            .getSimpleName();
    
    public static void main(String[] args) {
        
        try {
            cmd = parseArguments(args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            hf.printHelp(CMD_SYNTAX, opts, true);
            System.exit(2);
        }
        
        if (cmd.hasOption("h")) {
            showHelp();
            System.exit(0);
        }
        int port = (cmd.hasOption("p")) ? Integer.valueOf(cmd.getOptionValue("p"))
                : ServerUtil.DEFAULT_PORT;
        String host = (cmd.hasOption("h")) ? cmd.getOptionValue("h") : ServerUtil.DEFAULT_HOSTNAME;
        boolean useTextUI = (cmd.hasOption("t")) ? Boolean.getBoolean(cmd.getOptionValue("t")) : false;
        
        new ClientHandler(host, port, useTextUI);
    }
    
    public static void showHelp() { hf.printHelp(CMD_SYNTAX, opts, true); }
    public static CommandLine parseArguments(String[] args)
            throws ParseException {
        Option p = Option.builder("p").longOpt("port").argName("port_num")
                .desc("Port to connect").hasArg().required(false).build();
        
        Option n = Option.builder("n").longOpt("hostname").argName("hostname")
                .desc("Server host name").hasArg().required(false).build();
        
        Option t = Option.builder("t").longOpt("text")
                .desc("Run in text mode, or GUI if not specified.")
                .required(false).build();
        
        Option h = Option.builder("h").longOpt("help").desc("Show this help message")
                .hasArg(false).required(false).build();
        opts.addOption(p);
        opts.addOption(n);
        opts.addOption(t);
        opts.addOption(h);
        
        return clp.parse(opts, args, false);
    }
}
