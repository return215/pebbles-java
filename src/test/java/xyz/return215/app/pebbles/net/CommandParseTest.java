package xyz.return215.app.pebbles.net;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import xyz.return215.app.pebbles.net.NetProtocol.NetCommands;

class CommandParseTest {
    String line = "/rc twilight7 Does is really matter?";
    
    @Test
    void testGetCommand() {
        assertEquals(NetCommands.ROOM_CHAT, NetProtocol.getCommand(line).get());
    }
    
    @Test
    void testGetArgs() {
        assertEquals("twilight7", NetProtocol.getArgs(line)[0]);
    }
    
    @Test
    void testGetRest() {
        assertEquals("Does is really matter?", NetProtocol.getRest(line, 2));
    }
    
}
