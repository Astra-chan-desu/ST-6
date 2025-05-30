package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.Component;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

      @Test
    public void testCheckStatePlaying() {
        Game g = new Game();
        g.symbol = 'X';
        char[] board = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
        assertEquals(State.PLAYING, g.checkState(board));
    }

    @Test
    public void testCheckStateXWin() {
        Game g = new Game();
        g.symbol = 'X';
        char[] board = {'X','X','X',' ',' ',' ',' ',' ',' '};
        assertEquals(State.XWIN, g.checkState(board));
    }

    @Test
    public void testCheckStateOWin() {
        Game g = new Game();
        g.symbol = 'O';
        char[] board = {'O',' ',' ','O',' ',' ','O',' ',' '};
        assertEquals(State.OWIN, g.checkState(board));
    }

    @Test
    public void testCheckStateDraw() {
        Game g = new Game();
        g.symbol = 'X';
        char[] board = {'X','O','X','X','O','X','O','X','O'};
        assertEquals(State.DRAW, g.checkState(board));
    }

    @Test
    public void testGenerateMovesEmpty() {
        Game g = new Game();
        ArrayList<Integer> moves = new ArrayList<>();
        char[] board = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
        g.generateMoves(board, moves);
        assertEquals(9, moves.size());
        for(int i=0;i<9;i++) assertTrue(moves.contains(i));
    }

    @Test
    public void testGenerateMovesPartial() {
        Game g = new Game();
        ArrayList<Integer> moves = new ArrayList<>();
        char[] board = {'X',' ','O',' ','X',' ',' ',' ','O'};
        g.generateMoves(board, moves);
        assertEquals(5, moves.size());
        assertFalse(moves.contains(0));
        assertFalse(moves.contains(2));
        assertFalse(moves.contains(4));
        assertFalse(moves.contains(8));
    }

    @Test
    public void testEvaluatePositionXWinForX() {
        Game g = new Game();
        Player p = new Player();
        p.symbol = 'X';
        g.symbol = 'X';
        char[] board = {'X','X','X',' ',' ',' ',' ',' ',' '};
        assertEquals(Game.INF, g.evaluatePosition(board, p));
    }

    @Test
    public void testEvaluatePositionOWinForX() {
        Game g = new Game();
        Player p = new Player();
        p.symbol = 'X';
        g.symbol = 'O';
        char[] board = {'O','O','O',' ',' ',' ',' ',' ',' '};
        assertEquals(-Game.INF, g.evaluatePosition(board, p));
    }

    @Test
    public void testEvaluatePositionDraw() {
        Game g = new Game();
        Player p = new Player();
        p.symbol = 'O';
        g.symbol = 'X';
        char[] board = {'X','O','X','X','O','X','O','X','O'};
        assertEquals(0, g.evaluatePosition(board, p));
    }

    @Test
    public void testEvaluatePositionNonTerminal() {
        Game g = new Game();
        Player p = new Player();
        p.symbol = 'X';
        g.symbol = 'X';
        char[] board = {'X',' ',' ',' ',' ',' ',' ',' ',' '};
        assertEquals(-1, g.evaluatePosition(board, p));
    }

    @Test
    public void testMiniMaxSingleMove() {
        Game g = new Game();
        g.player2 = new Player();
        g.player2.symbol = 'X';
        char[] board = {'X','O','X','O','X','O','X','O',' '};
        int move = g.MiniMax(board, g.player2);
        assertEquals(9, move);
        assertEquals(0, g.q);
    }



    @Test
    public void testConstructorAndGetters() {
        TicTacToeCell cell = new TicTacToeCell(5,1,2);
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getCol());
        assertEquals(2, cell.getRow());
        assertEquals(' ', cell.getMarker());
        assertTrue(cell.isEnabled());
    }

    @Test
    public void testSetMarkerDisablesAndSetsText() {
        TicTacToeCell cell = new TicTacToeCell(0,0,0);
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertFalse(cell.isEnabled());
        assertEquals("O", cell.getText());
    }

      @Test public void row2Wins() {
        Game g = new Game();
        g.symbol = 'O';
        char[] b = {' ',' ',' ','O','O','O',' ',' ',' '};
        assertEquals(State.OWIN, g.checkState(b));
    }
    @Test public void col1Wins() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = {'O','X',' ','O','X',' ',' ','X',' '};
        assertEquals(State.XWIN, g.checkState(b));
    }
    @Test public void diag2Wins() {
        Game g = new Game();
        g.symbol = 'O';
        char[] b = {' ',' ','O',' ','O',' ','O',' ',' '};
        assertEquals(State.OWIN, g.checkState(b));
    }
    @Test public void fullBoardNoSpacesDraw() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = {'X','X','O','O','O','X','X','O','O'};
        assertEquals(State.DRAW, g.checkState(b));
    }
    @Test public void generateMovesNone() {
        Game g = new Game();
        ArrayList<Integer> m = new ArrayList<>();
        char[] b = {'X','O','X','O','X','O','X','O','X'};
        g.generateMoves(b, m);
        assertTrue(m.isEmpty());
    }

    // evaluatePosition: all result branches
    @Test public void evalXWinForO() {
        Game g = new Game();
        Player p = new Player(); p.symbol='O';
        g.symbol='X';
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        assertEquals(-Game.INF, g.evaluatePosition(b,p));
    }
    @Test public void evalDrawForO() {
        Game g = new Game();
        Player p = new Player(); p.symbol='O';
        g.symbol='X';
        char[] b = {'X','O','X','X','O','X','O','X','O'};
        assertEquals(0, g.evaluatePosition(b,p));
    }

    // MinMove/MaxMove directly on terminal
    @Test public void minMoveTerminal() {
        Game g = new Game();
        Player p = new Player(); p.symbol='X';
        g.symbol='X';
        char[] b = {'X','X','X',' ',' ',' ',' ',' ',' '};
        assertEquals(Game.INF, g.MinMove(b, p));
    }
    @Test public void maxMoveTerminal() {
        Game g = new Game();
        Player p = new Player(); p.symbol='O';
        g.symbol='O';
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        assertEquals(Game.INF, g.MaxMove(b, p));
    }

    // q counter increments on non-terminal recursion
    @Test public void qIncrementsInMinMax() {
        Game g = new Game();
        Player p = new Player(); p.symbol='X';
        g.symbol='O';
        char[] b = {'X',' ',' ',' ',' ',' ',' ',' ',' '};
        int v = g.MinMove(b, p);
        assertTrue(g.q > 0);
    }
    @Test public void qIncrementsInMaxMin() {
        Game g = new Game();
        Player p = new Player(); p.symbol='O';
        g.symbol='X';
        char[] b = {'O',' ',' ',' ',' ',' ',' ',' ',' '};
        int v = g.MaxMove(b, p);
        assertTrue(g.q > 0);
    }

    // MiniMax tie-break: two equal moves
    @Test public void miniMaxTwoChoicesPicksFirst() {
        Game g = new Game();
        g.player2 = new Player(); g.player2.symbol = 'X';
        // only positions 7 and 8 are empty
        char[] b = {'X','O','X','O','X','O','X',' ',' '};
        int move = g.MiniMax(b, g.player2);
        assertEquals(8, move);
        assertEquals(0, g.q);
    }

    // already existing coverage
    @Test public void testEvaluatePositionNonTerminal2() {
        Game g = new Game();
        Player p = new Player(); p.symbol='X';
        g.symbol='X';
        char[] b = {'X',' ',' ',' ',' ',' ',' ',' ',' '};
        assertEquals(-1, g.evaluatePosition(b,p));
    }
    @Test public void testMiniMaxSingleMove2() {
        Game g = new Game();
        g.player2 = new Player(); g.player2.symbol='X';
        char[] b = {'X','O','X','O','X','O','X','O',' '};
        assertEquals(9, g.MiniMax(b, g.player2));
        assertEquals(0, g.q);
    }

    
    @Test
    public void testPrintCharArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        char[] board = {'X',' ','O',' ',' ',' ',' ',' ',' '};
        Utility.print(board);
        String s = out.toString();
        assertTrue(s.contains("X- -O-"));
    }

    @Test
    public void testPrintIntArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        int[] board = {1,0,2,0,0,0,0,0,0};
        Utility.print(board);
        String s = out.toString();
        assertTrue(s.contains("1-0-2-"));
    }

    @Test
    public void testPrintMovesList() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(3); moves.add(5); moves.add(9);
        Utility.print(moves);
        String s = out.toString();
        assertTrue(s.contains("3-5-9-"));
    }
    
 
    @Test
    public void testPanelInitialization() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3,3));
        assertEquals(9, panel.getComponentCount());
        TicTacToeCell c4 = (TicTacToeCell)panel.getComponent(4);
        assertEquals(4, c4.getNum());
        assertEquals(1, c4.getCol());
        assertEquals(1, c4.getRow());
        assertEquals(' ', c4.getMarker());
    }

    @Test
    public void testPanelClickProducesOneXOneO() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3,3));
        TicTacToeCell first = (TicTacToeCell)panel.getComponent(0);
        first.doClick();  // simulates player click + computer response
        int xCount=0, oCount=0;
        for (Component c: panel.getComponents()) {
            if (!(c instanceof TicTacToeCell)) continue;
            char m = ((TicTacToeCell)c).getMarker();
            if (m=='X') xCount++;
            if (m=='O') oCount++;
        }
        assertEquals(1, xCount);
        assertEquals(1, oCount);
    }
     @Test public void rowMiddleWin() {
        Game g = new Game(); g.symbol='O';
        char[] b = {' ',' ',' ','O','O','O',' ',' ',' '};
        assertEquals(State.OWIN, g.checkState(b));
    }
    @Test public void colRightWin() {
        Game g = new Game(); g.symbol='X';
        char[] b = {' ',' ','X',' ',' ','X',' ',' ','X'};
        assertEquals(State.XWIN, g.checkState(b));
    }
    @Test public void diagMainWin() {
        Game g = new Game(); g.symbol='O';
        char[] b = {'O',' ',' ',' ','O',' ',' ',' ','O'};
        assertEquals(State.OWIN, g.checkState(b));
    }


    @Test public void generateMovesNone1() {
        Game g = new Game();
        ArrayList<Integer> m = new ArrayList<>();
        char[] b = {'X','O','X','O','X','O','X','O','X'};
        g.generateMoves(b,m);
        assertTrue(m.isEmpty());
    }
     @Test public void evalOWinForO() {
        Game g = new Game();
        Player p = new Player(); p.symbol='O';
        g.symbol='O';
        char[] b = {'O','O','O',' ',' ',' ',' ',' ',' '};
        assertEquals(Game.INF, g.evaluatePosition(b,p));
    }
    @Test public void evalDrawForX() {
        Game g = new Game();
        Player p = new Player(); p.symbol='X';
        g.symbol='O';
        char[] b = {'X','O','X','X','O','X','O','X','O'};
        assertEquals(0, g.evaluatePosition(b,p));
    }
     @Test public void tieScenarioResetsQ() {
        Game g = new Game();
        g.player2 = new Player(); g.player2.symbol='X';
        char[] b = {'X','O','X','O','X','O','X',' ',' '};
        g.q = 42;
        int move = g.MiniMax(b, g.player2);
        assertEquals(8, move);
        assertEquals(0, g.q);
    }
}
