package chess.pieces;

import chess.Color;

/**
 * Pawn --- class that describes the legal movements and information of a Pawn chess board piece
 * @author    Jeremy McMahan
 */
public class Pawn extends Piece {
    private boolean firstTurn; //To enforce the move twice on first move rule

    public Pawn(Color color, int row, int col) {
        super(color,row,col);
        firstTurn = true;
    }

    /**
     * Determines if the Pawn can legally move to the square, toSquare, based on the rules for the Pawn
     * @param toSquare An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean canMoveTo(int[] toSquare) {
        boolean oneRowAboveW = (row == toSquare[0] - 1) && (getColor() == Color.W);
        boolean oneRowBelowB = (row == toSquare[0] + 1) && (getColor() == Color.B);
        boolean twoRowsAboveW = (row == toSquare[0] - 2) && (getColor() == Color.W);
        boolean twoRowsBelowB = (row == toSquare[0] + 2) && (getColor() == Color.B);
        boolean sameColumn = (col == toSquare[1]);
        boolean firstTurnRule = firstTurn && (twoRowsAboveW || twoRowsBelowB);

        return (oneRowAboveW || oneRowBelowB || firstTurnRule) && sameColumn;
    }

    /**
     * Determines if the Pawn can legally capture the given piece based upon the rules for the Pawn.
     * @param piece A non-null Piece
     * @return boolean
     */
    public boolean canCapture(Piece piece) {
        int[] square = piece.getSquare();
        boolean oneColAway = (col == square[1] + 1 || col == square[1] - 1);
        boolean oneRowAboveW = (row == square[0] - 1) && (getColor() == Color.W);
        boolean oneRowBelowB = (row == square[0] + 1) && (getColor() == Color.B);
        boolean isOpponent = (color != piece.getColor());

        return isOpponent && (oneRowAboveW || oneRowBelowB) && oneColAway;
    }

    /**
     * Determines if the square, middle, is on the path that the Pawn takes as it legally moves to the end square
     * @param middle An integer array representing a square with its contents being the row and column
     * @param end An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public boolean isBetween(int[] middle, int[] end) { 
        boolean endTwoRowsAboveW = (row == end[0] - 2) && (getColor() == Color.W);
        boolean endTwoRowsBelowB = (row == end[0] + 2) && (getColor() == Color.B);
        boolean middleOneRowAboveW = (row == middle[0] - 1) && (getColor() == Color.W);
        boolean middleOneRowBelowB = (row == middle[0] + 1) && (getColor() == Color.B);
        boolean sameColumn = (col == middle[1]) && (col == end[1]);
        boolean middleVertically = (middleOneRowAboveW && endTwoRowsAboveW || middleOneRowBelowB && endTwoRowsBelowB);

        return firstTurn && sameColumn && middleVertically;
    }

    public void setFirstTurn(boolean firstTurn) { this.firstTurn = firstTurn; }

    public String toString(){
        return (color == Color.B)? "♟" : "♙";
    }

}