package chess.pieces;

import chess.Color;

/**
 * Piece --- class that describes the functionality of a general chess board piece, such as legal movements
 * @author    Jeremy McMahan
 */
public abstract class Piece{
    protected final Color color; //Which player this piece belongs to
    protected int row; //the row number of the square on the board where this piece lies
    protected int col; //the column number of the square on the board where this piece lies

    public Piece(Color color, int row, int col) {
        this.color = color; this.row = row; this.col = col;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Sets the square of the piece with corresponding row and column on the board
     * @param row the row of the square on the board
     * @param col the column of the square on the board
     */
    public void setSquare(int row, int col) { this.row = row; this.col = col; }

    /**
     * Gets the square of the piece represented as an int array with the first entry being the row and the second being
     * the column of the square.
     * @return int[]
     */
    public int[] getSquare() { return new int[]{row, col}; }

    /**
     * Determines if the piece can legally move to the square, toSquare, based on the rules for that piece
     * @param toSquare an integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public abstract boolean canMoveTo(int[] toSquare);

    /**
     * Determines if the piece can legally capture the given piece based upon the rules for the piece
     * @param piece a Piece
     * @return boolean
     */
    public abstract boolean canCapture(Piece piece);

    /**
     * Determines if the square, middle, is on the path that this piece takes as it legally moves to the end square
     * @param middle An integer array representing a square with its contents being the row and column
     * @param end An integer array representing a square with its contents being the row and column
     * @return boolean
     */
    public abstract boolean isBetween(int[] middle, int[] end);

    /**
     * Outputs the icon of the chess piece as a string
     * @return String
     */
    public abstract String toString();
}