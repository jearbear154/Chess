package tests.piecetests;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.Color;
import chess.pieces.Rook;

/**
 * RookTest --- class to test the logic of the rook movements
 * @author    Jeremy McMahan
 */
public class RookTest {
	/**
	 * Given a square with no piece on it tests if the rook correctly decides if it can move to that square
	 */
	@Test
    public void testCanMoveTo() {
		Rook r = new Rook(Color.B,2,3);
		assertEquals(true, r.canMoveTo(new int[] {2,99}));
		assertEquals(false, r.canMoveTo(new int[] {3,7}));
		assertEquals(true, r.canMoveTo(new int[] {-5,3}));
		assertEquals(false, r.canMoveTo(new int[] {2,3}));
    }

	/**
	 * Given a piece, tests if the rook correctly decides if it can capture that piece
	 */
	@Test
	public void testCanCapture() {
		Rook r = new Rook(Color.B,2,3);
		assertEquals(true, r.canCapture(new Rook(Color.W,2,99)));
		assertEquals(false, r.canCapture(new Rook(Color.B, 2,7)));
		assertEquals(true, r.canCapture(new Rook(Color.W,-5,3)));
		assertEquals(false, r.canCapture(new Rook(Color.B,2,3)));
	}
    
	/**
	 * Tests whether the rook correctly determines when a square is between it and another desired square
	 */
    @Test
    public void testIsBetween() {
		Rook r = new Rook(Color.W,2,3);
		assertEquals(false, r.isBetween(new int[] {5,3}, new int[] {2,7})); // both can move to, but one vertical one horizontal
		assertEquals(true, r.isBetween(new int[] {4,3}, new int[] {6,3})); //both in correctly in a line vertically
		assertEquals(false, r.isBetween(new int[] {1,3}, new int[] {3,3})); //both horizontal but in wrong order
		assertEquals(true, r.isBetween(new int[] {2, -4}, new int[] {2,-8})); //both correct horizontally with negatives
		assertEquals(false, r.isBetween(new int[] {2,3}, new int[] {4,5})); //includes current square
		assertEquals(false, r.isBetween(new int[] {2,5}, new int[]{2,5})); //middle = end
    }

    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals("♜", (new Rook(Color.B,2,3)).toString());
        assertEquals("♖", (new Rook(Color.W,2,3)).toString());
    }
}
