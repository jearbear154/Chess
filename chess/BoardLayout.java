package chess;

import chess.boards.ChessBoard;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * BoardLayout --- class that creates a graphical representation of the chess board
 * @author      Jeremy McMahan
 */
public class BoardLayout extends JPanel {

    /**
     * Square --- class that represents a square on the chess board graphically
     */
    public class Square extends JButton {
        public int[] position; //the position of the square on the board

        /**
         * Sets up the custom JButton to look like a proper chess square
         * @param board the chess board
         * @param row the row of the square on the board
         * @param col the column of the square on the board
         * @param squares the action listener to use for when the square is clicked
         */
        public Square(ChessBoard board, int row, int col, ActionListener squares) {
            super((board.getPiece(new int[] {row,col}) != null)?
                    board.getPiece(new int[] {row,col}).toString() : "");

            if (!board.isValidSquare(new int[] {row,col})) { //make invalid squares black
                setBackground(Color.BLACK);
            } else if ((row + col) % 2 == 0) { //alternate valid square colors
                setBackground(Color.GRAY);
            } else {
                setBackground(Color.WHITE);
            }

            position = new int[]{row, col};
            addActionListener(squares);
            setFont(new Font("Serif", Font.PLAIN, 50)); //font size that looks big enough to me
            setOpaque(true);
            setBorderPainted(false);
        }
    }

    /**
     * Sets up the graphical representation of the chess board
     * @param board the chess board we are modeling after
     * @param squares the action listener to handle clicking of the chess board squares
     */
    public BoardLayout(ChessBoard board, ActionListener squares) {
        super(new GridLayout(board.getBoardSize(), board.getBoardSize()));

        for(int row = board.getBoardSize()-1; row >= 0; row--) { //ensure white is on the bottom of the board
            for (int col = 0; col < board.getBoardSize(); col++) {
                add(new Square(board, row, col, squares));
            }
        }
    }

}