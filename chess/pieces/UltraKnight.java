package chess.pieces;

import chess.Color;

/**
 * UltraKnight --- class that describes the legal movements and information of a Ultra Knight chess board piece
 * Note this piece moves like a knight and can move to any square to its right in the same row
 * @author    Jeremy McMahan
 */
public class UltraKnight extends Knight {

    public UltraKnight(Color color, int row, int col) { super(color, row, col); }

    /**
     * Determines if the UltraKnight can legally move to the square, toSquare, based on the rules for the UltraKnight
     * @param toSquare An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean canMoveTo(int[] toSquare) {
        boolean toTheRight = (toSquare[1] > col);
        boolean sameRow = (toSquare[0] == row);
        return super.canMoveTo(toSquare) || (toTheRight && sameRow);
    }

    /**
     * Determines if the UltraKnight can legally capture the given piece based upon the rules for the UltraKnight.
     * @param piece A non-null Piece
     * @return boolean
     */
    public boolean canCapture(Piece piece) {
        boolean isOpponent = (color != piece.getColor());
        return isOpponent && canMoveTo(piece.getSquare());
    }

    public boolean isBetween(int[] middle, int[] end) {
        boolean sameRow = (row == middle[0]) && (middle[0] == end[0]);
        boolean isRightwards = ((col < middle[1]) && (middle[1] < end[1]));
        return sameRow && isRightwards;
    }

    public String toString() {
        return (color == Color.B)? "♦" : "♢";
    }

}
