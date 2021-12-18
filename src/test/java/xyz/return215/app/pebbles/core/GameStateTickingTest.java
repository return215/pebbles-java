package xyz.return215.app.pebbles.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameStateTickingTest {
    
    GameState state;
    
    @BeforeEach
    void setUp() throws Exception {
        state = GameState.initState(7, 7, false);
    }
    
    @Test
    void testSelectPit() { fail("Not yet implemented"); }
    
    @Test
    void testTickPhase() { fail("Not yet implemented"); }
    
}
