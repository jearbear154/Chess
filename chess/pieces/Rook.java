package chess.pieces;

import chess.Color;

/**
 * Rook --- class that describes the legal movements and information of a Rook chess board piece
 * @author    Jeremy McMahan
 */
public class Rook extends Piece {

    public Rook(Color color, int row, int col) {
		super(color, row, col);
	}

	/**
     * Determines if the Rook can legally move to the square, toSquare, based on the rules for the Rook
     * @param toSquare An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean canMoveTo(int[] toSquare) {
        boolean different = (row != toSquare[0]) || (col != toSquare[1]);
        boolean sameRow = (row == toSquare[0]);
        boolean sameCol = (col == toSquare[1]);
        return (sameRow || sameCol) && different;
    }

    /**
     * Determines if the Rook can legally capture the given piece based upon the rules for the Rook.
     * @param piece A non-null Piece
     * @return boolean
     */
    public boolean canCapture(Piece piece) {
        boolean isOpponent = (color != piece.getColor());
        return isOpponent && canMoveTo(piece.getSquare());
    }

    /**
     * Determines if the square, middle, is on the path the Rook takes as it legally moves to the end square
     * @param middle An integer array representing a square with its contents being the row and column
     * @param end An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean isBetween(int[] middle, int[] end) {
        boolean sameRow = (row == middle[0]) && (middle[0] == end[0]);
        boolean sameCol = (col == middle[1]) && (middle[1] == end[1]);
        boolean inBetweenRow = ((row < middle[0]) && (middle[0] < end[0])) ||
                ((row > middle[0]) && (middle[0] > end[0]));
        boolean inBetweenCol = ((col < middle[1]) && (middle[1] < end[1])) ||
                ((col > middle[1]) && (middle[1] > end[1]));
        return (sameRow && inBetweenCol) || (sameCol && inBetweenRow);
    }

    public String toString() {
        return (color == Color.B)? "♜" : "♖";
    }

}