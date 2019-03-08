package tests.piecetests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.Color;
import chess.pieces.Queen;

/**
 * QueenTest --- class to test the logic of the queen movements. Mostly a combination of the rook and bishop tests
 * @author    Jeremy McMahan
 */
public class QueenTest {
	/**
	 * Tests if the queen correctly decides whether it can move to a square
	 */
	@Test
	public void testCanMoveTo() {
		Queen q = new Queen(Color.W, 2,4);
		assertEquals(true, q.canMoveTo(new int[] {5,7})); //bishop rule test
		assertEquals(false, q.canMoveTo(new int[] {5,5}));
		assertEquals(true, q.canMoveTo(new int[] {2,8})); //rook rule test
		assertEquals(false, q.canMoveTo(new int[]{2,4})); //current square test
	 }

    /**
     * Tests if the queen correctly decides whether it can capture a piece
     */
	@Test
	public void testCanCapture() {
	    Queen q = new Queen(Color.W, 2,4);
	    assertEquals(true, q.canCapture(new Queen(Color.B,5,7))); //bishop rule
	    assertEquals(false, q.canCapture(new Queen(Color.W, 5,7))); //don't kill same color
		assertEquals(true, q.canCapture(new Queen(Color.B,2,8))); //rook rule
		assertEquals(false, q.canCapture(new Queen(Color.B,2,4))); //current square test
	 }
    /**
     * Tests if the queen correctly decides when a square is along its legal path of movement to another square
     */
	@Test
	public void testIsBetween() {
		Queen q = new Queen(Color.B, 2, 3);
		assertEquals(true, q.isBetween(new int[] {4,5}, new int[] {6,7})); //test Bishop rules
 		assertEquals(false, q.isBetween(new int[] {1,2}, new int[] {3,4}));
 		assertEquals(false, q.isBetween(new int[] {4, 5}, new int[] {1,2}));
 		assertEquals(false, q.isBetween(new int[] {2,3}, new int[] {4,5}));
 		assertEquals(true, q.isBetween(new int[] {-1,0}, new int[] {-5,-4}));
 		
 		assertEquals(false, q.isBetween(new int[] {5,3}, new int[] {2,7})); // test Rook rules
		assertEquals(true, q.isBetween(new int[] {4,3}, new int[] {6,3})); //both in correctly in a line vertically
		assertEquals(false, q.isBetween(new int[] {1,3}, new int[] {3,3})); //both horizontal but in wrong order
		assertEquals(true, q.isBetween(new int[] {2, -4}, new int[] {2,-8})); //both correct horizontally with negatives
		assertEquals(false, q.isBetween(new int[] {2,3}, new int[] {4,5})); //includes current square
		assertEquals(false, q.isBetween(new int[] {2,5}, new int[]{2,5})); //middle = end
 		
	 }

    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals("♛", (new Queen(Color.B,2,3)).toString());
        assertEquals("♕", (new Queen(Color.W,2,3)).toString());
    }
}
