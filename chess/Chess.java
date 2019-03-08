package chess;

import chess.boards.*;
import chess.pieces.Pawn;
import chess.pieces.Piece;


/**
 * Chess --- class to play a standard chess game. Includes functionality to choose a board and who goes first and then
 * play the game by continually moving pieces each turn and checking if a game ending condition has been meet.
 * @author    Jeremy McMahan
 */
public class Chess {
    private ChessBoard board; //The chess board that is being played on
    private Color turn; //The player whose turn it is
    private int[] lastFrom; //The square that was last moved from
    private int[] lastTo; //The square that was last moved to


    /**
     * Sets up the chess game with the desired type of board and the desired player to go first
     * @param firstTurn String describing the player who goes first
     * @param boardType String describing the kind of board desired
     */
    public Chess(String firstTurn, String boardType) {
        lastFrom = null;
        lastTo =  null;
        turn = (firstTurn.equals("W")) ? Color.W : Color.B;
        board = (boardType.equals("Standard")) ? new StandardBoard() : new CustomBoard();
    }

    /**
     * Moves the piece on fromSquare to toSquare if its a valid move for player. Returns a boolean to denote if the
     * move was indeed made.
     * @param fromSquare An integer array representing a square by containing its row and column
     * @param toSquare An integer array representing a square by containing its row and column
     * @return boolean will return true iff the move was legal
     */
    public boolean movePiece(int[] fromSquare, int[] toSquare) {
        if (BoardConfiguration.canMovePiece(fromSquare, toSquare, turn, board)) {
            Piece piece = board.getPiece(fromSquare);

            if (piece instanceof Pawn) {
                ((Pawn) piece).setFirstTurn(false);
            }
            lastFrom = fromSquare;
            lastTo = toSquare;
            board.movePiece(piece, toSquare);
            swapTurn();
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return boolean that is true iff the player whose turn it is, is in check mate
     */
    public boolean isCheckMate() { return BoardConfiguration.isCheckMate(turn, board); }

    /**
     * @return boolean that is true iff the player whose turn it is, is in check
     */
    public boolean isCheck() { return BoardConfiguration.isCheck(turn, board); }

    /**
     * @return boolean that is true iff a stale mate has been reached
     */
    public boolean isStaleMate() { return BoardConfiguration.isStaleMate(turn, board); }

    /**
     * Sets the current turn to be the other player
     */
    public void swapTurn() { turn = (turn == Color.W) ? Color.B : Color.W; }

    public ChessBoard getBoard() { return board; }

    /**
     * Gets the Color representation of the player whose turn it is
     * @return Color
     */
    public Color getPlayer() { return turn; }

    /**
     * Undoes the last move if there was any
     */
    public void undoLastMove() {
        if (lastFrom != null) {
            board.undoMove(lastFrom, lastTo);
            swapTurn();
            lastFrom = null;
            lastTo = null;
        }
    }
}