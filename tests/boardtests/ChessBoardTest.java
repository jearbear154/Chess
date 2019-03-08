package tests.boardtests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.boards.ChessBoard;
import chess.Color;
import chess.pieces.King;
import chess.pieces.Pawn;

import java.util.Arrays;

/**
 * ChessBoardTest --- program to test the Chess Board
 * @author    Jeremy McMahan
 */
public class ChessBoardTest {
	/**
	 * Tests the constructor for the board ensuring the size is correct
	 */
	@Test
	public void testChessBoardConstructor() {
		ChessBoard board = new ChessBoard(8);
		assertEquals(8,board.getBoardSize());
	}
	
	/**
	 * Tests the methods that make a square invalid and determine if a square is invalid
	 */
	@Test
	public void testInvalidMethods() {
		ChessBoard board = new ChessBoard(8);
		try{
            board.makeSquareInvalid(new int[] {0,0});
            board.makeSquareInvalid(new int[] {3,4});
        } catch(Exception e) {} //will not occur
		assertEquals(false, board.isValidSquare(new int[] {0,0}));
		assertEquals(false, board.isValidSquare(new int[] {3,4}));
		assertEquals(true, board.isValidSquare(new int[] {2,5}));
		assertEquals(false, board.isValidSquare(null));
	}
    /**
     *
     * Test makeSquareInvalid on a square that is not on the board
     */
    @Test(expected = Exception.class)
    public void testMakeSquareInvalid() throws Exception {
        ChessBoard board = new ChessBoard(8);
        board.makeSquareInvalid(new int[]{-2,3});
    }

	/**
	 * Tests adding a piece to the board
	 */
	@Test
	public void testAddPiece() {
		ChessBoard board = new ChessBoard(8);
		King k = new King(Color.B, 4, 6);
		board.addPiece(k, k.getSquare());
		assertEquals(k, board.getPiece(k.getSquare()));
	}
	
	/**
	 * Tests removing a piece from the board
	 */
	@Test
	public void testRemovePiece() {
		ChessBoard board = new ChessBoard(8);
		King k = new King(Color.B, 4, 6);
		board.addPiece(k, k.getSquare());
		assertEquals(k, board.getPiece(k.getSquare()));
		board.removePiece(k.getSquare());
		assertEquals(null, board.getPiece(k.getSquare()));
	}
	
	/**
	 * Tests moving a piece on the board
	 */
	@Test
	public void testMovePiece() {
		ChessBoard board = new ChessBoard(8);
		King k = new King(Color.B, 4, 6);
		board.addPiece(k, k.getSquare());
		assertEquals(k, board.getPiece(k.getSquare()));
		board.movePiece(k, new int[] {3,2});
		assertEquals(null, board.getPiece(new int[]{4,6}));
		assertEquals(k, board.getPiece(new int[] {3,2}));
	}
	
	/**
	 * Tests the functionality to get a king of a certain player
	 */
	@Test
	public void testGetKing() {
		ChessBoard board = new ChessBoard(8);
		King k = new King(Color.B,3,2);
		board.addPiece(k, new int[] {3,2});
		board.addPiece(new King(Color.W, 4,6), new int[] {4,6});
		board.addPiece(new Pawn(Color.W, 2,7), new int[] {2,7});
		assertEquals(k, board.getKing(Color.B));
		board = new ChessBoard(8);
        board.addPiece(new Pawn(Color.W, 2,7), new int[] {2,7});
		assertEquals(null, board.getKing(Color.B));
	}
	
	/**
	 * Tests the ability to get a piece on a given square
	 */
	@Test
	public void testGetPiece() {
		ChessBoard board = new ChessBoard(8);
		King k = new King(Color.B,3,2);
		board.addPiece(k, k.getSquare());
		assertEquals(k, board.getPiece(k.getSquare()));
	}
	
	/**
	 * Tests the functionality to get a set of the valid squares
	 */
	@Test
	public void testGetSquares() throws Exception {
		ChessBoard board = new ChessBoard(8);
		board.makeSquareInvalid(new int[] {3,2});
		board.makeSquareInvalid(new int[] {2,6});
		for (int[] square : board.getSquares()) {
			boolean isValid = board.isValidSquare(square) && !Arrays.equals(square, new int[] {3,2}) &&
					!Arrays.equals(square, new int[] {2,6});
			boolean isInvalid = !board.isValidSquare(square) && Arrays.equals(square, new int[] {3,2})  ||
					Arrays.equals(square, new int[] {2,6});
			assertEquals(true, isValid || isInvalid);
		}
		
	}

    /**
     * Tests undoing a move on the board
     */
    @Test
    public void testUndoMove() {
        ChessBoard board = new ChessBoard(8);
        Pawn p = new Pawn(Color.B, 3, 4);
        Pawn h = new Pawn(Color.W, 4,7);
        board.addPiece(p, new int[]{3,4});
        board.addPiece(h, new int[]{4,7});
        board.movePiece(p, new int[]{4,7});
        assertEquals(null, board.getPiece(new int[]{3,4}));
        assertEquals(p, board.getPiece(new int[]{4,7}));
        board.undoMove(new int[]{3,4}, new int[]{4,7});
        assertEquals(p, board.getPiece(new int[]{3,4}));
        assertEquals(h, board.getPiece(new int[]{4,7}));
        board.movePiece(p, new int[]{5,6});
        assertEquals(p, board.getPiece(new int[]{5,6}));
        board.undoMove(new int[]{3,4}, new int[]{5,6});
		assertEquals(p, board.getPiece(new int[]{3,4}));
		assertEquals(null, board.getPiece(new int[]{5,6}));
    }
}
