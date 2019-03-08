package tests.piecetests;

import chess.Color;
import chess.pieces.UltraRook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * UltraRookTest --- class to test the movement functionality of the UltraRook chess piece
 * @author    Jeremy McMahan
 */
public class UltraRookTest {
    /**
	 * Given a square with no piece on it tests if the ultra rook correctly decides if it can move to that square
	 */
    @Test
    public void testCanMoveTo() {
        UltraRook ur = new UltraRook(Color.B,2,3);
        //test rook moves
        assertEquals(true, ur.canMoveTo(new int[] {2,99}));
        assertEquals(false, ur.canMoveTo(new int[] {3,7}));
        assertEquals(true, ur.canMoveTo(new int[] {-5,3}));
        assertEquals(false, ur.canMoveTo(new int[] {2,3}));
        //test the special move
        assertEquals(true, ur.canMoveTo(new int[]{3,4}));
    }

    /**
     * Given a piece, tests if the ultra rook correctly decides if it can capture that piece
     */
    @Test
    public void testCanCapture() {
        UltraRook ur = new UltraRook(Color.B,2,3);
        //test rook capture
        assertEquals(true, ur.canCapture(new UltraRook(Color.W,2,99)));
        assertEquals(false, ur.canCapture(new UltraRook(Color.B, 2,7)));
        assertEquals(true, ur.canCapture(new UltraRook(Color.W,-5,3)));
        assertEquals(false, ur.canCapture(new UltraRook(Color.B,2,3)));
        //test the special capture
        assertEquals(true, ur.canCapture(new UltraRook(Color.W, 3,4)));
    }

    /**
     * Tests whether the ultra rook correctly determines when a square is between it and another desired square
     */
    @Test
    public void testIsBetween() {
        UltraRook ur = new UltraRook(Color.W,2,3);
        assertEquals(false, ur.isBetween(new int[] {5,3}, new int[] {2,7})); // both can move to, but one vertical one horizontal
        assertEquals(true, ur.isBetween(new int[] {4,3}, new int[] {6,3})); //both in correctly in a line vertically
        assertEquals(false, ur.isBetween(new int[] {1,3}, new int[] {3,3})); //both horizontal but in wrong order
        assertEquals(true, ur.isBetween(new int[] {2, -4}, new int[] {2,-8})); //both correct horizontally with negatives
        assertEquals(false, ur.isBetween(new int[] {2,3}, new int[] {4,5})); //current square = middle
        assertEquals(false, ur.isBetween(new int[] {2,5}, new int[]{2,5})); //middle = end
    }

    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals("♠", (new UltraRook(Color.B,2,3)).toString());
        assertEquals("♤", (new UltraRook(Color.W,2,3)).toString());
    }
}
