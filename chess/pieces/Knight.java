package chess.pieces;

import chess.Color;

/**
 * Knight --- class that describes the legal movements and information of a Knight chess board piece
 * @author    Jeremy McMahan
 */
public class Knight extends Piece {

    public Knight(Color color, int row, int col) {
		super(color, row, col);
	}

	/**
     * Determines if the Knight can legally move to the square, toSquare, based on the rules for the Knight
     * @param toSquare An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean canMoveTo(int[] toSquare) {
        boolean oneRowDifference = (row == toSquare[0] - 1) || (row == toSquare[0] + 1);
        boolean oneColDifference = (col == toSquare[1] - 1) || (col == toSquare[1] + 1);
        boolean twoRowDifference = (row == toSquare[0] - 2) || (row == toSquare[0] + 2);
        boolean twoColDifference = (col == toSquare[1] - 2) || (col == toSquare[1] + 2);

        return (oneRowDifference && twoColDifference) || (twoRowDifference && oneColDifference);
    }

    /**
     * Determines if the Knight can legally capture the given piece based upon the rules for the Knight.
     * @param piece A non-null Piece
     * @return boolean
     */
    public boolean canCapture(Piece piece) {
        boolean isOpponent = (color != piece.getColor());
        return isOpponent && canMoveTo(piece.getSquare());
    }

    /**
     * Determines if the square, middle, is on the path that the Knight takes as it legally moves to the end square
     * @param middle An integer array representing a square with its contents being the row and column
     * @param end An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean isBetween(int[] middle, int[] end) { return false; }

    public String toString() {
        return (color == Color.B)? "♞" : "♘";
    }
}
