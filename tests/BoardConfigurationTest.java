package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.BoardConfiguration;
import chess.boards.ChessBoard;
import chess.Color;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;
/**
 * BoardConfigurationTest --- class to test the functionality of the overall movement logic and end game conditions
 * @author    Jeremy McMahan
 */
public class BoardConfigurationTest {

    /**
     * Tests the canMovePiece function
     */
	@Test
	public void testCanMovePiece() {
		ChessBoard board = new ChessBoard(8);
		board.addPiece(new Pawn(Color.W,3,4), new int[] {3,4});
		board.addPiece(new King(Color.B, 3,3), new int[] {3,3});
		board.addPiece(new King(Color.W,2,6), new int[] {2,6});
		board.addPiece(new Queen(Color.W,3,7), new int[] {3,7});
		
		assertEquals(true, BoardConfiguration.canMovePiece(new int[] {3, 4}, new int[]{4,4},Color.W,board));
		assertEquals(false, BoardConfiguration.canMovePiece(new int[] {3, 4}, new int[]{4,4},Color.B,board));
		//if we kill pawn, queen kills us
		assertEquals(false, BoardConfiguration.canMovePiece(new int[] {3, 3}, new int[] {3, 4},Color.B, board));
		assertEquals(true, BoardConfiguration.canMovePiece(new int[] {3, 3}, new int[] {3,2}, Color.B, board));
		assertEquals(false, BoardConfiguration.canMovePiece(new int[] {-3, 23}, new int[] {23, 5}, Color.B, board));
		assertEquals(false, BoardConfiguration.canMovePiece(null, null, Color.W, null));
		
		board = new ChessBoard(8);
		board.addPiece(new Rook(Color.W,0,3), new int[] {0,3});
		board.addPiece(new King(Color.B, 5,4), new int[] {5,4});
		board.addPiece(new Rook(Color.W,0,4), new int[] {0,4});
		board.addPiece(new Queen(Color.W,2,5), new int[] {2,5});
		board.addPiece(new King(Color.W, 1,1), new int[] {1,1});
        //try to move out of check mate
		assertEquals(false, BoardConfiguration.canMovePiece(new int[] {5,4}, new int[] {5,5}, Color.B, board));
		
		board = new ChessBoard(8); //special case when two kings attempt to capture one another
		board.addPiece(new King(Color.W, 4,4), new int[] {4,4});
		board.addPiece(new King(Color.B, 4,6), new int[] {4,6});
		assertEquals(false, BoardConfiguration.canMovePiece(new int[] {4, 4}, new int[] {4, 5}, Color.W, board));
		assertEquals(false, BoardConfiguration.canMovePiece(new int[] {4, 6}, new int[] {4, 5}, Color.B, board));
	}
	
	/**
	 * Tests the isCheckMate function on several chess board configurations
	 */
	@Test
	public void testIsCheckMate() {
		ChessBoard board = new ChessBoard(8);
		board.addPiece(new Rook(Color.W,0,3), new int[] {0,3});
		board.addPiece(new King(Color.B, 5,4), new int[] {5,4});
		board.addPiece(new Pawn(Color.B, 4,5), new int[] {4,5});
		board.addPiece(new Rook(Color.W,0,4), new int[] {0,4});
		board.addPiece(new Queen(Color.W,2,5), new int[] {2,5});
		board.addPiece(new King(Color.W, 1,1), new int[] {1,1});
		
		assertEquals(false, BoardConfiguration.isCheckMate(Color.B,board)); //we can hide behind pawn
		board.removePiece(new int[] {4,5});
		assertEquals(false, BoardConfiguration.isCheckMate(Color.W,board)); 
		assertEquals(true, BoardConfiguration.isCheckMate(Color.B,board)); //cannot escape the rooks and queen
		board.removePiece(new int[] {2,5});
		assertEquals(false, BoardConfiguration.isCheckMate(Color.B, board)); //we can move out of the way
	}
	/**
	 * Tests the isStaleMate function on several chess board configurations
	 */
	@Test
	public void testIsStaleMate() {
		ChessBoard board = new ChessBoard(8);
		board.addPiece(new Rook(Color.W,0,3), new int[] {0,3});
		board.addPiece(new King(Color.B, 5,4), new int[] {5,4});
		board.addPiece(new Pawn(Color.B, 4,5), new int[] {4,5});
		board.addPiece(new Rook(Color.W,0,4), new int[] {0,4});
		board.addPiece(new Queen(Color.W,2,5), new int[] {2,5});
		board.removePiece(new int[] {4,5});
		
		assertEquals(false, BoardConfiguration.isStaleMate(Color.B,board)); //Checkmate
		board = new ChessBoard(8);
		board.addPiece(new King(Color.W,5,6), new int[] {5,6});
		board.addPiece(new Queen(Color.W, 6,5), new int[] {6,5});
		board.addPiece(new King(Color.B, 7,7), new int[] {7,7});
		assertEquals(true, BoardConfiguration.isStaleMate(Color.B, board)); //example from Wikipedia
		
	}

