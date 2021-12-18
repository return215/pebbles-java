package xyz.return215.app.pebbles.util;

public class ServerUtil {
    /** Default server port. It is the T9 of "dakon". */
    public static final int DEFAULT_PORT = 32566;
    public static final String DEFAULT_HOSTNAME = "localhost";
    
    public static String hostPortString(String host, int port) {
        return host + ":" + port;
    }
    
    public static String parameterize(String... params) {
        if (params.length == 0) return "";
        StringBuilder s = new StringBuilder(params[0]);
        for (int i = 1; i < params.length; i++) {
            s.append(" ").append(params[i]);
        }
        
        return s.toString();
    }
}
