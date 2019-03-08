package chess.pieces;

import chess.Color;

/**
 * UltraRook --- class that describes the legal movements and information of a Ultra Rook chess board piece
 * Note that this piece moves just like a rook, but can also move to the square one above it diagonally
 * @author    Jeremy McMahan
 */
public class UltraRook extends Rook {

    public UltraRook(Color color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Determines if the UltraRook can legally move to the square, toSquare, based on the rules for the UltraRook
     * @param toSquare An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean canMoveTo(int[] toSquare) {
        boolean upOneRightOne = (toSquare[0] == row + 1) && (toSquare[1] == col + 1);
        return super.canMoveTo(toSquare) || upOneRightOne;
    }

    /**
     * Determines if the UltraRook can legally capture the given piece based upon the rules for the UltraRook.
     * @param piece A non-null Piece
     * @return boolean
     */
    public boolean canCapture(Piece piece) {
        boolean isOpponent = (color != piece.getColor());
        return isOpponent && canMoveTo(piece.getSquare());
    }

    /**
     * Determines if the square, middle, is on the path the UltraRook takes as it legally moves to the end square
     * @param middle An integer array representing a square with its contents being the row and column
     * @param end An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean isBetween(int[] middle, int[] end) {
        return super.isBetween(middle, end);
    }

    public String toString() {
        return (color == Color.B)? "♠" : "♤";
    }

}