	/**
	 * Test whether we can determine if a player is stuck
	 */
	@Test
	public void testIsPlayerStuck() {
		ChessBoard board = new ChessBoard(8);
		board.addPiece(new King(Color.W, 7, 7), new int[] {7,7});//must have kings for canMove to work
		board.addPiece(new King(Color.B, 7, 0), new int[] {7,0});
		board.addPiece(new Pawn(Color.W,0,3), new int[] {0,3});
		board.addPiece(new Pawn(Color.W, 1,4), new int[] {1,4});
		board.addPiece(new Pawn(Color.W, 0,5), new int[] {0,5});
		board.addPiece(new Pawn(Color.B,1,3), new int[] {1,3});
		board.addPiece(new Pawn(Color.B,1,5), new int[] {1,5});
		board.addPiece(new Pawn(Color.B, 0,4), new int[] {0,4}); //trapped pawn in a half square
		assertEquals(false, BoardConfiguration.playerStuck(Color.B, board)); //even though a piece is stuck the player isn't
		board = new ChessBoard(8);
		board.addPiece(new King(Color.W,5,6), new int[] {5,6});
		board.addPiece(new Queen(Color.W, 6,5), new int[] {6,5});
		board.addPiece(new King(Color.B, 7,7), new int[] {7,7});
		assertEquals(true, BoardConfiguration.playerStuck(Color.B, board)); //stale mate is getting stuck
	}

	/**
	 * Test whether we can effectively determine if a move will put the king in danger of check
	 */
	@Test
	public void testEndangerKing() {
		ChessBoard board = new ChessBoard(8);
		board.addPiece(new Rook(Color.W,0,3), new int[] {0,3});
		board.addPiece(new King(Color.B, 5,4), new int[] {5,4});
		board.addPiece(new Rook(Color.B, 4,4), new int[] {4,4});
		board.addPiece(new Rook(Color.W,0,4), new int[] {0,4});
		board.addPiece(new Queen(Color.W,2,5), new int[] {2,5});
		board.addPiece(new King(Color.W, 1,1), new int[] {1,1});
		assertEquals(true, !BoardConfiguration.endangerKing(board.getPiece(new int[] {4,4}),
                new int[] {4,6},board).isEmpty());
		assertEquals(false, !BoardConfiguration.endangerKing(board.getPiece(new int[] {4,4}),
                new int[] {3,4},board).isEmpty());

	}

	/**
	 * Test whether we can determine if a player is in check
	 */
	@Test
	public void testCheck() {
		ChessBoard board = new ChessBoard(8); //a rook in front of a king
		board.addPiece(new King(Color.W, 7,7), new int[] {7,7});
		board.addPiece(new Rook(Color.W,0,3), new int[] {0,3});
		board.addPiece(new King(Color.B, 5,3), new int[] {5,3});
		assertEquals(true, BoardConfiguration.isCheck(Color.B, board));
		board.addPiece(new Rook(Color.B,2,3), new int[] {2,3}); // a rook to block the other one
		assertEquals(false, BoardConfiguration.isCheck(Color.B, board));

	}

	/**
	 * Test whether we can determine if a path has no chess.pieces between two squares following the rules of a piece
	 */
	@Test
	public void testIsClearPath() {
		ChessBoard board = new ChessBoard(8); //a rook in front of a king
		board.addPiece(new King(Color.W, 7,7), new int[] {7,7});
		board.addPiece(new Rook(Color.W,0,3), new int[] {0,3});
		board.addPiece(new King(Color.B, 5,3), new int[] {5,3});
		assertEquals(true, BoardConfiguration.isClearPath(null,
				board.getPiece(new int[] {0,3}), new int[] {5,3}, board));
		board.addPiece(new Pawn(Color.B, 2,3), new int[] {2,3}); //throw pawn in the path
		assertEquals(false, BoardConfiguration.isClearPath(null,
                board.getPiece(new int[] {0,3}), new int[] {5,3}, board));
		board.addPiece(new Bishop(Color.W, 3,1), new int[] {3,1});
		assertEquals(true, BoardConfiguration.isClearPath(null,
                board.getPiece(new int[] {3,1}), new int[] {5,3}, board));
		board.addPiece(new Pawn(Color.B, 4,2), new int[] {4,2}); //throw pawn in path
		assertEquals(false, BoardConfiguration.isClearPath(null,
                board.getPiece(new int[] {3,1}), new int[] {5,3}, board));
	}

	/**
	 * Test whether we can determine if a move can be made (not using the king) to get a player out of check
	 */
	@Test
	public void testCanSaveKing() {
        new BoardConfiguration(); //test constructor
		ChessBoard board = new ChessBoard(8); //a rook in front of a king
		board.addPiece(new King(Color.W, 7,7), new int[] {7,7});
		board.addPiece(new Rook(Color.W,0,3), new int[] {0,3});
		board.addPiece(new King(Color.B, 5,3), new int[] {5,3});
		board.addPiece(new Rook(Color.B,2,6), new int[] {2,6}); // a rook to block the other one
		assertEquals(true, BoardConfiguration.canSaveKing(Color.B, new int[] {2,3}, board));
	}
}
