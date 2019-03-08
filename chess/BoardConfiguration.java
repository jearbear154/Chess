package chess;
import java.util.Arrays;
import java.util.HashSet;

import chess.boards.ChessBoard;
import chess.pieces.King;
import chess.pieces.Piece;

/**
 * BoardConfiguration --- class to determine information about the configuration of a chess board such as game ending
 * conditions and validity of potential piece movement
 * @author    Jeremy McMahan
 */
public class BoardConfiguration {

    /**
     * Determines if moving the piece on fromSquare to toSquare on player's turn on the given chess board is legal
     * based on the rules of the game.
     * @param fromSquare An integer array representing a square by containing its row and column
     * @param toSquare An integer array representing a square by containing its row and column
     * @param player A Color that denotes the player whose turn it is
     * @param board The chess board
     * @return boolean
     */
    public static boolean canMovePiece(int[] fromSquare, int[] toSquare, Color player, ChessBoard board) {
        try {
            Piece toMove = board.getPiece(fromSquare);
            Piece toCapture = board.getPiece(toSquare);

            boolean validSquares = board.isValidSquare(fromSquare) && board.isValidSquare(toSquare);
            boolean isPlayerPiece = toMove.getColor() == player;
            boolean canCapture = (toCapture != null) && toMove.canCapture(toCapture)
                    && isClearPath(null, toMove, toSquare, board);
            boolean canMove = (toCapture == null) && toMove.canMoveTo(toSquare)
                    && isClearPath(null, toMove, toSquare, board);
            boolean kingSafe = endangerKing(toMove,toSquare, board).isEmpty(); //the move doesn't put player in check
            boolean savesKing = !(toMove instanceof King) && isCheck(player,board)
                    && canSaveKing(player,toSquare,board);

            return (kingSafe || savesKing || (toCapture instanceof King)) &&
                    (canCapture || canMove) && validSquares && isPlayerPiece;
        } catch (NullPointerException n) { //in case any input is null or there is no piece on toSquare
            return false;
        }
    }

    /**
     * Determines if the given player is in check mate
     * @param player A Color that denotes which player's turn it is
     * @param board A ChessBoard
     * @return boolean
     */
    public static boolean isCheckMate(Color player, ChessBoard board) {
        return isCheck(player, board) && playerStuck(player,board);
    }

    /**
     * Determines if the player is in stale mate
     * @param player A Color representing the player
     * @param board A ChessBoard
     * @return boolean
     */
    public static boolean isStaleMate(Color player, ChessBoard board) {
        return !isCheck(player, board) && playerStuck(player, board);
    }
    
    /**
     * Determines if the given piece when moved to toSquare can save the king from being captured assuming its in check
     * and assuming the piece can actually move to that square legally
     * @param player A Color denoting the player
     * @param toSquare A integer array representing a square by containing its row and column
     * @param board A ChessBoard
     * @return boolean
     */
    public static boolean canSaveKing(Color player, int[] toSquare, ChessBoard board) {
        King king = board.getKing(player);
        HashSet<Piece> canKill = endangerKing(king,king.getSquare(),board);
        Piece toKillKing = canKill.iterator().next();
        boolean captureKillKing = (Arrays.equals(toKillKing.getSquare(),toSquare));
        return (captureKillKing || toKillKing.isBetween(toSquare, king.getSquare())) && (canKill.size() == 1);
    }

    /**
     * Determines if there are no chess pieces along the path that the piece, toMove, would take to get to the
     * square, toSquare. Also, for the case when are testing if there would be a clear path if we move a piece,
     * we simulate that move by ignoring the piece using ignore.
     * @param ignore A Piece
     * @param toMove A non-null Piece
     * @param toSquare An integer array representing a square by containing its row and column
     * @param board A ChessBoard
     * @return boolean
     */
    public static boolean isClearPath(Piece ignore, Piece toMove, int[] toSquare, ChessBoard board) {
        for (Piece middle : board.getPieces()) {
            if (middle != ignore && toMove.isBetween(middle.getSquare(), toSquare)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if the given player is in check on the given chess board.
     * @param player An enum type that denotes the player
     * @param board A ChessBoard
     * @return boolean
     */
    public static boolean isCheck(Color player, ChessBoard board) {
        King king = board.getKing(player);
        return !endangerKing(king, king.getSquare(),board).isEmpty();
    }

    /**
     * Determines if there are no legal moves for the given player on the given board.
     * @param player A Color that denotes the player
     * @param board A ChessBoard
     * @return boolean
     */
    public static boolean playerStuck(Color player, ChessBoard board) {
        for (Piece piece : board.getPieces()) {
            for (int[] toSquare : board.getSquares()) {
                if (canMovePiece(piece.getSquare(), toSquare, player, board)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Returns a set containing all opponent chess pieces that could capture the king of the same color as
     * toMove on the given chess board if toMove were to move to toSquare.
     * @param toMove a Piece
     * @param toSquare A square represented as an integer array containing the row and column
     * @param board A ChessBoard
     * @return HashSet<Piece>
     */
    public static HashSet<Piece> endangerKing(Piece toMove, int[] toSquare, ChessBoard board) {
        HashSet<Piece> canKillKing = new HashSet<>();
        King king = board.getKing(toMove.getColor());
        //simulate moving the king
        king = (toMove == king)? new King(king.getColor(),toSquare[0],toSquare[1]) : king;
    		
        for (Piece piece : board.getPieces()) {
            boolean couldKill = piece.canCapture(king) && isClearPath(toMove, piece, king.getSquare(), board);
            if (!piece.isBetween(toSquare, king.getSquare()) && couldKill) {
                canKillKing.add(piece);
            }
        }
        return canKillKing;
    }
}