package tests;

import static org.junit.Assert.assertEquals;

import chess.Color;
import chess.PlayChess;
import chess.pieces.Piece;
import org.junit.Test;

import chess.Chess;

/**
 * ChessTest --- class to test the chess program
 * @author    Jeremy McMahan
 */
public class ChessTest {
    /**
     * Tests setup of the game
     */
	@Test
	public void testGameSetup() {
	    PlayChess game = new PlayChess();
		game.main(null);
	}

    /**
     * Tests the movePiece method using a standard board
     */
    @Test
    public void testMovePiece() {
        Chess game = new Chess("W", "Standard");
        assertEquals(true, game.movePiece(new int[] {0,1}, new int[] {2,2}));
        assertEquals(false, game.movePiece(new int[] {2, 2}, new int[] {0,3}));
        assertEquals(true, game.movePiece(new int[] {6,1},  new int[] {4,1}));//try pawn first move
        assertEquals(false, game.movePiece(new int[] {4,3}, new int[] {4,6}));//no chess pieces in these spots
        assertEquals(false, game.movePiece(new int[] {4,1}, new int[] {2,1}));//no longer first move
        assertEquals(false, game.movePiece(new int[] {1,3}, new int[] {4,1}));//must move a square
        assertEquals(false, game.movePiece(null, null)); //invalid squares
        assertEquals(Color.W, game.getPlayer());
    }

    /**
     * Tests the undo method using a standard board
     */
    @Test
    public void testUndoLastMove() {
        Chess game = new Chess("W", "Standard");
        Piece p = game.getBoard().getPiece(new int[]{0,1});
        assertEquals(true, game.movePiece(new int[] {0,1}, new int[] {2,2}));
        assertEquals(null, game.getBoard().getPiece(new int[]{0,1}));
        game.undoLastMove();
        assertEquals(p, game.getBoard().getPiece(new int[]{0,1}));
    }

    //The following methods are all tested extensively in the BoardConfigurationTest and so the wrapper functions here
    //are not test extensively

    /**
     * Tests if the current player is in check mate
     */
    @Test
    public void testIsCheckMate() {
        Chess game = new Chess("W", "Standard");
        assertEquals(false, game.isCheckMate());
    }

    /**
     * Tests if the current player is in check
     */
    @Test
    public void testIsCheck() {
        Chess game = new Chess("W", "Standard");
        assertEquals(false, game.isCheck());
    }

    /**
     * Tests if a stale mate has been reached
     */
    @Test
    public void testIsStaleMate() {
        Chess game = new Chess("W", "Standard");
        assertEquals(false, game.isStaleMate());
    }

    /**
     * Tests the toString method for Color
     */
    @Test
    public void testColorString() {
        assertEquals(true, Color.W.toString().equals("W"));
        assertEquals(true, Color.B.toString().equals("B"));
    }

}
