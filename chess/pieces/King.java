package chess.pieces;

import chess.Color;

/**
 * King --- class that describes the legal movements and information of a King chess board piece
 * @author    Jeremy McMahan
 */
public class King extends Piece {

    public King(Color color, int row, int col) {
		super(color, row, col);
	}

	/**
     * Determines if the King can legally move to the square, toSquare, based on the rules for that King.
     * @param toSquare An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean canMoveTo(int[] toSquare) {
        boolean different = (row != toSquare[0]) || (col != toSquare[1]); // can't move to current square
        boolean withinOneRow = (row == toSquare[0]) || (row == toSquare[0]+1) || (row == toSquare[0]-1);
        boolean withinOneCol = (col == toSquare[1]) || (col == toSquare[1]+1) || (col == toSquare[1]-1);
        return different && withinOneRow && withinOneCol;
    }

    /**
     * Determines if the King can legally capture the given piece based upon the rules for the King.
     * @param piece A non-null Piece
     * @return boolean
     */
    public boolean canCapture(Piece piece) {
        boolean isOpponent = (color != piece.getColor());
        return isOpponent && canMoveTo(piece.getSquare());
    }

    /**
     * Determines if the square, middle, is on the path that the King takes as it legally moves to the end square
     * @param middle An integer array representing a square with its contents being the row and column
     * @param end An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean isBetween(int[] middle, int[] end) { return false; }

    public String toString() {
        return (color == Color.B)? "♚" : "♔";
    }

}