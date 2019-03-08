package tests.piecetests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.Color;
import chess.pieces.King;

/**
 * KingTest --- class to test the movement functionality of a king
 * @author    Jeremy McMahan
 */
public class KingTest {
	/**
	 * Test king's basic ability to decide it can move to a square
	 */
	@Test
    public void testCanMoveTo() {
		King k = new King(Color.W, 2,4);
		assertEquals(true, k.canMoveTo(new int[] {3,5}));
		assertEquals(false, k.canMoveTo(new int[] {3,6}));
		assertEquals(false, k.canMoveTo(new int[] {2,8}));
		assertEquals(false, k.canMoveTo(new int[] {2,4})); //must move squares
    }

	/**
	 * Test king's ability to decide whether it can capture a piece
	 */
	@Test
	public void testCanCapture() {
		King k = new King(Color.W, 2,4);
		assertEquals(true, k.canCapture(new King(Color.B,3,5)));
		assertEquals(false, k.canCapture(new King(Color.B,3,6)));
		assertEquals(true, k.canCapture(new King(Color.B,1,4)));
		assertEquals(false, k.canCapture(new King(Color.W,3,5)));//don't capture same color
		assertEquals(false, k.canCapture(new King(Color.W,2,4)));
	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		assertEquals("♚", (new King(Color.B,2,3)).toString());
		assertEquals("♔", (new King(Color.W,2,3)).toString());
	}
}
