package xyz.return215.app.pebbles;

import org.apache.commons.cli.*;

public class PebblesServer {

    private static CommandLineParser clp;
    private static HelpFormatter hf;
    private static CommandLine cmd;
    private static Options opts;
    
    private static int port;
    
    /** Default server port. It is the T9 of "dakon". */
    private static final int DEFAULT_PORT = 32566;    
    private static final String CMD_SYNTAX = 
	    PebblesServer.class.getSimpleName();

    public static void main(String[] args) {
	// TODO Auto-generated method stub

	try {
	    cmd = createOptions(args);
	} catch (ParseException e) {
	    System.err.println(e.getMessage());
	    hf.printHelp(CMD_SYNTAX, opts, true);
	    System.exit(1);
	}
	
	port = (cmd.hasOption("p"))
		? Integer.valueOf(cmd.getOptionValue("p"))
		: DEFAULT_PORT;
	
	System.out.println(port);
    }

    public static CommandLine createOptions(String[] args) throws ParseException {
	opts = new Options();
	
	Option p = Option.builder("p")
			 .longOpt("port")
			 .argName("port_num")
			 .desc("Port to bind")
			 .hasArg()
			 .required(false)
			 .build();
	
	opts.addOption(p);
	
	clp = new DefaultParser();
	hf = new HelpFormatter();
	
	return clp.parse(opts, args, false);
    }
}
