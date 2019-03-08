package chess.boards;

import chess.Color;
import chess.boards.ChessBoard;
import chess.pieces.*;

/**
 * StandardBoard --- class that represents the standard chess board setup
 * @author Jeremy McMahan
 */
public class StandardBoard extends ChessBoard {
    //sets up the board
    public StandardBoard() {
        super(8);
        for (int i = 0; i < 8; i++) {
            addPiece(new Pawn(Color.W,1,i), new int[]{1,i});
            addPiece(new Pawn(Color.B,6,i), new int[]{6,i});
        }
        addPiece(new King(Color.W,0,4), new int[]{0,4});
        addPiece(new King(Color.B,7,4), new int[]{7,4});
        addPiece(new Queen(Color.W,0,3), new int[]{0,3});
        addPiece(new Queen(Color.B,7,3), new int[]{7,3});
        addPiece(new Rook(Color.W,0,0), new int[]{0,0});
        addPiece(new Rook(Color.W,0,7), new int[]{0,7});
        addPiece(new Rook(Color.B,7,0), new int[]{7,0});
        addPiece(new Rook(Color.B,7,7), new int[]{7,7});
        addPiece(new Knight(Color.W,0,1), new int[]{0,1});
        addPiece(new Knight(Color.W,0,6), new int[]{0,6});
        addPiece(new Knight(Color.B,7,1), new int[]{7,1});
        addPiece(new Knight(Color.B,7,6), new int[]{7,6});
        addPiece(new Bishop(Color.W,0,2), new int[]{0,2});
        addPiece(new Bishop(Color.W,0,5), new int[]{0,5});
        addPiece(new Bishop(Color.B,7,2), new int[]{7,2});
        addPiece(new Bishop(Color.B,7,5), new int[]{7,5});
    }
}