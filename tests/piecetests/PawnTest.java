package tests.piecetests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.Color;
import chess.pieces.Pawn;
/**
 * PawnTest --- program to test the movement functionality of a pawn
 * @author    Jeremy McMahan
 */
public class PawnTest {
	/**
	 * Tests the pawn's constructor, specifically, for the firstTurn field 
	 */
	@Test
	public void testPawnConstructor() {
		Pawn p = new Pawn(Color.W,2,3);
		assertEquals(2, p.getSquare()[0]);
		assertEquals(3, p.getSquare()[1]);
		assertEquals(Color.W, p.getColor());
		assertEquals(true, p.canMoveTo(new int[]{4,3})); //proves that firstTurn = true
	}

	/**
	 * Tests the pawn's ability to determine if it can move to a square 
	 */
	@Test
    public void testCanMoveTo() {
        Pawn p = new Pawn(Color.W,2,3);
        assertEquals(true, p.canMoveTo(new int[]{4,3})); //check first move rule
        assertEquals(true, p.canMoveTo(new int[] {3,3}));
        p.setFirstTurn(false);
        assertEquals(false, p.canMoveTo(new int[]{4,3})); //re-check first move rule
        assertEquals(false, p.canMoveTo(new int[] {4,6}));
        assertEquals(false, p.canMoveTo(new int[] {2,3}));
    }

	/**
	 * Tests the pawn's ability to determine if it can capture a piece
	 */
	@Test
	public void testCanCapture() {
		Pawn p = new Pawn(Color.W, 2,3);
		assertEquals(true, p.canCapture(new Pawn(Color.B,3,4))); 
        assertEquals(false, p.canCapture(new Pawn(Color.W,3,4))); //check pawn doesn't kill same color chess.pieces
        assertEquals(false, p.canCapture(new Pawn(Color.B,1,2))); //check pawn goes in right direction
        p = new Pawn(Color.B, 2, 3);
        assertEquals(true, p.canCapture(new Pawn(Color.W,1,2))); //check pawn goes in right direction
        assertEquals(false, p.canCapture(new Pawn(Color.W,3,4)));
	}
	
	/**
	 * Test the pawn's ability to determine if something is in it's way to getting to another square
	 */
	@Test
	public void testIsBetween() {
		Pawn p = new Pawn(Color.W,2,3);
		assertEquals(true, p.isBetween(new int[] {3, 3}, new int[] {4,3}));
		assertEquals(false, p.isBetween(new int[] {3, 3}, new int[] {5,3}));
		assertEquals(false, p.isBetween(new int[] {4,3}, new int[] {4,3})); //middle = end
		assertEquals(false, p.isBetween(new int[] {2,3}, new int[]{4,3})); //our square = middle
	}

    /**
     * Tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals("♟", (new Pawn(Color.B,2,3)).toString());
        assertEquals("♙", (new Pawn(Color.W,2,3)).toString());
    }
}
