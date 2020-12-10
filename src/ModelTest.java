import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ModelTest {
    
    @Test
    public void playersStartsWithPlayer1() {
        Connect4Game t = new Connect4Game();
        assertTrue(t.getCurrentPlayer());
    }
    
    @Test
    public void playersSwitchesToPlayer2() {
        Connect4Game t = new Connect4Game();
        t.playTurn(0);
        assertFalse(t.getCurrentPlayer());
    }
    
    @Test
    public void playersSwitchesBackToPlayer1() {
        Connect4Game t = new Connect4Game();
        t.playTurn(0);
        t.playTurn(0);
        assertTrue(t.getCurrentPlayer());
    }
    
    @Test
    public void numTurnsIncrements() {
        Connect4Game t = new Connect4Game();
        t.playTurn(0);
        t.playTurn(0);
        t.playTurn(0);
        t.playTurn(0);
        assertEquals(4, t.getNumTurms());
    }
    
    @Test
    public void playerWinsHorizontalLeft() {
        Connect4Game t = new Connect4Game();
        t.playTurn(0);
        t.playTurn(0);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(3);
        assertTrue(t.getCurrentPlayer());
        assertEquals(1, t.getWinner());
        assertTrue(t.getGameOver());
    }
    
    @Test
    public void playerWinsHorizontalRight() {
        Connect4Game t = new Connect4Game();
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(0);
        t.playTurn(0);
        assertTrue(t.getCurrentPlayer());
        assertEquals(1, t.getWinner());
        assertTrue(t.getGameOver());
    }
    
    @Test
    public void playerWinsVerticalBottom() {
        Connect4Game t = new Connect4Game();
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(3);
        assertTrue(t.getCurrentPlayer());
        assertEquals(1, t.getWinner());
        assertTrue(t.getGameOver());
    }
    
    @Test
    public void playerWinsDiagonalTopRight() {
        Connect4Game t = new Connect4Game();
        t.playTurn(1);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(4);
        t.playTurn(3);
        t.playTurn(4);
        t.playTurn(4);
        t.playTurn(2);
        t.playTurn(4);
        assertTrue(t.getCurrentPlayer());
        assertEquals(1, t.getWinner());
        assertTrue(t.getGameOver());
    }
    
    @Test
    public void playerWinsDiagonalBottomRight() {
        Connect4Game t = new Connect4Game();
        t.playTurn(4);
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(1);
        assertTrue(t.getCurrentPlayer());
        assertEquals(1, t.getWinner());
        assertTrue(t.getGameOver());
    }
    
    @Test
    public void playerWinsDiagonalTopLeft() {
        Connect4Game t = new Connect4Game();
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(4);
        assertTrue(t.getCurrentPlayer());
        assertEquals(1, t.getWinner());
        assertTrue(t.getGameOver());
    }
    
    @Test
    public void playerWinsDiagonalBottomLeft() {
        Connect4Game t = new Connect4Game();
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(4);
        t.playTurn(3);
        t.playTurn(4);
        t.playTurn(4);
        t.playTurn(2);
        t.playTurn(4);
        t.playTurn(1);
        assertTrue(t.getCurrentPlayer());
        assertEquals(1, t.getWinner());
        assertTrue(t.getGameOver());
    }
    
    @Test
    public void gameOverRegistersFalse() {
        Connect4Game t = new Connect4Game();
        t.playTurn(2);
        t.playTurn(2);
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void restWorksBeforeWinningGame() {
        Connect4Game t = new Connect4Game();
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(4);
        t.playTurn(1);
        t.reset();
        assertTrue(t.getCurrentPlayer());
        assertEquals(0, t.getNumTurms());
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void restWorksAfterWinningGame() {
        Connect4Game t = new Connect4Game();
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(1);
        t.playTurn(1);
        t.playTurn(0);
        t.playTurn(0);
        t.reset();
        assertTrue(t.getCurrentPlayer());
        assertEquals(0, t.getNumTurms());
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void singleUndoWorks() {
        Connect4Game t = new Connect4Game();
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(1);
        assertEquals(5, t.getNumTurms());
        assertFalse(t.getCurrentPlayer());
        assertFalse(t.getGameOver());
        t.undo();
        assertTrue(t.getCurrentPlayer());
        assertEquals(4, t.getNumTurms());
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void doulbeUndoWorks() {
        Connect4Game t = new Connect4Game();
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(1);
        assertEquals(5, t.getNumTurms());
        assertFalse(t.getCurrentPlayer());
        assertFalse(t.getGameOver());
        t.undo();
        t.undo();
        assertFalse(t.getCurrentPlayer());
        assertEquals(3, t.getNumTurms());
        assertFalse(t.getGameOver());
    }
    
    @Test
    public void undoToIntialState() {
        Connect4Game t = new Connect4Game();
        t.playTurn(3);
        t.playTurn(3);
        t.playTurn(2);
        t.playTurn(2);
        t.playTurn(1);
        assertEquals(5, t.getNumTurms());
        assertFalse(t.getCurrentPlayer());
        assertFalse(t.getGameOver());
        t.undo();
        t.undo();
        t.undo();
        t.undo();
        t.undo();
        assertTrue(t.getCurrentPlayer());
        assertEquals(0, t.getNumTurms());
        assertFalse(t.getGameOver());
    }

}
