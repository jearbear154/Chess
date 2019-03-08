package tests.piecetests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.Color;
import chess.pieces.Knight;

/**
 * KnightTest --- class to test the movement functionality of a Knight chess piece
 * @author    Jeremy McMahan
 */
public class KnightTest {
	/**
	 * Tests if the knight correctly decides whether it can move to a square
	 */
	@Test
    public void testCanMoveTo() {
		Knight k = new Knight(Color.W, 2,3);
		assertEquals(true, k.canMoveTo(new int[] {4,4}));
		assertEquals(false, k.canMoveTo(new int[] {4,5}));
		assertEquals(true, k.canMoveTo(new int[] {3,5}));
		assertEquals(true, k.canMoveTo(new int[] {1,1}));
		assertEquals(false, k.canMoveTo(new int[] {2,3})); //check we can't stay on our square
    }

	/**
	 * Tests if the queen correctly decides whether it can capture a piece
	 */
	@Test
	public void testCanCapture() {
		Knight k = new Knight(Color.W, 2,3);
		assertEquals(true, k.canCapture(new Knight(Color.B,4,4)));
		assertEquals(false, k.canCapture(new Knight(Color.W,4,4))); //check if capture our own piece
		assertEquals(true, k.canCapture(new Knight(Color.B,3,5)));
		assertEquals(true, k.canCapture(new Knight(Color.B,1,1)));
		assertEquals(false, k.canCapture(new Knight(Color.B,2,3))); //check we can't capture ourselves
	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		assertEquals("♞", (new Knight(Color.B,2,3)).toString());
		assertEquals("♘", (new Knight(Color.W,2,3)).toString());
	}
}
