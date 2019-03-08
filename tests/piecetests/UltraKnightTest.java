package tests.piecetests;

import chess.Color;
import chess.pieces.UltraKnight;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * UltraKnightTest --- class to test the functionality of the UltraKnight chess piece
 * @author    Jeremy McMahan
 */
public class UltraKnightTest {
    /**
	 * Tests if the knight correctly decides whether it can move to a square
	 */
    @Test
    public void testCanMoveTo() {
        UltraKnight uk = new UltraKnight(Color.W, 2,3);
        //test the the Knight functionality
        assertEquals(true, uk.canMoveTo(new int[] {4,4}));
        assertEquals(false, uk.canMoveTo(new int[] {4,5}));
        assertEquals(true, uk.canMoveTo(new int[] {3,5}));
        assertEquals(true, uk.canMoveTo(new int[] {1,1}));
        assertEquals(false, uk.canMoveTo(new int[] {2,3})); //check we can't stay on our square
        //test the new move
        assertEquals(true, uk.canMoveTo(new int[] {2,9}));
    }

    /**
     * Tests if the queen correctly decides whether it can capture a piece
     */
    @Test
    public void testCanCapture() {
        UltraKnight uk = new UltraKnight(Color.W, 2,3);
        assertEquals(true, uk.canCapture(new UltraKnight(Color.B,4,4)));
        assertEquals(false, uk.canCapture(new UltraKnight(Color.W,4,4))); //check if capture our own piece
        assertEquals(true, uk.canCapture(new UltraKnight(Color.B,3,5)));
        assertEquals(true, uk.canCapture(new UltraKnight(Color.B,1,1)));
        assertEquals(false, uk.canCapture(new UltraKnight(Color.B,2,3))); //check we can't capture ourselves
    }

    /**
     * Tests if a piece is to the right of the UltraKnight in the same row
     */
    @Test
    public void testIsBetween() {
        UltraKnight uk = new UltraKnight(Color.W, 2,3);
        assertEquals(true, uk.isBetween(new int[]{2,6}, new int[]{2,8}));
        assertEquals(false, uk.isBetween(new int[]{2,3}, new int[]{2,8}));
        assertEquals(false, uk.isBetween(new int[]{3,3}, new int[]{2,8}));
    }

    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals("♦", (new UltraKnight(Color.B,2,3)).toString());
        assertEquals("♢", (new UltraKnight(Color.W,2,3)).toString());
    }
}
