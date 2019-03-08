package tests.piecetests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.Color;
import chess.pieces.Bishop;

/**
 * BishopTest --- class to test the functionality of the Bishop chess piece
 * @author    Jeremy McMahan
 */
public class BishopTest {
    /**
     * Tests the canMoveTo method
     */
	@Test
    public void testCanMoveTo() {
        Bishop b = new Bishop(Color.W,2,3);
        assertEquals(true, b.canMoveTo(new int[]{4,5})); 
        assertEquals(false, b.canMoveTo(new int[] {4,6}));
        assertEquals(false, b.canMoveTo(new int[] {2,3}));
    }

    /**
     * Tests the canCapture method
     */
	@Test
	public void testCanCapture() {
		Bishop b = new Bishop(Color.W,2,3);
		assertEquals(true, b.canCapture(new Bishop(Color.B,4,5)));
		assertEquals(false, b.canCapture(new Bishop(Color.B,3,5)));
		assertEquals(false, b.canCapture(new Bishop(Color.W,4,5)));
		assertEquals(false, b.canCapture(new Bishop(Color.B,6,3)));
	}

    /**
     * Tests the isBetween method
     */
	@Test
    public void testIsBetween() {
		Bishop b = new Bishop(Color.W,2,3);
		assertEquals(true, b.isBetween(new int[] {4,5}, new int[] {6,7}));
		assertEquals(false, b.isBetween(new int[] {1,2}, new int[] {3,4}));
		assertEquals(false, b.isBetween(new int[] {4, 5}, new int[] {1,2}));
		assertEquals(false, b.isBetween(new int[] {2,3}, new int[] {4,5}));
		assertEquals(false, b.isBetween(new int[] {6,3}, new int[] {7,8}));
        assertEquals(false, b.isBetween(new int[] {2,3}, new int[]{4,5})); //our square = middle
        assertEquals(false, b.isBetween(new int[] {4,5}, new int[]{4,5})); //middle = end
    }

    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals("♝", (new Bishop(Color.B,2,3)).toString());
        assertEquals("♗", (new Bishop(Color.W,2,3)).toString());
    }

}
