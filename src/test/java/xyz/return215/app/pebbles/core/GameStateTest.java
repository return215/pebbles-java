package xyz.return215.app.pebbles.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class GameStateTest {
    GameState state;
    
    @BeforeAll
    void setUpBeforeClass() throws Exception {
        state = GameState.initState(7, 7, false);
    }
    
    @Test
    void testGetPitCount() { assertEquals(state.pitCount, 7); }
    
    @Test
    void testGetPlayerPitCount() { assertEquals(state.playerPitCount, 8); }
    
    @Test
    void testGetTotalPitCount() { assertEquals(state.totalPitCount, 16); }
    
    @Test
    void testIsCurrentlyPlayerTwo() {
        assertEquals(state.isCurrentlyPlayerTwo(), false);
    }
    
    @Test
    void testGetPhase() { assertEquals(state.phase, GameStatePhase.WAITING); }
    
    @Test
    void testInitState() {
        assertTrue(Arrays.stream(state.getPlayerPits(false))
                .allMatch(n -> n == 7));
        assertTrue(Arrays.stream(state.getPlayerPits(true))
                .allMatch(n -> n == 7));
    }
    
    @Test
    void testGetPlayerPits() {
        assertTrue(Arrays.stream(state.getPlayerPits(state.currentlyPlayerTwo))
                .allMatch(n -> n == 7));
    }
    
    @Test
    void testGetPlayerHomePot() {
        assertEquals(state.getPlayerHomePot(false), 0);
        assertEquals(state.getPlayerHomePot(true), 0);
    }
    
    @Test
    void testGetPlayerHomePotPos() {
        assertEquals(state.getPlayerHomePotPos(false), 7);
        assertEquals(state.getPlayerHomePotPos(true), 15);
    }
    
    @Test
    void testGetPlayerPitPos() { 
        assertEquals(state.getPlayerPitPos(false, 4), 4);
        assertEquals(state.getPlayerPitPos(true, 2), 10);
    }
    
    @Test
    void testGetCrossPit() {
        assertEquals(state.getCrossPit(false, 1), 13);
        assertEquals(state.getCrossPit(true, 13), 1);
    }
    
}
