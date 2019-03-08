package chess.pieces;

import chess.Color;

/**
 * Queen --- class that describes the legal movements and information of a Queen chess board piece
 * @author    Jeremy McMahan
 */
public class Queen extends Piece {

    public Queen(Color color, int row, int col) {
		super(color, row, col);
	}

	/**
     * Determines if the Queen can legally move to the square, toSquare, based on the rules for the Queen.
     * @param toSquare An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean canMoveTo(int[] toSquare) {
        boolean different = (row != toSquare[0]) || (col != toSquare[1]); //can't move to current square
        boolean sameRow = (row == toSquare[0]);
        boolean sameCol = (col == toSquare[1]);
        boolean diagonally = (Math.abs(row - toSquare[0]) == Math.abs(col - toSquare[1]));
        return (sameRow || sameCol || diagonally) && different;
    }

    /**
     * Determines if the Queen can legally capture the given piece based upon the rules for the Queen.
     * @param piece A non-null Piece
     * @return boolean
     */
    public boolean canCapture(Piece piece) {
        boolean isOpponent = (color != piece.getColor());
        return isOpponent && canMoveTo(piece.getSquare());
    }

    /**
     * Determines if the square, middle, is on the path that the Queen takes as it legally moves to the end square
     * @param middle An integer array representing a square with its contents being the row and column
     * @param end An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean isBetween(int[] middle, int[] end) {
        boolean inBetweenRow = (row < middle[0]) && (middle[0] < end[0]) ||
                (row > middle[0]) && (middle[0] > end[0]);
        boolean inBetweenCol = (col < middle[1]) && (middle[1] < end[1]) ||
                (col > middle[1]) && (middle[1] > end[1]);
        boolean bishop = inBetweenCol && inBetweenRow && canMoveTo(middle);
        boolean sameRow = (row == middle[0]) && (middle[0] == end[0]);
        boolean sameCol = (col == middle[1]) && (middle[1] == end[1]);
        boolean rook = (sameRow && inBetweenCol) || (sameCol && inBetweenRow);
        return rook || bishop;
    }

    public String toString() {
        return (color == Color.B)? "♛" : "♕";
    }

}