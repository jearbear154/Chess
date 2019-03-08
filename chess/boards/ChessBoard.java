package chess.boards;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import chess.Color;
import chess.pieces.*;

/**
 * ChessBoard --- class that represents a variable shape chess board by taking a square board and invalidating squares
 * to get the desired shape.
 * @author    Jeremy McMahan
 */
public class ChessBoard {
    private final int boardSize; //the square size of the board
    private final HashMap<Integer, Piece> board; //the board represented as the current chess.pieces and their positions
    private final HashSet<Integer> invalidSquare; //contains the index of all invalid squares
    private Piece lastCaptured; //For use with the undo feature

    /**
     * Constructor: creates a new board of given board size.
     * @param boardSize An integer giving the length of one side of the board
     */
    public ChessBoard(int boardSize) {
        this.boardSize = boardSize;
        board = new HashMap<>();
        invalidSquare = new HashSet<>();
        lastCaptured = null;
    }

    /**
     * Makes a given square invalid on the board
     * @param square An integer array representing a square by containing its row and column
     * @throws Exception denotes if the given square was off the board or null
     */
    public void makeSquareInvalid(int[] square) throws Exception {
        if (!(0 <= square[0] && square[0] < boardSize) && (0 <= square[1] && square[1] < boardSize)) {
            throw new Exception(); //square not on the board
        }
        invalidSquare.add(getSquareIndex(square));
    }

    /**
     * Adds a piece to the desired chess board square if it is a valid square on the board
     * @param toSquare An integer array representing a square by containing its row and column
     */
    public void addPiece(Piece piece, int[] toSquare) {
        if (isValidSquare(toSquare) && piece != null) {
            lastCaptured = board.put(getSquareIndex(toSquare),piece);
        }
    }

    /**
     * Removes the piece from the given square on the board
     * @param fromSquare An integer array representing a square by containing its row and column
     */
    public void removePiece(int[] fromSquare) {
        board.remove(getSquareIndex(fromSquare));
    }

    /**
     * Moves the given piece from its current square to the given square assuming the square is valid
     * @param piece A non-null Piece
     * @param toSquare An integer array representing a square by containing its row and column
     */
    public void movePiece(Piece piece, int[] toSquare) {
        addPiece(piece, toSquare);
        removePiece(piece.getSquare());
        piece.setSquare(toSquare[0], toSquare[1]);
    }

    /**
     * Determines if the given square is valid on the chess board.
     * @param square An integer array representing a square by containing its row and column
     * @return boolean
     */
    public boolean isValidSquare(int[] square) {
        if (square == null) {
            return false;
        }
        boolean onBoard = (0 <= square[0] && square[0] < boardSize) && (0 <= square[1] && square[1] < boardSize);
        boolean isValid = !invalidSquare.contains(getSquareIndex(square));
        return isValid && onBoard;
    }

    public int getBoardSize() { return boardSize; }

    /**
     * Returns the piece on the given square or null if there is none on that square
     * @param onSquare An integer array representing a square by containing its row and column
     * @return Piece
     */
    public Piece getPiece(int[] onSquare) {
        return board.get(getSquareIndex(onSquare));
    }

    /**
     * Returns the set of all chess pieces currently on the board.
     * @return HashSet<Piece>
     */
    public Collection<Piece> getPieces() {
        return board.values();
    }

    /**
     * Returns a set of all valid squares on the chess board
     * @return HashSet<>
     */
    public HashSet<int[]> getSquares() {
        HashSet<int[]> squares = new HashSet<>();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (isValidSquare(new int[]{row,col})) {
                    squares.add(new int[]{row,col});
                }
            }
        }
        return squares;
    }

    /**
     * Returns the king belonging to the player
     * @param player Color denoting the player
     * @return King 
     */
	public King getKing(Color player) {
		for (Piece piece : board.values()) {
			if (piece instanceof King && piece.getColor() == player) {
				return (King) piece;
			}
		}
		return null;
	}

    /**
     * Assuming the piece from fromSquare was just moved to toSquare, will reset the board to its configuration
     * before the move was made.
     */
	public void undoMove(int[] fromSquare, int[] toSquare) {
        Piece moved = getPiece(toSquare);
        Piece putBack = lastCaptured;
        if (putBack != null) {
            movePiece(moved, fromSquare);
            addPiece(putBack, toSquare);
        } else {
            movePiece(moved, fromSquare);
        }
        if (moved instanceof Pawn && (fromSquare[0] == 1 || fromSquare[0] == 6)) {
            ((Pawn) moved).setFirstTurn(true);
        }
        lastCaptured = null;
    }

    /**
     * Converts the square into an integer using the same calculation as for indexing two dimensional arrays
     * @param square An integer array representing a square by containing its row and column
     * @return int the index of the square to be used in the board
     */
    private int getSquareIndex(int[] square) {
        return square[0] * boardSize + square[1];
    }

}